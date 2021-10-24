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

public class patientScreen extends AppCompatActivity {

    private Button patientButton;
    private Button virtualTButton;
    private Button profileButton;
    private Button appointmentButton;
    private Button goBackButton;
    private Button reminderButton;
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_screen);
        BottomNavigationView bottomNavigationView = findViewById(R.id.footer);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        return true;
                    case R.id.goBack:
                        startActivity(new Intent(getApplicationContext(), signInPage.class));
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
                Intent intent = new Intent(patientScreen.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(patientScreen.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(patientScreen.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(patientScreen.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });

        patientButton = findViewById(R.id.patientButton);
        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openSignInPage();
            }
        });
        virtualTButton = findViewById(R.id.virtualTButton);
        virtualTButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openVirtualTPage();
            }
        });

        patientButton = findViewById(R.id.patientButton);
        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openSignInPage();
            }
        });
        profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openProfilePage();
            }
        });

        appointmentButton = findViewById(R.id.appointmentButton);
        appointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openAppointmentsPage();
            }
        });

        goBackButton = findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goBackButton();
            }
        });

        reminderButton = findViewById(R.id.reminderButton);
        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openReminderPage();
            }
        });
    }

    public void openProfilePage() {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }

    public void openVirtualTPage() {
        Intent intent = new Intent(this, virtualTherapist.class);
        startActivity(intent);
    }

    public void openSignInPage() {
        Intent intent = new Intent(this, patient.class);
        startActivity(intent);
    }

    public void openAppointmentsPage() {
        Intent intent = new Intent(this, patientAppointments.class);
        startActivity(intent);
    }

    public void goBackButton() {
        Intent intent = new Intent(this, signInPage.class);
        startActivity(intent);
    }

    public void openReminderPage() {
        Intent intent = new Intent(this, patientReminder.class);
        startActivity(intent);
    }

    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }
}