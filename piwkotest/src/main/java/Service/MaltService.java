package Service;


import Entity.Hops;
import Entity.Malt;
import Entity.Yeast;
import Repository.MaltRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class MaltService
{

    @Autowired
    MaltRepository maltRepository;

    @Autowired
    public MaltService(MaltRepository maltRepository)
    {
        this.maltRepository=maltRepository;
    }

    public Malt Save(Malt malt)
    {
        return maltRepository.save(malt);
    }

    public List<Malt> findByDate(int id_users, Date date)
    {
        return maltRepository.findByDate(id_users,date);
    }

    public List<Malt> findByDate2(int id_users, Date date)
    {
        return maltRepository.findByDate2(id_users,date);
    }

    public void deleteMoldy(List<Malt> malttodelete )
    {
        maltRepository.deleteAll(malttodelete);
    }

    public void deleteUsed(List<Malt> MaltToDelete)
    {
        maltRepository.deleteInBatch(MaltToDelete);
    }

}
