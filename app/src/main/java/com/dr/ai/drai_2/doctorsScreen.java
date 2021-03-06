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

public class doctorsScreen extends AppCompatActivity {
    private Button chatBtn;
    private Button patientBtn;
    private Button doctorProfileButton;
    private Button doctorAppointmentBtn;
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_screen);

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
                Intent intent = new Intent(doctorsScreen.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsScreen.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsScreen.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsScreen.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });


        chatBtn = findViewById(R.id.chatBtn);
        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChatPage();
            }
        });
        doctorProfileButton = findViewById(R.id.doctorProfileButton);
        doctorProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDoctorProfilePage();
            }
        });
        doctorAppointmentBtn = findViewById(R.id.doctorAppointmentBtn);
        doctorAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDoctorAppointmentPage();
            }
        });

        patientBtn = findViewById(R.id.patientBtn);
        patientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPatientPage();
            }
        });
    }
    public void openChatPage(){
        Intent intent = new Intent(this, patientTracking.class);
        startActivity(intent);
    }
    public void openDoctorProfilePage(){
        Intent intent = new Intent(this, doctorsProfile.class);
        startActivity(intent);
    }
    public void openDoctorAppointmentPage(){
        Intent intent = new Intent(this, doctorsAppointments.class);
        startActivity(intent);
    }

    private void menuButton() {
        drawer_layout.openDrawer(GravityCompat.START);
    }

    private void openPatientPage() {
        Intent intent = new Intent(this, patientsHealthRecords.class);
        startActivity(intent);    }
}