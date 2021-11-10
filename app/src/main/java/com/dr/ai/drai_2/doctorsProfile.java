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
import android.widget.ImageView;
import android.widget.TextView;

import com.dr.ai.drai_2.db.DatabaseHandler;
import com.dr.ai.drai_2.util.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class doctorsProfile extends AppCompatActivity {
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;
    TextView textInputLayoutName, textInputLayoutEmail, textInputLayoutPassword, textInputLayoutCity1, textInputLayoutID, textInputLayoutGender, textInputLayoutPhoneNo, textInputLayoutIban;
    ImageView imageView2;
    DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_profile);
        handler = new DatabaseHandler(this);

        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        textInputLayoutCity1 = findViewById(R.id.textInputLayoutCity1);
        textInputLayoutID = findViewById(R.id.textInputLayoutID);
        textInputLayoutGender = findViewById(R.id.textInputLayoutGender);
        textInputLayoutPhoneNo = findViewById(R.id.textInputLayoutPhoneNo);
        textInputLayoutIban = findViewById(R.id.textInputLayoutIban);
        imageView2 = findViewById(R.id.imageView2);
        loadProfileData();

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
                Intent intent = new Intent(doctorsProfile.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsProfile.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsProfile.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(doctorsProfile.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });

    }

    private void loadProfileData() {
        textInputLayoutName.setText(signInPage.loggedUser.getName());
        textInputLayoutEmail.setText(signInPage.loggedUser.getEmail());
        textInputLayoutPassword.setText(signInPage.loggedUser.getPassword());
        textInputLayoutCity1.setText(signInPage.loggedUser.getCity());
        textInputLayoutID.setText(signInPage.loggedUser.getPersonal_id());
        textInputLayoutGender.setText(signInPage.loggedUser.getGender());
        textInputLayoutPhoneNo.setText(signInPage.loggedUser.getPhone());
        textInputLayoutIban.setText(signInPage.loggedUser.getIban());
        imageView2.post(new Runnable() {
            @Override
            public void run() {
                imageView2.setImageBitmap(Utils.getImage(signInPage.loggedUser.getCertificate()));
            }
        });
    }

    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }

}