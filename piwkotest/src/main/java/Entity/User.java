package Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name ="users")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_users;

    @Column(unique=true)
    private String login;

    private String password;

    private String email;

    private String roles;

    private boolean isEnabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_token")
    private Token token;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="id_users")//"id_users tak zapisuje sie w tabeli report
            List<Hops> hopsList=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="id_users")//"id_users tak zapisuje sie w tabeli queue
            List<Malt> maltList=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="id_users")//"id_users tak zapisuje sie w tabeli done
            List<Yeast> yeastList=new ArrayList<>();


    public User(String login, String password, String email, String roles,Boolean isEnabled) {
       super();
        this.login = login;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.isEnabled=isEnabled;
    }

    public User()
    {

    }

    public List<Hops> getHopsList() {
        return hopsList;
    }

    public void setHopsList(List<Hops> hopsList) {
        this.hopsList = hopsList;
    }

    public List<Malt> getMaltList() {
        return maltList;
    }

    public void setMaltList(List<Malt> maltList) {
        this.maltList = maltList;
    }

    public List<Yeast> getYeastList() {
        return yeastList;
    }

    public void setYeastList(List<Yeast> yeastList) {
        this.yeastList = yeastList;
    }

    public int getId_users() {
        return id_users;
    }

    public void setId_users(int id_users) {
        this.id_users = id_users;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean getEnable() {
        return isEnabled;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

}



