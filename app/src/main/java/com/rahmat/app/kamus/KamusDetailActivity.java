package com.rahmat.app.kamus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.rahmat.app.kamus.adapter.KamusListAdapter;
import com.rahmat.app.kamus.data.db.model.Words;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KamusDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_mean_detail)
    TextView tv_mean;

    @BindView(R.id.tv_word_detail) TextView tv_word;

    Words words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus_detail);

        ButterKnife.bind(this);

        words = getIntent().getParcelableExtra(KamusListAdapter.WORDS);

        tv_mean.setText(words.getTranslation());
        tv_word.setText(words.getWords());

    }
}
