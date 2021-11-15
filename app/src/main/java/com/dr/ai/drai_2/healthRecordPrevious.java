package com.dr.ai.drai_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class healthRecordPrevious extends AppCompatActivity {
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;

    List<Appointment> patientPRecyclers = new ArrayList<>();
    DatabaseHandler handler;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    String date;
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//    String today;
//    String[] date;//"dd[0]-MM[1]-yyyy[2]"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_record_previous);
//        today = simpleDateFormat.format(Calendar.getInstance().getTime());
//        date = today.split("-");
        handler = new DatabaseHandler(this);
        recyclerView = findViewById(R.id.mRecyclerView);
        if (getIntent().getStringExtra("date") != null){
            Log.e("Date Status","Arrived");
            date = getIntent().getStringExtra("date");
            loadAppointments();
        }else {
            Log.e("Date Status","Failed");
        }
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
                Intent intent = new Intent(healthRecordPrevious.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(healthRecordPrevious.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(healthRecordPrevious.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(healthRecordPrevious.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });

    }
    private void loadAppointments() {
        patientPRecyclers = handler.getPatientAppointments(signInPage.loggedUser, date);
        adapter = new doctorRecyclerAdapter(this, patientPRecyclers);
        recyclerView.setAdapter(adapter);
    }

    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }
}