package edu.gatech.score.orphanconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        BottomNavigationView buttonNavigationView = findViewById(R.id.navigation);
        buttonNavigationView.getMenu().setGroupCheckable(0, false, true);
        buttonNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(ResetPasswordActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                case R.id.navigation_account:
                    return true;
                case R.id.navigation_donation:
                    startActivity(new Intent(ResetPasswordActivity.this, My_Donation.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    return true;
                case R.id.navigation_map:
                    return true;
            }
            return false;
        });

        Button resetPassword = findViewById(R.id.reset_password_activity_button);
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText newPassword = findViewById(R.id.reset_password_activity_input_new_password);
                EditText confirmNewPassword = findViewById(R.id.reset_password_activity_confirm_new_password);

                String a = newPassword.toString();
                String b = confirmNewPassword.toString();

                if (a.isEmpty() || b.isEmpty()){
                    Toast.makeText(view.getContext(), "You need to input new password or confirm new password.", Toast.LENGTH_SHORT).show();
                } else if (a.equals(b)) {
                    Toast.makeText(view.getContext(), "Your new password should be equal to confirm new password.", Toast.LENGTH_SHORT).show();
                } else if (a.length() < 6 || b.length() < 6) {
                    Toast.makeText(view.getContext(), "Your new password should longer than 6.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


}
