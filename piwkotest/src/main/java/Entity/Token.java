package Entity;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Entity
@Table(name ="tokens")
public class Token
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_token;

    private int value;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "token")
    private User user;


    public Token(int value, User user) {
       super();
        this.value = value;
        this.user = user;
    }

    public Token()
    {

    }

    public int getId_token() {
        return id_token;
    }

    public void setId_token(int id_token) {
        this.id_token = id_token;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}