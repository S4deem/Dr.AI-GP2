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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class doctorsAppointments1 extends AppCompatActivity {
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Appointment> appointmentArrayList = new ArrayList<>();
    DatabaseHandler handler;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String today;
    String[] date;//"dd[0]-MM[1]-yyyy[2]"


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_appointments1);
        today = simpleDateFormat.format(Calendar.getInstance().getTime());
        date = today.split("-");
        handler = new DatabaseHandler(this);
        loadAppointments();
        recyclerView = findViewById(R.id.mRecyclerView);
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
                Intent intent = new Intent(doctorsAppointments1.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsAppointments1.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsAppointments1.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsAppointments1.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });

    }

    private void loadAppointments() {
        appointmentArrayList = handler.getDoctorAppointments(signInPage.loggedUser, date[0]+"-"+date[1]+"-"+date[2]);
        adapter = new doctorRecyclerAdapter(this, appointmentArrayList);
        recyclerView.setAdapter(adapter);
    }

    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }
}