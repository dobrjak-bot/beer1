package Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name ="hops")
public class Hops
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_hops;

    private LocalDate date;

    public Hops(LocalDate date)
    {
        super();
        this.date = date;
    }

    public Hops()
    {

    }

    public int getId_hops() {
        return id_hops;
    }

    public void setId_hops(int id_hops) {
        this.id_hops = id_hops;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
