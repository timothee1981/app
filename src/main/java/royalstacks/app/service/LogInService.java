package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.User;
import royalstacks.app.model.repository.UserRepository;

import java.util.Optional;

@Service
public class LogInService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByUsername (String username) {
        Optional<User> user = Optional.ofNullable(userRepository.findUserByUsername(username));
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

}
