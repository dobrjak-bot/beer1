package Repository;

import Entity.Hops;
import Entity.Yeast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface HopsRepository extends JpaRepository<Hops,Integer>
{
    //splesniale
    @Query(nativeQuery= true,value="select * from piwkotest.hops WHERE date <= ?2 AND id_users=?1")
    List<Hops> findByDate(int id_users, Date date);

    //dobre
    @Query(nativeQuery= true,value="select * from piwkotest.hops WHERE date >= ?2 AND id_users=?1")
    List<Hops> findByDate2(int id_users, Date date);


}
