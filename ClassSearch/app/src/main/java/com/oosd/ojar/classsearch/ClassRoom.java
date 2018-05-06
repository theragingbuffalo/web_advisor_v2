package com.oosd.ojar.classsearch;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClassRoom {
    private static ClassRoom sClassRoom;

    private List<Class> mClasses;

    public static ClassRoom get(Context context) {
        if (sClassRoom == null) {
            sClassRoom = new ClassRoom(context);
        }

        return sClassRoom;
    }

    private ClassRoom(Context context) {
        mClasses = new ArrayList<>();
    }

    public void addClasses(ArrayList<Class> classes) { mClasses.addAll(classes); }

    public List<Class> getClasses() {
        return mClasses;
    }

    public Class getClass(UUID id) {
        for (Class course : mClasses) {
            if (course.getId().equals(id)) {
                return course;
            }
        }

        return null;
    }
}
