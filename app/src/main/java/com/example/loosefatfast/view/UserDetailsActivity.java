package com.example.loosefatfast.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.loosefatfast.R;
import com.example.loosefatfast.model.User;
import com.example.loosefatfast.presenter.UserDetailPresenter;

public class UserDetailsActivity extends AppCompatActivity {
    private UserDetailPresenter presenter;
    private EditText ageEditText, genderEditText, heightEditText, weightEditText, targetWeightEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        presenter = new UserDetailPresenter(this);

        ageEditText = findViewById(R.id.age);
        genderEditText = findViewById(R.id.gender);
        heightEditText = findViewById(R.id.height);
        weightEditText = findViewById(R.id.weight);
        targetWeightEditText = findViewById(R.id.targetWeight);
        submitButton = findViewById(R.id.submitButton);

        // handle submit button click
        submitButton.setOnClickListener(v -> {
            try {
                String ageStr = ageEditText.getText().toString().trim();
                String heightStr = heightEditText.getText().toString().trim();
                String weightStr = weightEditText.getText().toString().trim();
                String targetWeightStr = targetWeightEditText.getText().toString().trim();
                String gender = genderEditText.getText().toString().trim();

                if (ageStr.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty() || targetWeightStr.isEmpty() || gender.isEmpty()) {
                    Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_LONG).show();
                    return;
                }

                int age = Integer.parseInt(ageStr);
                double height = Double.parseDouble(heightStr);
                double weight = Double.parseDouble(weightStr);
                double targetWeight = Double.parseDouble(targetWeightStr);

                User user = new User(age, gender, height, weight, targetWeight);
                presenter.updateUserDetails(user);
                showSuccess("User details updated successfully.");
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter valid numbers.", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void displayUserDetails(User user) {
        ageEditText.setText(String.valueOf(user.getAge()));
        genderEditText.setText(user.getGender());
        heightEditText.setText(String.valueOf(user.getHeight()));
        weightEditText.setText(String.valueOf(user.getWeight()));
        targetWeightEditText.setText(String.valueOf(user.getTargetWeight()));
    }

    public void showSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra("userAge", ageEditText.getText().toString());
        intent.putExtra("userGender", genderEditText.getText().toString());
        intent.putExtra("userHeight", heightEditText.getText().toString());
        intent.putExtra("userWeight", weightEditText.getText().toString());
        intent.putExtra("userTargetWeight", targetWeightEditText.getText().toString());
        startActivity(intent);
    }

    public void showError(String errorMessage) {
        Toast.makeText(this, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
    }
}
