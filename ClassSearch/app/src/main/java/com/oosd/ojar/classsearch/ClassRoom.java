package com.oosd.ojar.classsearch;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
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

    public List<Class> getClasses(String searchParam) {
        if (searchParam.isEmpty())
        {
            Log.d("tag", "here");
            return mClasses;
        }
        else
        {
            Log.d("tag", searchParam);
            List<Class> classes = new ArrayList<>();
            classes.addAll(mClasses);
            String[] rules = searchParam.split("&&");
            for (String rule : rules)
            {
                if (rule.substring(0, rule.indexOf(':')).equals("AREA"))
                {
                    String area = rule.substring(rule.indexOf(':') + 1);
                    ListIterator<Class> iter = classes.listIterator();
                    while(iter.hasNext())
                    {
                        if(!iter.next().getApprovals().contains(area))
                        {
                            iter.remove();
                        }
                    }
                }
                else if (rule.substring(0, rule.indexOf(':')).equals("DEP"))
                {
                    String department = rule.substring(rule.indexOf(':') + 1);
                    ListIterator<Class> iter = classes.listIterator();
                    while(iter.hasNext())
                    {
                        if(!iter.next().getCode().contains(department))
                        {
                            iter.remove();
                        }
                    }
                }
            }
            return classes;
        }
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
