package com.oosd.ojar.classsearch;

import java.util.Date;
import java.util.UUID;

public class Class {

    private UUID mId;
    private String mTitle;
    private String mProfessor;
    private int mSynonym;
    private String mCode;
    private String mApprovals;
    private String mMeetingDays;
    private String mStart;
    private String mEnd;
    private String mDescription = "";
    private boolean mIsSaved;

    public Class(String code, int synonym, String title, String meetingDays,
                 String start, String end, String professor, String approvals) {
        mId = UUID.randomUUID();
        mCode = code;
        mSynonym = synonym;
        mTitle = title;
        mMeetingDays = meetingDays;
        mStart = start;
        mEnd = end;
        mProfessor = professor;
        mApprovals = approvals;
        mIsSaved = false;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getProfessor() {
        return mProfessor;
    }

    public int getSynonym() {
        return mSynonym;
    }

    public String getCode() {
        return mCode;
    }

    public String getApprovals() {
        return mApprovals;
    }

    public String getMeetingDays() {
        return mMeetingDays;
    }

    public String getStart() {
        return mStart;
    }

    public String getEnd() {
        return mEnd;
    }

    public void toggleSave() {
        mIsSaved = !mIsSaved;
    }

    public boolean isSaved() {
        return mIsSaved;
    }

    public String getDescription() { return mDescription; }

    public void setDescription(String description) { mDescription = description; }
}
