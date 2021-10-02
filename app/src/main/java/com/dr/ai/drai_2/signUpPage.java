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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class signUpPage extends AppCompatActivity {
    private Button signUpP;
    TextView btn;
    EditText sName, sEmail, sPassword, resPassword, sCity, sGender, sId, sPhone;
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        BottomNavigationView bottomNavigationView = findViewById(R.id.footer);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                    case R.id.goBack:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
                Intent intent = new Intent(signUpPage.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpPage.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpPage.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpPage.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.SignUp);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpPage.this, signInDoctor.class);
                startActivity(intent);
                return false;
            }
        });
        menuItem = mainNavMenu.findItem(R.id.SignIn);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpPage.this, signInPage.class);
                startActivity(intent);
                return false;
            }
        });
        menuItem = mainNavMenu.findItem(R.id.SignInP);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpPage.this, signInPage.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.SignUpP);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpPage.this, signUpPage.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.SignInA);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpPage.this, signInPage.class);
                startActivity(intent);
                return false;
            }
        });
        btn = findViewById(R.id.textAlready);
        btn.setOnClickListener((view) -> {
            startActivity(new Intent(signUpPage.this, signInAdmin.class));
        });
        signUpP = (Button) findViewById(R.id.signUpP);
        signUpP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();

            }
        });
        sName = findViewById(R.id.pName);
        sEmail = findViewById(R.id.pEmail);
        sPassword = findViewById(R.id.pPassword);
        resPassword = findViewById(R.id.rePassword);
        sGender = findViewById(R.id.pGender);
        sPhone = findViewById(R.id.pPhone);
        sId = findViewById(R.id.pId);
        sCity = findViewById(R.id.pCity);


    }
    public void checkCredentials() {
        String pName = sName.getText().toString();
        String pPassword = sPassword.getText().toString();
        String pEmail = sEmail.getText().toString();
        String rePassword = resPassword.getText().toString();
        String pGender = sGender.getText().toString();
        String pCity = sCity.getText().toString();
        String pPhone = sPhone.getText().toString();
        String pId = sId.getText().toString();


        if (pName.isEmpty()){
            showError(sName, "Enter an Name!");

        }
        else if  (!pEmail.contains("@")) {

            showError(sEmail, "Enter a valid email!");
        }
        else if (pEmail.isEmpty()){
            showError(sEmail, "Enter an email!");

        }
        else if (pId.isEmpty()){
            showError(sId, "Enter an Id");
        }
        else if (pGender.isEmpty() || pGender.equals("Female") || pGender.equals("Male")){
            showError(sGender, "Enter 'Female' or 'Male'");
        }
        else if (pCity.isEmpty()){
            showError(sCity, "Enter a city");
        }

        else if (pPhone.isEmpty()){
            showError(sPhone, "Enter a phone number");
        }
        else if  (pPassword.isEmpty() || pPassword.length()<8){
            showError(sPassword,"Password must be 8 characters ");
        }
        else if  (rePassword.isEmpty() || !rePassword.equals(pPassword)){
            showError(resPassword,"Password does no match");
        }

        else{
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }
    private void showError(EditText e, String s) {
        e.setError(s);
        e.requestFocus();
    }
    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }
}