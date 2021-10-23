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

public class healthRecord extends AppCompatActivity {
    private Button previousButton;
    private Button comingButton;
    private Button diagnosisButton;
    private Button prescriptionButton;
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_record);
        BottomNavigationView bottomNavigationView = findViewById(R.id.footer);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        return true;
                    case R.id.goBack:
                        startActivity(new Intent(getApplicationContext(), patient.class));
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
                Intent intent = new Intent(healthRecord.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(healthRecord.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(healthRecord.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(healthRecord.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.SignUp);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(healthRecord.this, signUpDoctor.class);
                startActivity(intent);
                return false;
            }
        });
        menuItem = mainNavMenu.findItem(R.id.SignIn);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(healthRecord.this, signInDoctor.class);
                startActivity(intent);
                return false;
            }
        });
        menuItem = mainNavMenu.findItem(R.id.SignInP);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(healthRecord.this, signInPage.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.SignUpP);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(healthRecord.this, signUpPage.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.SignInA);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(healthRecord.this, signInAdmin.class);
                startActivity(intent);
                return false;
            }
        });

        previousButton = findViewById(R.id.previousButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openPreviousPage();
            }
        });

        comingButton = findViewById(R.id.comingButton);
        comingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openComingPage();
            }
        });

        diagnosisButton = findViewById(R.id.diagnosisButton);
        diagnosisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDiagnosisPage();
            }
        });

        prescriptionButton = findViewById(R.id.prescriptionButton);
        prescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openPrescriptionPage();
            }
        });
    }

    public void openPreviousPage() {
        Intent intent = new Intent(this, healthRecordPrevious.class);
        startActivity(intent);
    }
    public void openComingPage() {
        Intent intent = new Intent(this, healthRecordComing.class);
        startActivity(intent);
    }

    public void openDiagnosisPage() {
        Intent intent = new Intent(this, diagnosis.class);
        startActivity(intent);
    }

    public void openPrescriptionPage() {
        Intent intent = new Intent(this, prescription.class);
        startActivity(intent);
    }
    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }
}