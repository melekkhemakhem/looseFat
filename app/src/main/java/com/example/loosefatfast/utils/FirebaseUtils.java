package com.example.loosefatfast.utils;


import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {
    private static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public static FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public static DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public static Task<String> createUserWithEmailAndPasword(String email, String password){
        return firebaseAuth.createUserWithEmailAndPassword(email,password).continueWithTask(task->{
            if(task.isSuccessful()){
                FirebaseUser user=task.getResult().getUser();
                return Tasks.forResult(user.getUid());
            }else{
                return Tasks.forException(task.getException());
            }
        });

    }
}
