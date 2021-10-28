package com.dr.ai.drai_2;

public class rejectedRecycler {

    String nameViewR;
    String numberViewR;
    String emailViewR;
    String idViewR;
    int imageR;

    public String getNameViewR() {
        return nameViewR;
    }

    public String getNumberViewR() {
        return numberViewR;
    }

    public String getEmailViewR() {
        return emailViewR;
    }

    public String getIdViewR() {
        return idViewR;
    }

    public int getImageR() {
        return imageR;
    }

    public rejectedRecycler(String nameViewR, String numberViewR, String emailViewR, String idViewR, int imageR) {
        this.nameViewR = nameViewR;
        this.numberViewR = numberViewR;
        this.emailViewR = emailViewR;
        this.idViewR = idViewR;
        this.imageR = imageR;
    }

}