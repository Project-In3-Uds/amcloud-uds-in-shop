package cm.amcloud.mobile.uds_in_shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cm.amcloud.mobile.uds_in_shop.exception.ResourceNotFoundException;
import cm.amcloud.mobile.uds_in_shop.model.Role;
import cm.amcloud.mobile.uds_in_shop.model.User;
import cm.amcloud.mobile.uds_in_shop.repository.RoleRepository;
import cm.amcloud.mobile.uds_in_shop.repository.UserRepository;
import cm.amcloud.mobile.uds_in_shop.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findByDeletedFalse(); // Fetch only non-deleted users
    }

    @Override
    public User updateUserRole(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with name: " + roleName));
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        user.setDeleted(true); // Perform a soft delete by marking the user as deleted
        userRepository.save(user);
    }
}   

