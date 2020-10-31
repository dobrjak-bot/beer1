package Service;

import Entity.Hops;
import Entity.User;
import Entity.Yeast;
import Repository.YeastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class YeastService
{
    @Autowired
    YeastRepository yeastRepository;

    @Autowired
    public YeastService(YeastRepository yeastRepository) {
        this.yeastRepository = yeastRepository;
    }


    public Yeast Save(Yeast yeast)
    {
        return yeastRepository.save(yeast);
    }

    public List<Yeast> findByDate(int id_users,Date date)
    {
       return yeastRepository.findByDate(id_users,date);
    }

    public List<Yeast> findByDate2(int id_users, Date date)
    {
        return yeastRepository.findByDate2(id_users,date);
    }

    public void deleteMoldy(List<Yeast> yeastodelete )
    {
        yeastRepository.deleteAll(yeastodelete);
    }

    public void deleteUsed(List<Yeast> YeastToDelete)
    {
        yeastRepository.deleteInBatch(YeastToDelete);
    }

}
