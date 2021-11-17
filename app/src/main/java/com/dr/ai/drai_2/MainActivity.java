package com.dr.ai.drai_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.dr.ai.drai_2.databinding.ActivityMainBinding;
import com.dr.ai.drai_2.db.DatabaseHandler;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        homeFragment mHomeFragment = new homeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayoutDrawer, mHomeFragment).commit();

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.ContactUs, R.id.aboutUs, R.id.Privacy).setDrawerLayout(drawerLayout).build();
        NavigationView navigationView = findViewById(R.id.main_nav_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);

                if (id == R.id.aboutUs) {
                    Fragment aboutUsFragment = new aboutUsFragment();
                    openFragment(aboutUsFragment);

                } else if (id == R.id.ContactUs) {
                    Fragment contactUsFragment = new contactUsFragment();
                    openFragment(contactUsFragment);
                } else if (id == R.id.Privacy) {
                    Fragment privacyFragment = new privacyFragment();
                    openFragment(privacyFragment);
                } else if (id == R.id.Home) {
                    Fragment homeFragment = new homeFragment();
                    openFragment(homeFragment);
                } else if (id == R.id.LogOut) {
                    DatabaseHandler handler = new DatabaseHandler(getApplicationContext());
                    handler.logout(signInPage.loggedUser);
                    signInPage.loggedUser = null;
                    Intent intent = new Intent(MainActivity.this, signInPage.class);
                    startActivity(intent);

                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutDrawer, fragment);
        fragmentTransaction.commit();

    }

    public void openSignInAdmin(View view) {
        Intent intent = new Intent(this, adminScreen.class);
        startActivity(intent);
    }

    public void openSignIn(View view) {
        Intent intent = new Intent(this, signInPage.class);
        startActivity(intent);
    }

    public void openSignUp(View view) {
        Intent intent = new Intent(this, signUpPage.class);
        startActivity(intent);
    }
}
