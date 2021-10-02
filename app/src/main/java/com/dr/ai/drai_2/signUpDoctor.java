package com.dr.ai.drai_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class signUpDoctor extends AppCompatActivity {
    private Button signUpDoctors;
    Button btn;
    ImageView imageView;
    int SELECT_IMAGE_CODE=1;
    EditText bName, bEmail, bPassword, besPassword, bCity, bGender, bId, bPhone, bIban, breIban;
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_doctor);
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
                Intent intent = new Intent(signUpDoctor.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpDoctor.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpDoctor.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpDoctor.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.SignUp);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpDoctor.this, signUpDoctor.class);
                startActivity(intent);
                return false;
            }
        });
        menuItem = mainNavMenu.findItem(R.id.SignIn);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpDoctor.this, signInDoctor.class);
                startActivity(intent);
                return false;
            }
        });
        menuItem = mainNavMenu.findItem(R.id.SignInP);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpDoctor.this, signInPage.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.SignUpP);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpDoctor.this, signUpPage.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.SignInA);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(signUpDoctor.this, signInAdmin.class);
                startActivity(intent);
                return false;
            }
        });

        btn = findViewById(R.id.btnImage);
        imageView = findViewById(R.id.imageView2);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);


            }
        });
        signUpDoctors = (Button) findViewById(R.id.signUpDoctors);
        signUpDoctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();

            }
        });
        bName = findViewById(R.id.dName);
        bEmail = findViewById(R.id.dEmail);
        bPassword = findViewById(R.id.dPassword);
        besPassword = findViewById(R.id.rePasswordd);
        bGender = findViewById(R.id.dGender);
        bPhone = findViewById(R.id.dPhone);
        bId = findViewById(R.id.dId);
        bCity = findViewById(R.id.dCity);
        bIban = findViewById(R.id.dIban);
        breIban= findViewById(R.id.reIban);


    }
    public void checkCredentials() {
        String dName = bName.getText().toString();
        String dPassword = bPassword.getText().toString();
        String dEmail = bEmail.getText().toString();
        String rePasswordd = besPassword.getText().toString();
        String dGender = bGender.getText().toString();
        String dCity = bCity.getText().toString();
        String dPhone = bPhone.getText().toString();
        String dId = bId.getText().toString();
        String dIban = bIban.getText().toString();
        String reIban = breIban.getText().toString();



        if (dName.isEmpty()){
            showError(bName, "Enter an Name!");

        }
        else if  (!dEmail.contains("@")) {

            showError(bEmail, "Enter a valid email!");
        }
        else if (dEmail.isEmpty()){
            showError(bEmail, "Enter an email!");

        }
        else if (dId.isEmpty()){
            showError(bId, "Enter an Id");
        }
        else if (dGender.isEmpty() || dGender.equals("Female") || dGender.equals("Male")){
            showError(bGender, "Enter 'Female' or 'Male'");
        }
        else if (dCity.isEmpty()){
            showError(bCity, "Enter a city");
        }

        else if (dPhone.isEmpty()){
            showError(bPhone, "Enter a phone number");
        }
        else if  (dPassword.isEmpty() || dPassword.length()<8){
            showError(bPassword,"Password must be 8 characters ");
        }
        else if  (rePasswordd.isEmpty() || !rePasswordd.equals(dPassword)){
            showError(besPassword,"Password does no match");
        }

        else{
            Intent intent = new Intent(this , doctorsScreen.class);
            startActivity(intent);        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            Uri uri = data.getData();
            imageView.setImageURI(uri);


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
