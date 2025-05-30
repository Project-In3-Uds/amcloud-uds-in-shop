package cm.amcloud.mobile.uds_in_shop.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cm.amcloud.mobile.uds_in_shop.exception.ResourceNotFoundException;
import cm.amcloud.mobile.uds_in_shop.model.Role;
import cm.amcloud.mobile.uds_in_shop.model.User;
import cm.amcloud.mobile.uds_in_shop.repository.RoleRepository;
import cm.amcloud.mobile.uds_in_shop.repository.UserRepository;
import cm.amcloud.mobile.uds_in_shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    

    @Override
    public User createUser(User user) {
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email '" + user.getEmail() + "' is already in use.");
        }

        // Assign role by name
        if (user.getRole() != null && user.getRole().getName() != null) {
            Role role = roleRepository.findByName(user.getRole().getName())
                    .orElseThrow(() -> new RuntimeException("Role '" + user.getRole().getName() + "' not found"));
            user.setRole(role);
        } else {
            // Assign default role if no role is provided
            Role defaultRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Default role USER not found"));
            user.setRole(defaultRole);
        }

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findByDeletedFalse(); // Correct method call
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);

        // Check if email is being updated to an already existing email
        if (!existingUser.getEmail().equals(user.getEmail()) &&
                userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email '" + user.getEmail() + "' is already in use.");
        }

        // Assign role by name
        if (user.getRole() != null && user.getRole().getName() != null) {
            Role role = roleRepository.findByName(user.getRole().getName())
                    .orElseThrow(() -> new RuntimeException("Role '" + user.getRole().getName() + "' not found"));
            existingUser.setRole(role);
        }

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        user.setDeleted(true); // Correct method call
        userRepository.save(user);
    }

    @Override
    public User registerUser(User user) {
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email '" + user.getEmail() + "' is already in use.");
        }

        // Assign default role
        Role defaultRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Default role USER not found"));
        user.setRole(defaultRole);

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
