package Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name ="malts")
public class Malt
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_malts;

    private LocalDate date;

    public Malt(LocalDate date)
    {
        super();
        this.date = date;
    }

    public Malt()
    {

    }

    public int getId_malts() {
        return id_malts;
    }

    public void setId_malts(int id_malts) {
        this.id_malts = id_malts;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
