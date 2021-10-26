package com.dr.ai.drai_2;

public class patientPRecycler {

    String doctorName;
    String date;
    String typeOfSession;

    public patientPRecycler(String doctorName, String date, String typeOfSession) {
        this.doctorName = doctorName;
        this.date = date;
        this.typeOfSession = typeOfSession;
    }


    public String getDoctorName() {
        return doctorName;
    }

    public String getDate() {
        return date;
    }

    public String getTypeOfSession() {
        return typeOfSession;
    }

}
