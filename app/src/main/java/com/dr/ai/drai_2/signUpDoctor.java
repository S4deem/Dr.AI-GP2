package com.dr.ai.drai_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import java.util.regex.Pattern;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;

public class signUpDoctor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] options;
    Spinner spinner;

    ImageView imageView;
    int SELECT_IMAGE_CODE=1;
    Button btnImage;

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
            Pattern.compile("^[A-Za-z]+$");

    public static final Pattern IBAN_PATTERN =
            Pattern.compile("([S][A][0-9]{22})");

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputCPassword;
    private TextInputLayout textInputPhone;
    private TextInputLayout textInputID;
    private TextInputLayout textInputName;
   // private TextInputLayout textInputCity;
    private TextInputLayout textInputIban;
    private RadioGroup radioGroupA;
    private RadioButton pRadioButton;
    private RadioButton dRadioButton;
    private Button next;
    private NavigationView mainNavView;
    private Menu mainNavMenu;
    private MenuItem menuItem;
    private Button menuButton;
    private DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_doctor);

        spinner = (Spinner) findViewById(R.id.spinner);
        // Creating ArrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities, android.R.layout.simple_spinner_item);
        // Specify layout to be used when list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Applying the adapter to our spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        options = signUpDoctor.this.getResources().getStringArray(R.array.cities);


        btnImage = findViewById(R.id.btnImage);
        imageView = findViewById(R.id.imageView2);
        btnImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);


            }
        });



        radioGroupA=findViewById(R.id.radioGroupA);
        pRadioButton=findViewById(R.id.pRadioBtn);
        dRadioButton=findViewById(R.id.dRadioBtn);
        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pRadioButton.isChecked()){
                    Intent intent = new Intent(signUpDoctor.this, signUpPage.class);
                    startActivity(intent);
                }
                else if(dRadioButton.isChecked()){
                    Intent intent = new Intent(signUpDoctor.this, signUpDoctor.class);
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



        textInputEmail = findViewById(R.id.textInputLayoutEmail);
        textInputPassword = findViewById(R.id.textInputLayoutPassword);
        textInputCPassword = findViewById(R.id.textInputLayoutCPassword);
        textInputPhone = findViewById(R.id.textInputLayoutPhoneNo);
        textInputID = findViewById(R.id.textInputLayoutID);
        textInputName = findViewById(R.id.textInputLayoutName);
      //  textInputCity = findViewById(R.id.textInputLayoutCity);
        textInputIban = findViewById(R.id.textInputLayoutIban);







    }
    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
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
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            textInputPassword.setError("Password is weak");
            return false;
        }
        else{
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
        }
        else if (!PASSWORD_PATTERN.matcher(passwordCInput).matches()){
            textInputCPassword.setError("Password is weak");
            return false;
        }
        else{
            textInputCPassword.setError(null);
            textInputCPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhone() {
        String phoneInput = textInputPhone.getEditText().getText().toString().trim();
        if (phoneInput.isEmpty()) {
            textInputPhone.setError("Field can't be empty");
            return false;
        }
        else if (!PHONE_PATTERN.matcher(phoneInput).matches()){
            textInputPhone.setError("Make sure you entered the correct number");
            return false;
        }
        else{
            textInputPhone.setError(null);
            textInputPhone.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateID() {
        String idInput = textInputID.getEditText().getText().toString().trim();
        if (idInput.isEmpty()) {
            textInputID.setError("Field can't be empty");
            return false;
        }
        else if (!ID_PATTERN.matcher(idInput).matches()){
            textInputID.setError("Make sure you entered the correct number");
            return false;
        }
        else{
            textInputID.setError(null);
            textInputID.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateName() {
        String nameInput = textInputName.getEditText().getText().toString().trim();
        if (nameInput.isEmpty()) {
            textInputName.setError("Field can't be empty");
            return false;
        }
        else if (!NAME_PATTERN.matcher(nameInput).matches()){
            textInputName.setError("Make sure you entered the correct name");
            return false;
        }
        else{
            textInputName.setError(null);
            textInputName.setErrorEnabled(false);
            return true;
        }

    }

    /*private boolean validateCity() {
        String cityInput = textInputCity.getEditText().getText().toString().trim();
        if (cityInput.isEmpty()) {
            textInputCity.setError("Field can't be empty");
            return false;
        }
        else if (!NAME_PATTERN.matcher(cityInput).matches()){
            textInputCity.setError("Make sure you entered the correct name");
            return false;
        }
        else{
            textInputCity.setError(null);
            textInputCity.setErrorEnabled(false);
            return true;
        }

    }*/

    private boolean validateIban() {
        String ibanInput = textInputIban.getEditText().getText().toString().trim();
        if (ibanInput.isEmpty()) {
            textInputIban.setError("Field can't be empty");
            return false;
        }
        else if (!IBAN_PATTERN.matcher(ibanInput).matches()){
            textInputIban.setError("Make the Iban starts with 'SA'");
            return false;
        }
        else{
            textInputIban.setError(null);
            textInputIban.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateCity(){
        String spinnerValidate= null;
        if(spinner != null && spinner.getSelectedItem() !=null ) {
            spinnerValidate = (String)spinner.getSelectedItem();
            return false;
        } else  {
            Toast.makeText(this, "Choose a city", Toast.LENGTH_SHORT).show();
            return true;
        }
    }





    public void confirmInput (View v) {
        if (!validateEmail() | !validatePassword() | validateCPassword() | validatePhone() | validateID() | validateName() | validateCity() | validateIban())  {
            return;
        }
    }

    private void menuButton() {

        drawer_layout.openDrawer(GravityCompat.START);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            Uri uri = data.getData();
            imageView.setImageURI(uri);


        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      //  Toast.makeText(this, " You select >> "+options[position], Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}