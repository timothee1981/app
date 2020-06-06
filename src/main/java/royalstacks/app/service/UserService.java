package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import royalstacks.app.model.User;
import royalstacks.app.model.repository.UserRepository;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private static final int MIN_USERNAME_LENGTH = 3;
    private static final int MAX_USERNAME_LENGTH = 20;
    private static final int MIN_PASSWORD_LENGTH = 10;
    private static final int MAX_PASSWORD_LENGTH = 100;

    private static final Pattern usernameRegex = Pattern.compile("^[a-zA-Z0-9_-]+$");
    private static final Pattern passwordRegex = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!\"#$%&'()*+,\\-./:;<=>?@^_`{|}~\\[\\]])[A-Za-z\\d!\"#$%&'()*+,\\-./:;<=>?@^_`{|}~\\[\\]]+$");
    private static final Pattern nameRegex = Pattern.compile("^[^\\s].*[a-zA-Z-'\\s][^.]{1,100}");

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
        Matcher m = usernameRegex.matcher(username);
        // username mag kleine letters, grote letters, getallen, en - of _ bevatten en moet tussen 3 en 20 characters lang zijn.
        return m.matches()
                && username.length() >= MIN_USERNAME_LENGTH
                && username.length() <= MAX_USERNAME_LENGTH;
    }

    public boolean isPasswordValid(String password){
        Matcher m = passwordRegex.matcher(password);
        // Moet 1 kleine letter, 1 grote letter, 1 nummer, 1 speciaal karakter en minstens 10 karakters lang zijn
        return m.matches()
                && password.length() >= MIN_PASSWORD_LENGTH
                && password.length() <= MAX_PASSWORD_LENGTH;
    }

    public boolean isNameValid(String name){
        Matcher m = nameRegex.matcher(name);
        return m.matches();
    }
}
