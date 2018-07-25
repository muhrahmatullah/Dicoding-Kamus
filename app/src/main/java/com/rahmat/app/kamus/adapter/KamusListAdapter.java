package com.rahmat.app.kamus.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rahmat.app.kamus.KamusDetailActivity;
import com.rahmat.app.kamus.R;
import com.rahmat.app.kamus.data.db.model.Words;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KamusListAdapter extends RecyclerView.Adapter<KamusListAdapter.ViewHolder>{

    public static final String WORDS = "words";
    List<Words> wordsList = new ArrayList<>();
    Context context;

    public KamusListAdapter(Context context) {
        this.context = context;
    }

    void getWordsList(List<Words> wordsList){
        this.wordsList = wordsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_words, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        
        @BindView(R.id.tv_word) TextView tv_word;
        @BindView(R.id.tv_mean) TextView tv_mean;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final Words words){
            tv_word.setText(words.getWords());
            tv_mean.setText(words.getTranslation());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(itemView.getContext(), KamusDetailActivity.class);
                    i.putExtra(WORDS, words);
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }
}
