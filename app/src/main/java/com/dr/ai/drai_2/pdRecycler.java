package com.dr.ai.drai_2;

public class pdRecycler {

    String id;
    String dName;
    String pName;
    String diagnose;
    String prescription;

    public pdRecycler(String dName, String diagnose, String prescription) {
        this.dName = dName;
        this.diagnose = diagnose;
        this.prescription = prescription;
    }
    public pdRecycler(){

    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpName() {
        return pName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public void setPrescription(String prescription) {
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
