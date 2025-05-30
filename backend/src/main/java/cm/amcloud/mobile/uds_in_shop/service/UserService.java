package cm.amcloud.mobile.uds_in_shop.service;

import java.util.List;

import cm.amcloud.mobile.uds_in_shop.model.User;

public interface UserService {
    User createUser(User user);
    User registerUser(User user); // New method for user registration
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
