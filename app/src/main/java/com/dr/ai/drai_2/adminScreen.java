package com.dr.ai.drai_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class adminScreen extends AppCompatActivity {


    private Button doctorsManagementButton;
    private Button patientsManagementButton;
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);

        drawer_layout = findViewById(R.id.drawer_layout);
        mainNavView = findViewById(R.id.main_nav_view);
        mainNavView.setItemIconTintList(null);
        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuButton();
            }
        });

        mainNavMenu = mainNavView.getMenu();
        menuItem = mainNavMenu.findItem(R.id.Home);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(adminScreen.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(adminScreen.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(adminScreen.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(adminScreen.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });
        doctorsManagementButton = findViewById(R.id.doctorsManagementButton);
        doctorsManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDoctorsManagementPage();
            }
        });

        patientsManagementButton = findViewById(R.id.patientsManagementButton);
        patientsManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPatientsManagementPage();
            }
        });

    }
    public void openDoctorsManagementPage(){
        Intent intent = new Intent(this, doctorsAccountManagement.class);
        startActivity(intent);
    }
    public void openPatientsManagementPage(){
        Intent intent = new Intent(this, patientsAccountManagment.class);
        startActivity(intent);
    }
    
    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }
}