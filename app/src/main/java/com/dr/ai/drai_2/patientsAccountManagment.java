package com.dr.ai.drai_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dr.ai.drai_2.db.DatabaseHandler;
import com.dr.ai.drai_2.model.Appointment;
import com.dr.ai.drai_2.model.User;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class patientsAccountManagment extends AppCompatActivity {
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    DatabaseHandler handler;
     List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_account_managment);
        recyclerView = findViewById(R.id.patientRecyclerView);
        handler = new DatabaseHandler(this);
        loadPatients();
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
                Intent intent = new Intent(patientsAccountManagment.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(patientsAccountManagment.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(patientsAccountManagment.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(patientsAccountManagment.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });

    }

    private void loadPatients() {
        userList = handler.getAllPatients();
        adapter = new pManaRecyclerAdapter(this, userList);
        recyclerView.setAdapter(adapter);
    }

    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }
}