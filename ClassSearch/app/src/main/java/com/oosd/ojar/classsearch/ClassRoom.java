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
        for (int i = 0; i < 100; i++) {
            Class course = new Class();
            course.setTitle("Class #" + i);
            course.setProfessor("San #" + i);
            mClasses.add(course);
        }
    }

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
