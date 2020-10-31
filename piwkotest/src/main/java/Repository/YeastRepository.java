package Repository;

import Entity.Hops;
import Entity.User;
import Entity.Yeast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface YeastRepository extends JpaRepository<Yeast, Integer>
{

    //splesniale
    @Query(nativeQuery= true,value="select * from piwkotest.yeasts WHERE date <= ?2 AND id_users=?1")
    List<Yeast> findByDate(int id_users, Date date);

    //dobre
    @Query(nativeQuery= true,value="select * from piwkotest.yeasts WHERE date >= ?2 AND id_users=?1")
    List<Yeast> findByDate2(int id_users, Date date);

}

