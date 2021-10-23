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

public class doctorsPatientScreen extends AppCompatActivity {
    private Button trackPatientButton;
    private Button healthRecordBtn;
    private Button checkUpBtn;
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_patient_screen);
        BottomNavigationView bottomNavigationView = findViewById(R.id.footer);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        return true;
                    case R.id.goBack:
                        startActivity(new Intent(getApplicationContext(), doctorsScreen.class));
                        return true;
                }
                return false;
            }
        });
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
                Intent intent = new Intent(doctorsPatientScreen.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsPatientScreen.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsPatientScreen.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsPatientScreen.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.SignUp);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsPatientScreen.this, signUpDoctor.class);
                startActivity(intent);
                return false;
            }
        });
        menuItem = mainNavMenu.findItem(R.id.SignIn);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsPatientScreen.this, signInDoctor.class);
                startActivity(intent);
                return false;
            }
        });
        menuItem = mainNavMenu.findItem(R.id.SignInP);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsPatientScreen.this, signInPage.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.SignUpP);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsPatientScreen.this, signUpPage.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.SignInA);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsPatientScreen.this, signInAdmin.class);
                startActivity(intent);
                return false;
            }
        });
        trackPatientButton = findViewById(R.id.trackPatientButton);
        trackPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTrackPatienPage();
            }
        });

        healthRecordBtn = findViewById(R.id.healthRecordBtn);
        healthRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHealthRecordPage();
            }
        });

        checkUpBtn = findViewById(R.id.checkUpBtn);
        checkUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCheckUpPage();
            }
        });
    }
    public void openTrackPatienPage(){
        Intent intent = new Intent(this, patientTracking.class);
        startActivity(intent);
    }

    public void openHealthRecordPage(){
        Intent intent = new Intent(this, patientsHealthRecords.class);
        startActivity(intent);
    }

    public void openCheckUpPage(){
        Intent intent = new Intent(this, checkUpAppointmentsDoctor.class);
        startActivity(intent);
    }

    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }
}