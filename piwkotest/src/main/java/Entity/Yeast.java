package Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name ="yeasts")
public class Yeast
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_yeast;

    private LocalDate date;

    public Yeast(LocalDate date)
    {
        super();
        this.date = date;
    }

    public Yeast()
    {

    }

    public int getId_yeast() {
        return id_yeast;
    }

    public void setId_yeast(int id_yeast) {
        this.id_yeast = id_yeast;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
