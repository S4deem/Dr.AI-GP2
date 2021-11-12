package com.dr.ai.drai_2;

import android.graphics.Bitmap;

public class pendingRecycler {

    String nameView;
    String numberView;
    String emailView;
    String idView;
    Bitmap image;

    public String getNameView() {
        return nameView;
    }

    public String getNumberView() {
        return numberView;
    }

    public String getEmailView() {
        return emailView;
    }

    public String getIdView() {
        return idView;
    }

    public Bitmap getImage() {
        return image;
    }
    public pendingRecycler(String nameView, String numberView, String emailView, String idView, Bitmap image) {
        this.nameView = nameView;
        this.numberView = numberView;
        this.emailView = emailView;
        this.idView = idView;
        this.image = image;
    }

}