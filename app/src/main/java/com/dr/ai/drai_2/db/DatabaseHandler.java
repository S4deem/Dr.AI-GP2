package com.dr.ai.drai_2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dr.ai.drai_2.model.Appointment;
import com.dr.ai.drai_2.model.User;
import com.dr.ai.drai_2.pdRecycler;
import com.dr.ai.drai_2.util.RSAUtil;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    //region Database Configuration
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    //endregion

    //region Users Table
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PERSONAL_ID = "personal_id";
    private static final String KEY_CERTIFICATE = "certificate";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_CITY = "city";
    private static final String KEY_PHONE_NO = "phone";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IBAN = "iban";
    private static final String KEY_DOCTOR_APPROVED = "approved";//yes(for approved) OR no(for rejected) OR pending(for registered)
    private static final String KEY_USER_TYPE = "type";//Doctor OR Patient
    private static final String KEY_USER_STATUS = "status";//LoggedIn OR LoggedOut
    //endregion

    //region Appointments Table
    private static final String TABLE_APPOINTMENTS = "appointments";
    private static final String KEY_APPOINTMENT_ID = "id";
    private static final String KEY_APPOINTMENT_TYPE = "type";
    private static final String KEY_APPOINTMENT_DATE = "app_date";
    private static final String KEY_APPOINTMENT_TIME = "app_time";
    private static final String KEY_APPOINTMENT_DOCTOR_ID = "doctor_id";
    private static final String KEY_APPOINTMENT_PATIENT_ID = "patient_id";
    //endregion

    //region Records Table
    private static final String TABLE_RECORD = "records";
    private static final String KEY_RECORD_ID = "id";
    private static final String KEY_RECORD_DIAGNOSES = "diagnoses";
    private static final String KEY_RECORD_PRESCRIPTION = "prescription";
    private static final String KEY_RECORD_PATIENT_ID = "patient_id";
    private static final String KEY_RECORD_DOCTOR_ID = "doctor_id";
    //endregion

    RSAUtil rsaUtil;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        rsaUtil = new RSAUtil();
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PERSONAL_ID + " TEXT,"
                + KEY_CERTIFICATE + " BLOB,"
                + KEY_GENDER + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_PHONE_NO + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_IBAN + " TEXT,"
                + KEY_DOCTOR_APPROVED + " TEXT,"
                + KEY_USER_TYPE + " TEXT,"
                + KEY_USER_STATUS + " TEXT" + ")";

        String CREATE_APPOINTMENTS_TABLE = "CREATE TABLE " + TABLE_APPOINTMENTS + "("
                + KEY_APPOINTMENT_ID + " INTEGER PRIMARY KEY,"
                + KEY_APPOINTMENT_TYPE + " TEXT,"
                + KEY_APPOINTMENT_DATE + " TEXT,"
                + KEY_APPOINTMENT_TIME + " TEXT,"
                + KEY_APPOINTMENT_DOCTOR_ID + " TEXT,"
                + KEY_APPOINTMENT_PATIENT_ID + " TEXT" + ")";

        String CREATE_RECORD_TABLE = "CREATE TABLE " + TABLE_RECORD + "("
                + KEY_RECORD_ID + " INTEGER PRIMARY KEY,"
                + KEY_RECORD_DIAGNOSES + " TEXT,"
                + KEY_RECORD_PRESCRIPTION + " TEXT,"
                + KEY_RECORD_DOCTOR_ID + " TEXT,"
                + KEY_RECORD_PATIENT_ID + " TEXT" + ")";

        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_APPOINTMENTS_TABLE);
        db.execSQL(CREATE_RECORD_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORD);

        // Create tables again
        onCreate(db);
    }

    public Boolean doctorRegister(String name, String email, String personal_id, byte[] img, String gender, String city, String phone, String password, String iban) {

        if (!userExist(email)) {
            name = RSAUtil.encryptData(name);
            email = RSAUtil.encryptData(email);
            personal_id = RSAUtil.encryptData(personal_id);
            gender = RSAUtil.encryptData(gender);
            city = RSAUtil.encryptData(city);
            phone = RSAUtil.encryptData(phone);
            password = RSAUtil.encryptData(password);
            iban = RSAUtil.encryptData(iban);
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, name);
            values.put(KEY_EMAIL, email);
            values.put(KEY_PERSONAL_ID, personal_id);
            values.put(KEY_CERTIFICATE, img);
            values.put(KEY_GENDER, gender);
            values.put(KEY_CITY, city);
            values.put(KEY_PHONE_NO, phone);
            values.put(KEY_PASSWORD, password);
            values.put(KEY_IBAN, iban);
            values.put(KEY_DOCTOR_APPROVED, "pending");
            values.put(KEY_USER_TYPE, "Doctor");
            values.put(KEY_USER_STATUS, "LoggedOut");

            // Inserting Row
            db.insert(TABLE_USERS, null, values);
            //2nd argument is String containing nullColumnHack
            db.close(); // Closing database connection
            return true;
        } else {
            return false;
        }
    }

    public Boolean patientRegister(String name, String email, String personal_id, String gender, String city, String phone, String password) {

        if (!userExist(email)) {
            name = RSAUtil.encryptData(name);
            email = RSAUtil.encryptData(email);
            personal_id = RSAUtil.encryptData(personal_id);
            gender = RSAUtil.encryptData(gender);
            city = RSAUtil.encryptData(city);
            phone = RSAUtil.encryptData(phone);
            password = RSAUtil.encryptData(password);
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, name);
            values.put(KEY_EMAIL, email);
            values.put(KEY_PERSONAL_ID, personal_id);
            values.put(KEY_GENDER, gender);
            values.put(KEY_CITY, city);
            values.put(KEY_PHONE_NO, phone);
            values.put(KEY_PASSWORD, password);
            values.put(KEY_USER_TYPE, "Patient");
            values.put(KEY_USER_STATUS, "LoggedOut");

            // Inserting Row
            db.insert(TABLE_USERS, null, values);
            //2nd argument is String containing nullColumnHack
            db.close(); // Closing database connection
            return true;
        } else {
            return false;
        }
    }

    public User login(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + ";", null);
        if (cursor.moveToFirst()) {
            do {
                if (RSAUtil.decryptData(cursor.getString(2)).equals(email) && RSAUtil.decryptData(cursor.getString(8)).equals(password)) {
                    User user = new User(cursor.getString(0),
                            RSAUtil.decryptData(cursor.getString(1)),
                            RSAUtil.decryptData(cursor.getString(2)),
                            RSAUtil.decryptData(cursor.getString(3)),
                            cursor.getBlob(4),
                            RSAUtil.decryptData(cursor.getString(5)),
                            RSAUtil.decryptData(cursor.getString(6)),
                            RSAUtil.decryptData(cursor.getString(7)),
                            RSAUtil.decryptData(cursor.getString(8)),
                            RSAUtil.decryptData(cursor.getString(9)),
                            RSAUtil.decryptData(cursor.getString(10)),
                            RSAUtil.decryptData(cursor.getString(11)),
                            RSAUtil.decryptData(cursor.getString(12)));

                    if (updateUserStatus(user, "LoggedIn") == 1) {
                        return user;
                    } else {
                        return null;
                    }
                }
            }while (cursor.moveToNext());
        } else {
            return null;
        }
        return null;
    }

    public Boolean logout(User user) {

        return updateUserStatus(user, "LoggedOut") == 1;
    }

    public int confirmDoctorApprobation(User user) {
        return updateDoctorApprobation(user, "yes");
    }

    public int rejectDoctorApprobation(User user) {
        return updateDoctorApprobation(user, "no");
    }

    public List<User> getAllDoctors() {
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_USER_TYPE + " =  'Doctor' ;", null)) {
            if (cursor.moveToFirst()) {
                do {
                    User user = new User(cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getBlob(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8),
                            cursor.getString(9),
                            cursor.getString(10),
                            cursor.getString(11),
                            cursor.getString(12));
                    // Adding contact to list
                    list.add(user);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public List<User> getAllPendingDoctors() {
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_USER_TYPE + " =  'Doctor' AND " + KEY_DOCTOR_APPROVED + " = 'pending';", null)) {
            if (cursor.moveToFirst()) {
                do {
                    User user = new User(cursor.getString(0),
                            RSAUtil.decryptData(cursor.getString(1)),
                            RSAUtil.decryptData(cursor.getString(2)),
                            RSAUtil.decryptData(cursor.getString(3)),
                            cursor.getBlob(4),
                            RSAUtil.decryptData(cursor.getString(5)),
                            RSAUtil.decryptData(cursor.getString(6)),
                            RSAUtil.decryptData(cursor.getString(7)),
                            RSAUtil.decryptData(cursor.getString(8)),
                            RSAUtil.decryptData(cursor.getString(9)),
                            RSAUtil.decryptData(cursor.getString(10)),
                            RSAUtil.decryptData(cursor.getString(11)),
                            RSAUtil.decryptData(cursor.getString(12)));
                    // Adding contact to list
                    list.add(user);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public List<User> getAllAcceptedDoctors() {
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_USER_TYPE + " =  'Doctor' AND " + KEY_DOCTOR_APPROVED + " = 'yes';", null)) {
            if (cursor.moveToFirst()) {
                do {
                    User user = new User(cursor.getString(0),
                            RSAUtil.decryptData(cursor.getString(1)),
                            RSAUtil.decryptData(cursor.getString(2)),
                            RSAUtil.decryptData(cursor.getString(3)),
                            cursor.getBlob(4),
                            RSAUtil.decryptData(cursor.getString(5)),
                            RSAUtil.decryptData(cursor.getString(6)),
                            RSAUtil.decryptData(cursor.getString(7)),
                            RSAUtil.decryptData(cursor.getString(8)),
                            RSAUtil.decryptData(cursor.getString(9)),
                            RSAUtil.decryptData(cursor.getString(10)),
                            RSAUtil.decryptData(cursor.getString(11)),
                            RSAUtil.decryptData(cursor.getString(12)));
                    // Adding contact to list
                    list.add(user);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public List<User> getAllRejectedDoctors() {
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_USER_TYPE + " =  'Doctor' AND " + KEY_DOCTOR_APPROVED + " = 'no';", null)) {
            if (cursor.moveToFirst()) {
                do {
                    User user = new User(cursor.getString(0),
                            RSAUtil.decryptData(cursor.getString(1)),
                            RSAUtil.decryptData(cursor.getString(2)),
                            RSAUtil.decryptData(cursor.getString(3)),
                            cursor.getBlob(4),
                            RSAUtil.decryptData(cursor.getString(5)),
                            RSAUtil.decryptData(cursor.getString(6)),
                            RSAUtil.decryptData(cursor.getString(7)),
                            RSAUtil.decryptData(cursor.getString(8)),
                            RSAUtil.decryptData(cursor.getString(9)),
                            RSAUtil.decryptData(cursor.getString(10)),
                            RSAUtil.decryptData(cursor.getString(11)),
                            RSAUtil.decryptData(cursor.getString(12)));
                    // Adding contact to list
                    list.add(user);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public List<User> getAllPatients() {
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_USER_TYPE + " =  'Patient' ;", null)) {
            if (cursor.moveToFirst()) {
                do {
                    User user = new User(cursor.getString(0),
                            RSAUtil.decryptData(cursor.getString(1)),
                            RSAUtil.decryptData(cursor.getString(2)),
                            RSAUtil.decryptData(cursor.getString(3)),
                            cursor.getBlob(4),
                            RSAUtil.decryptData(cursor.getString(5)),
                            RSAUtil.decryptData(cursor.getString(6)),
                            RSAUtil.decryptData(cursor.getString(7)),
                            RSAUtil.decryptData(cursor.getString(8)),
                            RSAUtil.decryptData(cursor.getString(9)),
                            RSAUtil.decryptData(cursor.getString(10)),
                            RSAUtil.decryptData(cursor.getString(11)),
                            RSAUtil.decryptData(cursor.getString(12)));
                    // Adding contact to list
                    list.add(user);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public Boolean registerAppointment(String date, String time, String type, String doctorId, String patientId) {

        if (!appointmentExist(date,
                time, doctorId)) {
            date = RSAUtil.encryptData(date);
            time = RSAUtil.encryptData(time);
            type = RSAUtil.encryptData(type);
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_APPOINTMENT_TYPE, type);
            values.put(KEY_APPOINTMENT_DATE, date);
            values.put(KEY_APPOINTMENT_TIME, time);
            values.put(KEY_APPOINTMENT_DOCTOR_ID, doctorId);
            values.put(KEY_APPOINTMENT_PATIENT_ID, patientId);

            // Inserting Row
            db.insert(TABLE_APPOINTMENTS, null, values);
            //2nd argument is String containing nullColumnHack
            db.close(); // Closing database connection
            return true;
        } else {
            return false;
        }
    }

    public List<Appointment> getPatientAppointments(User user, String date) {
        List<Appointment> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_APPOINTMENTS + " WHERE " + KEY_APPOINTMENT_PATIENT_ID + " =  '" + user.getId() + "' ;", null)) {
            if (cursor.moveToFirst()) {
                do {
                    if (RSAUtil.decryptData(cursor.getString(2)).equals(date)) {
                        Appointment appointment = new Appointment();
                        appointment.setId(cursor.getString(0));
                        appointment.setType(RSAUtil.decryptData(cursor.getString(1)));
                        appointment.setDate(RSAUtil.decryptData(cursor.getString(2)));
                        appointment.setTime(RSAUtil.decryptData(cursor.getString(3)));
                        appointment.setDoctorId(cursor.getString(4));
                        appointment.setPatientId(cursor.getString(5));
                        appointment.setPatientName(RSAUtil.decryptData(getUserName(cursor.getString(5))));
                        appointment.setDoctorName(RSAUtil.decryptData(getUserName(cursor.getString(4))));
                        // Adding contact to list
                        list.add(appointment);
                    }
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public List<Appointment> getDoctorAppointments(User user, String date) {
        List<Appointment> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_APPOINTMENTS + " WHERE " + KEY_APPOINTMENT_DOCTOR_ID + " =  '" + user.getId() + "' ;", null)) {
            if (cursor.moveToFirst()) {
                do {
                    if (RSAUtil.decryptData(cursor.getString(2)).equals(date)) {
                        Appointment appointment = new Appointment();
                        appointment.setId(cursor.getString(0));
                        appointment.setType(RSAUtil.decryptData(cursor.getString(1)));
                        appointment.setDate(RSAUtil.decryptData(cursor.getString(2)));
                        appointment.setTime(RSAUtil.decryptData(cursor.getString(3)));
                        appointment.setDoctorId(cursor.getString(4));
                        appointment.setPatientId(cursor.getString(5));
                        appointment.setPatientName(RSAUtil.decryptData(getUserName(cursor.getString(5))));
                        appointment.setDoctorName(RSAUtil.decryptData(getUserName(cursor.getString(4))));
                        // Adding contact to list
                        list.add(appointment);
                    }
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public Boolean registerDiagnoses(String diagnoses, String prescription, String doctorId, String patientId) {
        diagnoses = RSAUtil.encryptData(diagnoses);
        prescription = RSAUtil.encryptData(prescription);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RECORD_DIAGNOSES, diagnoses);
        values.put(KEY_RECORD_PRESCRIPTION, prescription);
        values.put(KEY_RECORD_DOCTOR_ID, doctorId);
        values.put(KEY_RECORD_PATIENT_ID, patientId);

        // Inserting Row
        db.insert(TABLE_RECORD, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
        return true;
    }

    public List<pdRecycler> getAllDiagnoses() {
        List<pdRecycler> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RECORD + ";", null)) {
            if (cursor.moveToFirst()) {
                do {
                    pdRecycler dia = new pdRecycler();
                    dia.setId(cursor.getString(0));
                    dia.setDiagnose(RSAUtil.decryptData(cursor.getString(1)));
                    dia.setPrescription(RSAUtil.decryptData(cursor.getString(2)));
                    dia.setdName(RSAUtil.decryptData(getUserName(cursor.getString(3))));
                    dia.setpName(RSAUtil.decryptData(getUserName(cursor.getString(4))));
                    list.add(dia);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public List<pdRecycler> getAllDiagnoses(String id) {
        List<pdRecycler> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RECORD + " WHERE " + KEY_RECORD_PATIENT_ID + " = '" + id + "';", null)) {
            if (cursor.moveToFirst()) {
                do {
                    pdRecycler dia = new pdRecycler();
                    dia.setId(cursor.getString(0));
                    dia.setDiagnose(RSAUtil.decryptData(cursor.getString(1)));
                    dia.setPrescription(RSAUtil.decryptData(cursor.getString(2)));
                    dia.setdName(RSAUtil.decryptData(getUserName(cursor.getString(3))));
                    dia.setpName(RSAUtil.decryptData(getUserName(cursor.getString(4))));
                    list.add(dia);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    /**
     * Private Methods implemented for easy use
     */
    private Boolean userExist(String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + ";", null);
        // means no users with same email exist
        if (cursor.moveToFirst()) {
            do {
                if (RSAUtil.decryptData(cursor.getString(2)).equals(email)){
                    return true;
                }
            }while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return false; // means already registered
    }

    private String getUserName(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_ID + " = '" + id + "';", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                return cursor.getString(1);
            }
        } else {
            db.close();
        }
        return null;
    }

    private Boolean appointmentExist(String date, String time, String doctorId) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_APPOINTMENTS + " WHERE " + KEY_APPOINTMENT_DOCTOR_ID + " = '" + doctorId + "';", null);
        if (cursor.moveToFirst()) {
            do {
                if (RSAUtil.decryptData(cursor.getString(2)).equals(date) && RSAUtil.decryptData(cursor.getString(3)).equals(time)) {
                    cursor.close();
                    db.close();
                    return true;
                }
            }while (cursor.moveToNext());
        } else {
            db.close();
            return false;
        }
        return false;
    }

    private int updateUserStatus(User user, String status) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_STATUS, status);

        // updating row
        int numRowsAffected = db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
        return numRowsAffected;
    }

    private int updateDoctorApprobation(User user, String approbation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DOCTOR_APPROVED, approbation);

        // updating row
        int numRowsAffected = db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
        return numRowsAffected;
    }

}
