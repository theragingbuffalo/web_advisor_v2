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
        }
        return "success";
    }

    @Override
    protected void onPostExecute(String s) {
        ClassRoom.get(mSearchActivity).addClasses(mClasses);
        new DownloadDescriptions(mSearchActivity).execute();
    }
}