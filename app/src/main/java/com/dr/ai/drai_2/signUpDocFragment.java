package com.dr.ai.drai_2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link signUpDocFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class signUpDocFragment extends Fragment implements AdapterView.OnItemSelectedListener {
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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public signUpDocFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment signUpDocFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static signUpDocFragment newInstance(String param1, String param2) {
        signUpDocFragment fragment = new signUpDocFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sign_up_doc, container, false);

        spinner = view.findViewById(R.id.spinner);
        // Creating ArrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity().getBaseContext(),
                R.array.cities,
                android.R.layout.simple_spinner_item);
        // Specify layout to be used when list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Applying the adapter to our spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        options = signUpDocFragment.this.getResources().getStringArray(R.array.cities);


        btnImage = view.findViewById(R.id.btnImage);
        imageView = view.findViewById(R.id.imageView2);
        btnImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);


            }
        });


        textInputEmail = view.findViewById(R.id.textInputLayoutEmail);
        textInputPassword = view.findViewById(R.id.textInputLayoutPassword);
        textInputCPassword = view.findViewById(R.id.textInputLayoutCPassword);
        textInputPhone = view.findViewById(R.id.textInputLayoutPhoneNo);
        textInputID = view.findViewById(R.id.textInputLayoutID);
        textInputName = view.findViewById(R.id.textInputLayoutName);
        //  textInputCity = findViewById(R.id.textInputLayoutCity);
        textInputIban = view.findViewById(R.id.textInputLayoutIban);

        return inflater.inflate(R.layout.fragment_sign_up_doc, container, false);
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
            Toast.makeText(getActivity(),"Choose a city!",Toast.LENGTH_SHORT).show();
            return true;
        }
    }





    public void confirmInput (View v) {
        if (!validateEmail() | !validatePassword() | validateCPassword() | validatePhone() | validateID() | validateName() | validateCity() | validateIban())  {
            return;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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
