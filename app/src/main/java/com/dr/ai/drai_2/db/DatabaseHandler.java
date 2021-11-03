package com.dr.ai.drai_2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dr.ai.drai_2.model.Appointment;
import com.dr.ai.drai_2.model.User;

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
    private static final String KEY_DOCTOR_APPROVED = "approved";//yes(for approved) OR no(for rejected) OR notYet(for registered)
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

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_APPOINTMENTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENTS);

        // Create tables again
        onCreate(db);
    }

    public Boolean doctorRegister(String name, String email, String personal_id, byte[] img, String gender, String city, String phone, String password, String iban) {
        if (!userExist(email)) {
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
            values.put(KEY_DOCTOR_APPROVED, "notYet");
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

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_EMAIL + " = " + email + " AND " + KEY_PASSWORD + " = " + password + ";", null);
        if (cursor != null) {
            cursor.moveToFirst();

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

            if (updateUserStatus(user, "LoggedIn") == 1) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
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

    public Boolean registerAppointment(String date, String time, String type, String doctorId, String patientId) {
        if (!appointmentExist(date,
                time, doctorId)) {
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
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_APPOINTMENTS + " WHERE " + KEY_APPOINTMENT_DATE + " = " + date + " AND " + KEY_APPOINTMENT_PATIENT_ID + " =  " + user.getId() + " ;", null)) {
            if (cursor.moveToFirst()) {
                do {
                    Appointment appointment = new Appointment();
                    appointment.setId(cursor.getString(0));
                    appointment.setType(cursor.getString(1));
                    appointment.setDate(cursor.getString(2));
                    appointment.setTime(cursor.getString(3));
                    appointment.setDoctorId(cursor.getString(4));
                    appointment.setPatientId(cursor.getString(5));
                    // Adding contact to list
                    list.add(appointment);
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

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_EMAIL + " = " + email + ";", null);
        if (cursor != null) {
            cursor.close();
            db.close();
            return true; // means already registered
        } else {
            db.close();
            return false; // means no users with same email exist
        }
    }

    private Boolean appointmentExist(String date, String time, String doctorId) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_APPOINTMENTS + " WHERE " + KEY_APPOINTMENT_DATE + " = " + date + " AND " + KEY_APPOINTMENT_TIME + " = " + time + " AND " + KEY_APPOINTMENT_DOCTOR_ID + " = " + doctorId + ";", null);
        if (cursor != null) {
            cursor.close();
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
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
