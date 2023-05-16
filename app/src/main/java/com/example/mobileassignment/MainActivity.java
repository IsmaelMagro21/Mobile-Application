package com.example.mobileassignment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mobileassignment.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    //declaration of variable
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //The BottomNavigationView with the id nav_view is retrieved from the layout using findViewById
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //An AppBarConfiguration is created using AppBarConfiguration. The destinations are R.id.navigation_dashboard and R.id.navigation_mode.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_dashboard, R.id.navigation_mode).build();
        //The NavController is obtained using Navigation.findNavController by passing the activity and the id of the NavHostFragment from the layout.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //This connects the navigation controller with the bottom navigation view, allowing the user to navigate between destinations by selecting items in the bottom navigation bar.
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}