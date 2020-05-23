package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.User;
import royalstacks.app.model.repository.CustomerRepository;
import royalstacks.app.model.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
}
