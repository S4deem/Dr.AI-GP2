package com.dr.ai.drai_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.dr.ai.drai_2.db.DatabaseHandler;
import com.dr.ai.drai_2.model.User;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class viewHealthRecord extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] options;
    Spinner patientSpinner;

  //  private Spinner spinnerTextSize;
    private Button viewBtn;
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;
    RecyclerView recDiagnoses;
    RecyclerView.Adapter adapter;
    List<pdRecycler> pdRecyclers = new ArrayList<>();
    String [] doctorNameItems;
    DatabaseHandler handler;
    User selectedPatient;
    List<User> patientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_health_record);
        handler = new DatabaseHandler(this);

        patientSpinner = findViewById(R.id.doctorSpinner);
        recDiagnoses = findViewById(R.id.recDiagnoses);
        patientList = handler.getAllPatients();
        doctorNameItems = new String[patientList.size()];
        for (int i = 0; i < patientList.size(); i++) {
            doctorNameItems[i] = patientList.get(i).getName();
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, doctorNameItems);
        // Specify layout to be used when list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Applying the adapter to our spinner
        patientSpinner.setAdapter(adapter);
        patientSpinner.setOnItemSelectedListener(this);


        options = viewHealthRecord.this.getResources().getStringArray(R.array.Patient_Id);

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
                Intent intent = new Intent(viewHealthRecord.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(viewHealthRecord.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(viewHealthRecord.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(viewHealthRecord.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });
    }

    private void loadDiagnoses(String id) {
        pdRecyclers = handler.getAllDiagnoses(id);
        adapter = new pdRecyclerAdapter(this, pdRecyclers);
        recDiagnoses.setAdapter(adapter);
    }

    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedPatient = patientList.get(position);
        loadDiagnoses(selectedPatient.getId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void openViewPage(View view) {
        Intent intent = new Intent(this, pdPatient.class);
        startActivity(intent);
    }
}