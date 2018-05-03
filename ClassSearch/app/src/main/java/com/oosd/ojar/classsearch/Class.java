package com.oosd.ojar.classsearch;

import java.util.Date;
import java.util.UUID;

public class Class {

    private UUID mId;
    private String mTitle;
    private String mProfessor;

    public Class() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getProfessor() {
        return mProfessor;
    }

    public void setProfessor(String prof) {
        mProfessor = prof;
    }
}
