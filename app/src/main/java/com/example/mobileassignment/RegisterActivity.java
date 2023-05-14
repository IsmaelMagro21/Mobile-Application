package com.example.mobileassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class RegisterActivity extends AppCompatActivity {

    //declaration of variables

    EditText username, password, repeatpassword;
    Button login, register;
    DBHelper DB;

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

        DB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repeatpass = repeatpassword.getText().toString();

// If any of the fields are empty, it displays a Toast message prompting the user to fill in all the fields

                if(user.equals("")||pass.equals("")||repeatpass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();

// If all fields are filled, it checks if the password and repeat password fields match

                else {
                    if(pass.equals(repeatpass)) {
                        Boolean checkuser = DB.checkusername(user);

// If the username does not exist, it calls the insertData method from the DB instance to insert the new user's credentials into the database

                        if(checkuser==false) {
                            Boolean insert = DB.insertData(user, pass);

// If the insertion is successful, it displays a Toast message indicating that registration was successful and starts a new MainActivity

                            if(insert==true) {
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else{
                                // If the insertion is unsuccessful, it displays a Toast message indicating that registration was unsuccessful

                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        // If the username already exists, it displays a Toast message indicating that the user already exists and prompts the user to sign in

                        else{
                            Toast.makeText(RegisterActivity.this, "User already exists, please sign in.", Toast.LENGTH_SHORT).show();
                        }
                        // If the passwords do not match, it displays a Toast message prompting the user to ensure that the passwords match
                    }else{
                        Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            //When the register button is clicked, a new intent is created which launches the login activity class
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

        }
// Method for the login button to check whether the fields for username and password are empty
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