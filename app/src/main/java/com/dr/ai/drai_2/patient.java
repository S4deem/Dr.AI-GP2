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

public class patient extends AppCompatActivity {
    private Button appointmentSummaryButton;
    private Button healthRecordButton;
    private Button checkUpButton;
    private Button goBackButton;
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

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
                Intent intent = new Intent(patient.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(patient.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(patient.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(patient.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });

        appointmentSummaryButton = findViewById(R.id.appointmentSummaryButton);
        appointmentSummaryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                openAppointmentSummaryPage();
            }
        });

        healthRecordButton = findViewById(R.id.healthRecordButton);
        healthRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHealthRecordPage();
            }
        });
    }
    public void openAppointmentSummaryPage() {
        Intent intent = new Intent(this, pdPatient.class);
        startActivity(intent);
    }

    public void openHealthRecordPage() {
        Intent intent = new Intent(this, healthRecord.class);
        startActivity(intent);
    }

    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }
}