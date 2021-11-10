package com.dr.ai.drai_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class doctorsAccountManagement extends AppCompatActivity {

    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;

    private RadioButton pendingBtn;

    FragmentTransaction ft;
    pendingFragment frgPending;
    acceptedFragment frgAccepted;
    rejectedFragment frgRejected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_account_management);

        frgPending = new pendingFragment();
        frgAccepted = new acceptedFragment();
        frgRejected = new rejectedFragment();

        pendingBtn = findViewById(R.id.pendingBtn);
        pendingBtn.setChecked(true);
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.frameLayoutAdmin, frgPending).
                commit();

        // set listener
        ((RadioGroup)findViewById(R.id.radioGroupD)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ft = getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.pendingBtn:
                        ft.replace(R.id.frameLayoutAdmin, frgPending);
                        break;
                    case R.id.acceptedBtn:
                        ft.replace(R.id.frameLayoutAdmin, frgAccepted);
                        break;
                    case R.id.rejectedBtn:
                        ft.replace(R.id.frameLayoutAdmin, frgRejected);
                        break;
                }
                ft.commit();
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
                Intent intent = new Intent(doctorsAccountManagement.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsAccountManagement.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsAccountManagement.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsAccountManagement.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });

    }

    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }
}