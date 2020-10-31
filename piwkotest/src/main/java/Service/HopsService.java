package Service;

import Entity.Hops;
import Entity.Yeast;
import Repository.HopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class HopsService
{

    @Autowired
    HopsRepository hopsRepository;

    @Autowired
    public HopsService(HopsRepository hopsRepository)
    {
        this.hopsRepository=hopsRepository;
    }

    public Hops Save(Hops hops)
    {
        return hopsRepository.save(hops);
    }

    public List<Hops> findByDate(int id_users, Date date)
    {
        return hopsRepository.findByDate(id_users,date);
    }

    public List<Hops> findByDate2(int id_users, Date date)
    {
        return hopsRepository.findByDate2(id_users,date);
    }


    public void deleteMoldy(List<Hops> hopstodelete )
    {
       hopsRepository.deleteAll(hopstodelete);
    }

    public void deleteUsed(List<Hops> HopsToDelete)
    {
        hopsRepository.deleteInBatch(HopsToDelete);
    }

}
