package com.oosd.ojar.classsearch;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by James on 5/2/2018.
 */

public class DownloadDescriptions extends AsyncTask<Void, Void, String> {
    private List<Class> mClasses = new ArrayList<>();
    private SearchActivity mSearchActivity;
    private static final Map<String, String> DEPARTMENT_MAP = createMap();
    private static final String catalogBase = "https://gustavus.edu/general_catalog/current/";

    private static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();
        map.put("AFS", "africanstudies");
        map.put("ART", "art");
        map.put("BIO", "bio");
        map.put("CHE", "che");
        map.put("CLA", "classics");
        map.put("GRE", "classics");
        map.put("LAT", "classics");
        map.put("COM", "comm");
        map.put("MCS", "mcs");
        map.put("E/M", "econ");
        map.put("EDU", "educ");
        map.put("ENG", "english");
        map.put("ENV", "env");
        map.put("FRE", "mlc_french");
        map.put("GWS", "gws");
        map.put("GEG", "geography");
        map.put("GEO", "geology");
        map.put("GER", "mlc_german");
        map.put("HES", "HES");
        map.put("HIS", "history");
        map.put("IDS", "interdis");
        map.put("NDL", "interdis");
        map.put("JPN", "mlc_japanese");
        map.put("MCS", "mcs");
        map.put("MUS", "mus");
        map.put("NUR", "nursing");
        map.put("PHI", "philosophy");
        map.put("PHY", "physics");
        map.put("POL", "polisci");
        map.put("PSY", "psychology");
        map.put("REL", "religion");
        map.put("RUS", "mlc_russian");
        map.put("SWE", "scand");
        map.put("SCA", "scand");
        map.put("S/A", "socioanthro");
        map.put("SPA", "mlc_spanish");
        map.put("T/D", "theatre-dance");
        return Collections.unmodifiableMap(map);
    }

    public DownloadDescriptions(SearchActivity searchActivity) { mSearchActivity = searchActivity; }

    @Override
    protected String doInBackground(Void... params) {
        mClasses = ClassRoom.get(mSearchActivity).getClasses("");

        for (int i = 0; i < mClasses.size(); i++) { //first row is the col names so skip it.
            Class course = mClasses.get(i);

            String department = course.getCode().substring(0, 3);
            String number = course.getCode().substring(4, 7);

            URL catalogURL = null;
            try {
                catalogURL = new URL(catalogBase + DEPARTMENT_MAP.get(department));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            String description = "";

            if (department.equals("CLA")) {

            } else if (department.equals("GRE")) {

            } else if (department.equals("LAT")) {

            } else if (department.equals("IDS")) {

            } else if (department.equals("NDL")) {

            } else if (department.equals("SWE")) {

            } else if (department.equals("SCA")) {

            } else {
                Document doc2 = null;
                try {
                    doc2 = Jsoup.parse(catalogURL, 3000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements strongs = doc2.select("p");
                for(Element p : strongs) {
                    if (p.text().contains(number)) {
                        description = p.text();
                        Log.d("taggy", description);
                        break;
                    }
                }
            }
            course.setDescription(description);
        }
        return "success";
    }

    @Override
    protected void onPostExecute(String s) {

    }
}