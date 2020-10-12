package Repository;

import Entity.Token;
import Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer>
{
    Token findByValue(int Value);
    Token findByUser(User user);
    Token save(Token token);

}
