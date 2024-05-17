package com.example.loosefatfast.model;

import com.example.loosefatfast.utils.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class UserRepository {

    public interface UserCallback {
        void onUserLoaded(User user);
        void onError(String message);
    }

    public void getUser(String userId, UserCallback callback) {
        // Exemple d'utilisation de Firebase pour obtenir des données utilisateur
        FirebaseUtils.getDatabaseReference()
                .child("users")
                .child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        if (user != null) {
                            callback.onUserLoaded(user);
                        } else {
                            callback.onError("User not found");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        callback.onError(databaseError.getMessage());
                    }
                });
    }

    public void saveUser(User user, UserCallback callback) {
        FirebaseUtils.getDatabaseReference()
                .child("users")
                .child(user.getUserId())
                .setValue(user, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            callback.onError(databaseError.getMessage());
                        } else {
                            callback.onUserLoaded(user);
                        }
                    }
                });
    }
}
