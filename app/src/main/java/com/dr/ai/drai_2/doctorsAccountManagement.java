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

import com.google.android.material.navigation.NavigationView;

public class doctorsAccountManagement extends AppCompatActivity {
    private Button doctorsForm;
    private Button doctorsForm1;
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;

    private Button next;

    private RadioGroup radioGroupD;

    private RadioButton pRadioButton;
    private RadioButton aRadioButton;
    private RadioButton rRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_account_management);

        radioGroupD=(RadioGroup)findViewById(R.id.radioGroupD);

        pRadioButton=(RadioButton)findViewById(R.id.pRadioBtn);
        aRadioButton=(RadioButton)findViewById(R.id.aRadioBtn);
        rRadioButton=(RadioButton)findViewById(R.id.rRadioBtn);
        next=(Button)findViewById(R.id.nextD);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pRadioButton.isChecked()){
                    Intent intent = new Intent(doctorsAccountManagement.this, pendingAccounts.class);
                    startActivity(intent);
                }
                else if(rRadioButton.isChecked()){
                    Intent intent = new Intent(doctorsAccountManagement.this, rejectedAccounts.class);
                    startActivity(intent);
                }
                else if(aRadioButton.isChecked()){
                    Intent intent = new Intent(doctorsAccountManagement.this, acceptedAccounts.class);
                    startActivity(intent);
                }
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