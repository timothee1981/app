package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import royalstacks.app.model.User;
import royalstacks.app.model.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByUserId(int userId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        else {
            return null;
        }
    }
}
