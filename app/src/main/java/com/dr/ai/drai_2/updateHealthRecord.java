package com.dr.ai.drai_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class updateHealthRecord extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] options;
    Spinner patientSpinner;

    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;
    TextInputEditText diagnosisTIET, prescriptionTIET;
    Button saveBtn;
    String [] patientNameItems;
    DatabaseHandler handler;
    User selectedPatient;
    List<User> patientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_health_record);
        handler = new DatabaseHandler(this);
        diagnosisTIET = findViewById(R.id.diagnosisTIET);
        prescriptionTIET = findViewById(R.id.prescriptionTIET);
        saveBtn = findViewById(R.id.saveBtn);
        patientSpinner = findViewById(R.id.doctorSpinner);
        patientList = handler.getAllPatients();
        patientNameItems = new String[patientList.size()];
        for (int i = 0; i < patientList.size(); i++) {
            patientNameItems[i] = patientList.get(i).getName();
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, patientNameItems);
        // Specify layout to be used when list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Applying the adapter to our spinner
        patientSpinner.setAdapter(adapter);
        patientSpinner.setOnItemSelectedListener(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiagnoses();
            }
        });

        options = updateHealthRecord.this.getResources().getStringArray(R.array.Patient_Id);

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
                Intent intent = new Intent(updateHealthRecord.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.aboutUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(updateHealthRecord.this, aboutUs.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.Privacy);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(updateHealthRecord.this, security.class);
                startActivity(intent);
                return false;
            }
        });

        menuItem = mainNavMenu.findItem(R.id.ContactUs);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(updateHealthRecord.this, contactUs.class);
                startActivity(intent);
                return false;
            }
        });
    }

    private void saveDiagnoses() {
        if (handler.registerDiagnoses(String.valueOf(diagnosisTIET.getText()), String.valueOf(prescriptionTIET.getText()), signInPage.loggedUser.getId(), selectedPatient.getId())) {
            //Todo: navigate to repeat the process
        }else {
            Log.e("DB","Diagnoses Registration Failed");
        }
    }

    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedPatient = patientList.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}