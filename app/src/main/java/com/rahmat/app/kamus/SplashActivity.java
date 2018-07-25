package com.rahmat.app.kamus;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.rahmat.app.kamus.data.db.KamusDbHelper;
import com.rahmat.app.kamus.data.db.model.Words;
import com.rahmat.app.kamus.pref.KamusPreference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void>{

        KamusDbHelper kamusDbHelper;
        KamusPreference kamusPreference;
        double progress;
        double maxprogress = 100;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            kamusDbHelper = new KamusDbHelper(getApplicationContext());
            kamusPreference = new KamusPreference(getApplicationContext());
        }

        @Override
        protected Void doInBackground(Void... voids) {
            boolean firstRun = kamusPreference.getFirstRun();
            kamusDbHelper.open();

            if(firstRun) {

                ArrayList<Words> wordsEnglishModel = preLoadRaw(R.raw.english_indonesia);
                ArrayList<Words> wordsIndoModel = preLoadRaw(R.raw.indonesia_english);

                progress = 30;
                publishProgress((int) progress);
                Double progressMaxInsert = 80.0;
                Double progressDiff = (progressMaxInsert - progress) /
                        (wordsEnglishModel.size() + wordsIndoModel.size());

                kamusDbHelper.insertTransaction(wordsEnglishModel, true);
                progress+=progressDiff;
                publishProgress((int) progress);

                kamusDbHelper.insertTransaction(wordsIndoModel, false);
                progress+=progressDiff;
                publishProgress((int) progress);

                kamusDbHelper.close();
                kamusPreference.setFirstRun(false);
            }else{
                try {
                    synchronized (this) {
                        this.wait(300);

                        publishProgress(50);

                        this.wait(300);
                        publishProgress((int) maxprogress);
                    }
                } catch (Exception e) {
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    private ArrayList<Words> preLoadRaw(int data) {
        ArrayList<Words> words = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_res = res.openRawResource(data);

            reader = new BufferedReader(new InputStreamReader(raw_res));
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                Words w;

                w = new Words(splitstr[0], splitstr[1]);

                words.add(w);
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;


    }
}
