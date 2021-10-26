package com.dr.ai.drai_2;

public class doctorRecycler {


    String patientName;
    String date;
    String typeOfSession;


    public String getPatientName() {
        return patientName;
    }

    public String getDate() {
        return date;
    }

    public String getTypeOfSession() {
        return typeOfSession;
    }


    public doctorRecycler(String patientName, String date, String typeOfSession) {
        this.patientName = patientName;
        this.date = date;
        this.typeOfSession = typeOfSession;
    }



}
