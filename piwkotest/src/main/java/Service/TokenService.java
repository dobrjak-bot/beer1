package Service;

import Entity.Token;
import Entity.User;
import Repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService
{
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository)
    {
        this.tokenRepository=tokenRepository;
    }

    public Token findByValue(int value)
    {
        return tokenRepository.findByValue(value);
    }

    public Token findByUser(User user)
    {
        return tokenRepository.findByUser(user);
    }

    public Token save (Token token)
    {
        return tokenRepository.save(token);
    }
}
