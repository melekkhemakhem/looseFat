package com.example.loosefatfast.presenter;

import com.example.loosefatfast.utils.FirebaseUtils;
import com.example.loosefatfast.view.LoginActivity;
import com.example.loosefatfast.model.UserRepository;
import com.example.loosefatfast.view.LoginActivity;

public class LoginPresenter {
    private LoginActivity view;
    private UserRepository userRepository;

    public LoginPresenter(LoginActivity view) {
        this.view = view;
        this.userRepository = new UserRepository();
    }



    public void loginUser(String email, String password) {
        // Ici, vous pouvez utiliser Firebase Auth pour connecter l'utilisateur
        FirebaseUtils.getFirebaseAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        view.onLoginSuccess();
                    } else {
                        view.onLoginFailure(task.getException().getMessage());
                    }
                });
    }


}
