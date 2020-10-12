package Service;

import Entity.User;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user)
    {
        return userRepository.save(user);
    }


    public User findByLogin(String login)
    {
        return userRepository.findByLogin(login);
    }

}
