package com.rahmat.app.kamus;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.rahmat.app.kamus.adapter.KamusListAdapter;
import com.rahmat.app.kamus.data.db.KamusDbHelper;
import com.rahmat.app.kamus.data.db.model.Words;
import com.rahmat.app.kamus.pref.KamusPreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        MaterialSearchBar.OnSearchActionListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.searchbar) MaterialSearchBar materialSearchBar;

    KamusListAdapter adapter;
    List<Words> listWords = new ArrayList<>();

    KamusDbHelper kamusDbHelper;
    KamusPreference kamusPreference;

    boolean isEnglish = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        materialSearchBar.setOnSearchActionListener(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.kamus_name);
        getSupportActionBar().setSubtitle(getString(R.string.ing_indo));

        adapter = new KamusListAdapter(this);
        kamusDbHelper = new KamusDbHelper(this);
        kamusPreference = new KamusPreference(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        kamusDbHelper.open();
        listWords = kamusDbHelper.getAll(isEnglish);
        kamusDbHelper.close();
        adapter.setWordsList(listWords);
        recyclerView.setAdapter(adapter);

    }

    void setUpActionBar(boolean isEnglish){
        kamusDbHelper.open();
        if(isEnglish){
            getSupportActionBar().setSubtitle(getString(R.string.ing_indo));
            listWords = kamusDbHelper.getAll(isEnglish);
            adapter.setWordsList(listWords);
        }else {
            getSupportActionBar().setSubtitle(getString(R.string.indo_ing));
            listWords = kamusDbHelper.getAll(isEnglish);
            adapter.setWordsList(listWords);
        }
        kamusDbHelper.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.change){
            if(isEnglish){
                isEnglish = false;
            }else{
                isEnglish = true;
            }
            setUpActionBar(isEnglish);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        getData(String.valueOf(text));
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }

    void getData(String q){
        try {
            kamusDbHelper.open();
            if (q.isEmpty()) {
                Toast.makeText(this, "Please insert word to translate", Toast.LENGTH_SHORT).show();
            } else {
                listWords = kamusDbHelper.getByName(q, isEnglish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            kamusDbHelper.close();
        }
        adapter.setWordsList(listWords);

    }
}
