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
import java.util.Map;

/**
 * Created by James on 5/2/2018.
 */

public class DownloadListings extends AsyncTask<String, String, String> {
    private ArrayList<Class> mClasses = new ArrayList<>();
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

    public DownloadListings(SearchActivity searchActivity) { mSearchActivity = searchActivity; }

    @Override
    protected String doInBackground(String... urlString) {
        URL url = null;
        try {
            url = new URL(urlString[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = Jsoup.parse(url, 3000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element table = doc.select("table").get(0); //select the first table.
        Elements rows = table.select("tr");

        for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
            Element row = rows.get(i);
            Elements cols = row.select("td");

            Class newClass = new Class(cols.get(0).text(), Integer.parseInt(cols.get(1).text()),
                    cols.get(2).text(), cols.get(3).text(), cols.get(4).text(), cols.get(5).text(),
                    cols.get(6).text(), cols.get(7).text());
            mClasses.add(newClass);

            String department = newClass.getCode().substring(0, 3);
            String number = newClass.getCode().substring(4, 7);

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
            newClass.setDescription(description);
        }
        return "success";
    }

    @Override
    protected void onPostExecute(String s) {
        ClassRoom.get(mSearchActivity).addClasses(mClasses);
    }
}