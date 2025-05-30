package cm.amcloud.mobile.uds_in_shop.service;

import java.util.List;

import cm.amcloud.mobile.uds_in_shop.model.User;

public interface AdminService {
    List<User> getAllUsers();
    User updateUserRole(Long userId, String roleName);
    void deleteUser(Long userId);
}
