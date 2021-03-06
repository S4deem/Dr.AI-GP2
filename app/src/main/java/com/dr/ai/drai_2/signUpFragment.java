package com.dr.ai.drai_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.dr.ai.drai_2.db.DatabaseHandler;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link signUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class signUpFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    String[] options;
    Spinner spinner;
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

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputCPassword;
    private TextInputLayout textInputPhone;
    private TextInputLayout textInputID;
    private TextInputLayout textInputName;
    private RadioGroup genderRG;
    Button signUpP;
    String nameInput, cityInput, idInput, phoneInput, passwordInput, emailInput, selectedGender = "Male";
    DatabaseHandler handler;
    ArrayAdapter<CharSequence> adapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public signUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment signUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static signUpFragment newInstance(String param1, String param2) {
        signUpFragment fragment = new signUpFragment();
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        handler = new DatabaseHandler(requireActivity());

        spinner = (Spinner) view.findViewById(R.id.spinner);
        signUpP = view.findViewById(R.id.signUpP);
        // Creating ArrayAdapter using the string array and default spinner layout
        adapter = ArrayAdapter.createFromResource(
                getActivity().getBaseContext(),
                R.array.cities,
                android.R.layout.simple_spinner_item);
        // Specify layout to be used when list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Applying the adapter to our spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        options = signUpFragment.this.getResources().getStringArray(R.array.cities);


        // userTypeRG = view.findViewById(R.id.radioGroupA);
        genderRG = view.findViewById(R.id.radioGroup);

        textInputEmail = view.findViewById(R.id.textInputLayoutEmail);
        textInputPassword = view.findViewById(R.id.textInputLayoutPassword);
        textInputCPassword = view.findViewById(R.id.textInputLayoutCPassword);
        textInputPhone = view.findViewById(R.id.textInputLayoutPhoneNo);
        textInputID = view.findViewById(R.id.textInputLayoutID);
        textInputName = view.findViewById(R.id.textInputLayoutName);
        // textInputCity = findViewById(R.id.textInputLayoutCity);


        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == view.findViewById(R.id.maleRadioBtn).getId()) {
                    selectedGender = "Male";
                } else {
                    selectedGender = "Female";
                }
            }
        });

        return view;
    }

    private boolean validateEmail() {
        emailInput = textInputEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.getEditText().setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.getEditText().setError("Please enter a valid email address");
            return false;

        } else {
            textInputEmail.getEditText().setError(null);
            textInputEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        passwordInput = textInputPassword.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            textInputPassword.getEditText().setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.getEditText().setError("Password is weak");
            return false;
        } else {
            textInputPassword.getEditText().setError(null);
            textInputPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateCPassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        String passwordCInput = textInputCPassword.getEditText().getText().toString().trim();
        if (!passwordCInput.equals(passwordInput)) {
            textInputCPassword.getEditText().setError("Password isn't correct");
            return false;
        } else {
            textInputCPassword.getEditText().setError(null);
            textInputCPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhone() {
        phoneInput = textInputPhone.getEditText().getText().toString().trim();
        if (phoneInput.isEmpty()) {
            textInputPhone.getEditText().setError("Field can't be empty");
            return false;
        } else if (!PHONE_PATTERN.matcher(phoneInput).matches()) {
            textInputPhone.getEditText().setError("Make sure you entered the correct number");
            return false;
        } else {
            textInputPhone.getEditText().setError(null);
            textInputPhone.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateID() {
        idInput = textInputID.getEditText().getText().toString().trim();
        if (idInput.isEmpty()) {
            textInputID.getEditText().setError("Field can't be empty");
            return false;
        } else if (!ID_PATTERN.matcher(idInput).matches()) {
            textInputID.getEditText().setError("Make sure you entered the correct number");
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
            textInputName.getEditText().setError("Field can't be empty");
            return false;
        } else if (!NAME_PATTERN.matcher(nameInput).matches()) {
            textInputName.getEditText().setError("Make sure you entered the correct name");
            return false;
        } else {
            textInputName.setError(null);
            textInputName.setErrorEnabled(false);
            return true;
        }

    }

    /*private boolean validateCity() {
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

    }*/

    private boolean validateCity() {
        if (cityInput != null ) {
            return true;
        } else {
            Toast.makeText(getActivity(), "Choose a city!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void confirmInput() {
        if (!validateEmail() || !validatePassword() || !validateCPassword() || !validatePhone() || !validateID() || !validateName() || !validateCity()) {
            return;
        } else {
            if(handler.patientRegister(nameInput, emailInput, idInput, selectedGender, cityInput, phoneInput, passwordInput)){
                //Todo: navigate to login
                Log.e("DB","Register Success");
                startActivity(new Intent(requireActivity(), signInPage.class));
            }else {
                Log.e("DB","User Already Exist");
            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        signUpP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmInput();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cityInput = spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}