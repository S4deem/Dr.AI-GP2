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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class patientsHealthRecords extends AppCompatActivity {
   private Button InsertBtn;
   private Button viewBtn;
   private Button checkUpBtn;

    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_health_records);

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
                Intent intent = new Intent(patientsHealthRecords.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(patientsHealthRecords.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(patientsHealthRecords.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(patientsHealthRecords.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });

        InsertBtn = findViewById(R.id.InsertBtn);
        InsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInsertPage();
            }
        });

        viewBtn = findViewById(R.id.viewBtn);
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewPage();
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

    public void openInsertPage(){
        Intent intent = new Intent(this, updateHealthRecord1.class);
        startActivity(intent);
    }

    public void openViewPage(){
        Intent intent = new Intent(this, viewHealthRecord.class);
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
