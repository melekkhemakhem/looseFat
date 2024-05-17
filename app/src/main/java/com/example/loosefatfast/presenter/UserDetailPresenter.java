package com.example.loosefatfast.presenter;

import com.example.loosefatfast.model.User;
import com.example.loosefatfast.model.UserRepository;
import com.example.loosefatfast.view.UserDetailsActivity;

public class UserDetailPresenter {
    private UserDetailsActivity view;
    private UserRepository userRepository;

    public UserDetailPresenter(UserDetailsActivity view) {
        this.view = view;
        this.userRepository = new UserRepository();
    }

    public void loadUserDetails(String userId) {
        userRepository.getUser(userId, new UserRepository.UserCallback() {
            @Override
            public void onUserLoaded(User user) {
                view.displayUserDetails(user);
            }

            @Override
            public void onError(String message) {
                view.showError(message);
            }
        });
    }

    public void updateUserDetails(User user) {
        userRepository.saveUser(user, new UserRepository.UserCallback() {
            @Override
            public void onUserLoaded(User updatedUser) {
                view.showSuccess("User updated successfully.");
            }

            @Override
            public void onError(String message) {
                view.showError(message);
            }
        });
    }
}
