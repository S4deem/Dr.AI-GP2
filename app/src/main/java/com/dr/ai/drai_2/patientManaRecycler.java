package com.dr.ai.drai_2;

public class patientManaRecycler {

    String nameViewP;
    String numberViewP;
    String emailViewP;
    String idViewP;
    String cityView;
    String genderView;

    public String getNameViewP() {
        return nameViewP;
    }

    public String getNumberViewP() {
        return numberViewP;
    }

    public String getEmailViewP() {
        return emailViewP;
    }

    public String getIdViewP() {
        return idViewP;
    }

    public String getCityView() {
        return cityView;
    }

    public String getGenderView() {
        return genderView;
    }

    public patientManaRecycler(String nameViewP, String numberViewP, String emailViewP, String idViewP, String cityView, String genderView) {
        this.nameViewP = nameViewP;
        this.numberViewP = numberViewP;
        this.emailViewP = emailViewP;
        this.idViewP = idViewP;
        this.cityView = cityView;
        this.genderView = genderView;
    }


}