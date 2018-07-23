package com.rahmat.app.kamus.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Words implements Parcelable {
    int id;
    String words;
    String translation;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.words);
        dest.writeString(this.translation);
    }

    public Words() {
    }

    protected Words(Parcel in) {
        this.id = in.readInt();
        this.words = in.readString();
        this.translation = in.readString();
    }

    public static final Parcelable.Creator<Words> CREATOR = new Parcelable.Creator<Words>() {
        @Override
        public Words createFromParcel(Parcel source) {
            return new Words(source);
        }

        @Override
        public Words[] newArray(int size) {
            return new Words[size];
        }
    };
}
