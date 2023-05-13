package com.example.mobileassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileassignment.DB.DBHelperLogin;


public class RegisterActivity extends AppCompatActivity {

    EditText username, password, repeatpassword;
    Button login, register;
    DBHelperLogin DB;

    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        username = (EditText)  findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repeatpassword = (EditText) findViewById(R.id.repeatpassword);
        register = (Button) findViewById(R.id.registerButton);
        login = (Button) findViewById(R.id.loginButton);

        //header = findViewById(R.id.loginText);
        DB = new DBHelperLogin(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repeatpass = repeatpassword.getText().toString();

                if(user.equals("")||pass.equals("")||repeatpass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();

                else {
                    if(pass.equals(repeatpass)) {
                        Boolean checkuser = DB.checkusername(user);

                        if(checkuser==false) {
                            Boolean insert = DB.insertData(user, pass);

                            if(insert==true) {
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "User already exists, please sign in.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

        }

    public void loginButton(View view){
        if(username.length() == 0 || password.length() == 0 ){
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }
        startActivity(new Intent(this, MainActivity.class));
    }
}