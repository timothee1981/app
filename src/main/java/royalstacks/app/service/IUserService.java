package royalstacks.app.service;

import royalstacks.app.model.User;

import java.util.Optional;

public interface IUserService {

    public Optional<User> findById(int id);

}
