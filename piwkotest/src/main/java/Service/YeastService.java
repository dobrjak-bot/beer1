package Service;

import Entity.Yeast;
import Repository.YeastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YeastService
{
    YeastRepository yeastRepository;

    @Autowired

    public YeastService(YeastRepository yeastRepository) {
        this.yeastRepository = yeastRepository;
    }


    public Yeast Save(Yeast yeast)
    {
        return yeastRepository.save(yeast);
    }
}
