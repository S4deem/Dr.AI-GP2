package com.dr.ai.drai_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.dr.ai.drai_2.db.DatabaseHandler;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class signUpPage extends FragmentActivity {
    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    public static final Pattern PHONE_PATTERN =
            Pattern.compile("[6-9][0-9]{11}");

    public static final Pattern ID_PATTERN =
            Pattern.compile("[0-9]{10}");

    public static final Pattern NAME_PATTERN =
            Pattern.compile("[a-zA-Z]");
    private RadioButton pRadioBtn, dRadioBtn;
    private RadioGroup radioGroupA;
    // private Button next;
    FragmentTransaction ft;
    signUpFragment frg1;
    signUpDocFragment frg2;

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputCPassword;
    private TextInputLayout textInputPhone;
    private TextInputLayout textInputID;
    private TextInputLayout textInputName;
    private TextInputLayout textInputCity;
    private RadioGroup userTypeRG, genderRG;
    private RadioButton pRadioButton;
    private RadioButton dRadioButton;


    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton, signUpBtn;
    private DrawerLayout drawer_layout;
    DatabaseHandler handler;
    String nameInput, cityInput, idInput, phoneInput, passwordInput, emailInput, selectedUsertype = "Patient", selectedGender = "Male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        userTypeRG = (RadioGroup) findViewById(R.id.radioGroupA);
        genderRG = (RadioGroup) findViewById(R.id.radioGroup);
        pRadioButton = (RadioButton) findViewById(R.id.pRadioBtn);
        dRadioButton = (RadioButton) findViewById(R.id.dRadioBtn);
        signUpBtn = (Button) findViewById(R.id.signUp);
        handler = new DatabaseHandler(this);
        frg1 = new signUpFragment();
        frg2 = new signUpDocFragment();
        pRadioBtn = findViewById(R.id.pRadioBtn);
        pRadioBtn.setChecked(true);
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.frameLayout, frg1).
                commit();

        // set listener
        ((RadioGroup)findViewById(R.id.radioGroupA)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        ft = getSupportFragmentManager().beginTransaction();
                        switch (checkedId) {
                            case R.id.pRadioBtn:
                                ft.replace(R.id.frameLayout, frg1);
                                break;
                            case R.id.dRadioBtn:
                                ft.replace(R.id.frameLayout, frg2);
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

        textInputEmail = findViewById(R.id.textInputLayoutEmail);
        textInputPassword = findViewById(R.id.textInputLayoutPassword);
        textInputCPassword = findViewById(R.id.textInputLayoutCPassword);
        textInputPhone = findViewById(R.id.textInputLayoutPhoneNo);
        textInputID = findViewById(R.id.textInputLayoutID);
        textInputName = findViewById(R.id.textInputLayoutName);
        textInputCity = findViewById(R.id.textInputLayoutCity);



        userTypeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == findViewById(R.id.pRadioBtn).getId()) {
                    selectedUsertype = "Patient";
                } else {
                    selectedUsertype = "Doctor";
                }
            }
        });

        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == findViewById(R.id.maleRadioBtn).getId()) {
                    selectedUsertype = "Male";
                } else {
                    selectedUsertype = "Female";
                }
            }
        });

    }

    private boolean validateEmail() {
        emailInput = textInputEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Please enter a valid email address");
            return false;

        } else {
            textInputEmail.setError(null);
            textInputEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        passwordInput = textInputPassword.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("Password is weak");
            return false;
        } else {
            textInputPassword.setError(null);
            textInputPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateCPassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        String passwordCInput = textInputCPassword.getEditText().getText().toString().trim();
        if (passwordCInput == passwordInput) {
            textInputCPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordCInput).matches()) {
            textInputCPassword.setError("Password is weak");
            return false;
        } else {
            textInputCPassword.setError(null);
            textInputCPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhone() {
        phoneInput = textInputPhone.getEditText().getText().toString().trim();
        if (phoneInput.isEmpty()) {
            textInputPhone.setError("Field can't be empty");
            return false;
        } else if (!PHONE_PATTERN.matcher(phoneInput).matches()) {
            textInputPhone.setError("Make sure you entered the correct number");
            return false;
        } else {
            textInputPhone.setError(null);
            textInputPhone.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateID() {
        idInput = textInputID.getEditText().getText().toString().trim();
        if (idInput.isEmpty()) {
            textInputID.setError("Field can't be empty");
            return false;
        } else if (!ID_PATTERN.matcher(idInput).matches()) {
            textInputID.setError("Make sure you entered the correct number");
            return false;
        } else {
            textInputID.setError(null);
            textInputID.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateName() {
        nameInput = textInputName.getEditText().getText().toString().trim();
        if (nameInput.isEmpty()) {
            textInputName.setError("Field can't be empty");
            return false;
        } else if (!NAME_PATTERN.matcher(nameInput).matches()) {
            textInputName.setError("Make sure you entered the correct name");
            return false;
        } else {
            textInputName.setError(null);
            textInputName.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateCity() {
        cityInput = textInputCity.getEditText().getText().toString().trim();
        if (cityInput.isEmpty()) {
            textInputCity.setError("Field can't be empty");
            return false;
        } else if (!NAME_PATTERN.matcher(cityInput).matches()) {
            textInputCity.setError("Make sure you entered the correct name");
            return false;
        } else {
            textInputCity.setError(null);
            textInputCity.setErrorEnabled(false);
            return true;
        }

    }

    private void confirmInput() {
        if (!validateEmail() | !validatePassword() | !validateCPassword() | !validatePhone() | !validateID() | !validateName() | !validateCity()) {
            return;
        } else {
            if (selectedUsertype.equals("Doctor")) {
//                handler.patientRegister(nameInput, emailInput, idInput, selectedGender, cityInput, phoneInput, passwordInput);
            } else {
                handler.patientRegister(nameInput, emailInput, idInput, selectedGender, cityInput, phoneInput, passwordInput);
            }
        }
    }

    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }
}