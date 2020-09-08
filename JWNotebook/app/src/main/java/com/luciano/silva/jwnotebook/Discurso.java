package com.luciano.silva.jwnotebook;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * Created by Luciano on 15/09/2015.
 */
public class Discurso implements Parcelable {

    private String tema;
    private String orador;
    private String data;
    private String discurso;

    private static int identificador = 0;

    public Discurso(String tema, String orador, String data, String discurso) {

        this.tema = tema;
        this.orador = orador;
        this.discurso = discurso;

        ++identificador;

        if (data == null) {
            Locale brasil = new Locale("pt", "BR");
            DateFormat f = DateFormat.getDateInstance(DateFormat.MEDIUM, brasil);
            data = f.format(new Date(System.currentTimeMillis()));
        }

        this.data = data;
    }

    public Discurso(String tema, String orador, String discurso) {
        this(tema, orador, null, discurso);
    }

    private Discurso(Parcel parcel) {
        tema = parcel.readString();
        orador = parcel.readString();
        data = parcel.readString();
        discurso = parcel.readString();
    }


    public static final Parcelable.Creator<Discurso> CREATOR = new Creator<Discurso>() {
        @Override
        public Discurso createFromParcel(Parcel in) {
            return new Discurso(in);
        }

        @Override
        public Discurso[] newArray(int size) {
            return new Discurso[size];
        }
    };

    @Override
    public int describeContents() {
        return identificador;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tema);
        dest.writeString(orador);
        dest.writeString(data);
        dest.writeString(discurso);
    }

    public String getTema() {
        return tema;
    }

    public String getOrador() {
        return orador;
    }

    public String getData() {
        return data;
    }

    public String getDiscurso() {
        return discurso;
    }

}
