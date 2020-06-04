package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import royalstacks.app.model.User;
import royalstacks.app.model.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private static final int MIN_USERNAME_LENGTH = 3;
    private static final int MAX_USERNAME_LENGTH = 15;
    private static final int MIN_PASSWORD_LENGTH = 10;
    private static final int MAX_PASSWORD_LENGTH = 100;

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

    public boolean isUsernameFormatValid(String username){
        // username mag kleine letters, grote letters, getallen, en - of _ bevatten en moet tussen 3 en 20 characters lang zijn.
        return username.matches("^[a-zA-Z0-9_-]+$")
                && username.length() >= MIN_USERNAME_LENGTH
                && username.length() <= MAX_USERNAME_LENGTH;
    }

    public boolean isPasswordValid(String inputPassword){
        // Moet 1 kleine letter, 1 grote letter, 1 nummer, 1 speciaal karakter en minstens 10 karakters lang zijn
        return inputPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!\"#$%&'()*+,\\-./:;<=>?@^_`{|}~\\[\\]])[A-Za-z\\d!\"#$%&'()*+,\\-./:;<=>?@^_`{|}~\\[\\]]+$")
                && inputPassword.length() >= MIN_PASSWORD_LENGTH
                && inputPassword.length() <= MAX_PASSWORD_LENGTH;
    }

    public boolean isFirstNameValid(String firstName){
        firstName = firstName.trim();
        return firstName.matches("^[^\\s].*[a-zA-Z-'\\s?][^.]{1,100}");
    }

    public boolean isLastNameValid(String lastName){
        lastName = lastName.trim();
        return lastName.matches("^[^\\s].*[a-zA-Z-'\\s?][^.]{1,100}");
    }
}
