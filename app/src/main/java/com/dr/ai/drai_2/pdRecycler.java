package com.dr.ai.drai_2;

public class pdRecycler {

    String dName;
    String diagnose;
    String prescription;

    public pdRecycler(String dName, String diagnose, String prescription) {
        this.dName = dName;
        this.diagnose = diagnose;
        this.prescription = prescription;
    }

    public String getdName() {
        return dName;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public String getPrescription() {
        return prescription;
    }
}
