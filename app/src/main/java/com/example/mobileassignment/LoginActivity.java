package com.example.mobileassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    //declaration of variables
    EditText username, password;
    Button login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        login = (Button) findViewById(R.id.loginButton1);
        DB = new DBHelper(this);

        //when the login button is clicked, it retrieves the values entered in the username and password fields
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                //if the username or password field is empty, it prompts the user to enter all the fields
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

                else{
                    //checks the user credentials in the database to see if the user acutally exists
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    //if it exists, print and complete sign in
                    if(checkuserpass==true) {
                        Toast.makeText(LoginActivity.this, "Sign in Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        //if it does not exist, prompt user
                        Toast.makeText(LoginActivity.this, "Invalid Credentials ", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}