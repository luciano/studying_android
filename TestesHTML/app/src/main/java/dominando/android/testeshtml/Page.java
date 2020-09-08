package dominando.android.testeshtml;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *  Representa cada pagina HTML
 *
 * Created by Luciano on 01/09/2015.
 */
public class Page implements Parcelable {

    private String data;
    private String url;
    private String titulo;

    private static int identificador = 0;

    public Page(String titulo, String url) {
        this(titulo, url, null);
    }

    public Page(String titulo, String url, String data) {
        setTitulo(titulo);
        setUrl(url);
        ++identificador;

        if (data == null) {
            Locale brasil = new Locale("pt", "BR");
            DateFormat f = DateFormat.getDateInstance(DateFormat.MEDIUM, brasil);
            data = f.format(new Date(System.currentTimeMillis()));
        }
        this.data = data;
    }

    private Page(Parcel parcel) {
        titulo = parcel.readString();
        url = parcel.readString();
        data = parcel.readString();
    }


    public static final Parcelable.Creator<Page> CREATOR = new Creator<Page>() {
        @Override
        public Page createFromParcel(Parcel in) {
            return new Page(in);
        }

        @Override
        public Page[] newArray(int size) {
            return new Page[size];
        }
    };

    @Override
    public int describeContents() {
        return identificador;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(url);
        dest.writeString(data);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
