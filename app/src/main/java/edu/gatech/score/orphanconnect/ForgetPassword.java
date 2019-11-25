package edu.gatech.score.orphanconnect;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class ForgetPassword extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

    }

    public void sentVertificationDialog(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Email Vertification Sent, Please Check your Email");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void return_to_sign_in(View view) {
        Intent intent = new Intent(ForgetPassword.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void resentVertificationDialog(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Email Vertification Resent, Please Check your Email");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void signUp(View view) {
        Intent intent = new Intent(ForgetPassword.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
