package dominando.android.hellohandler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Luciano on 21/09/2015.
 */
public class Download {

    public static Bitmap downloadBitmap(String url) throws IOException {

        // faz download da imagem
        Bitmap bitmap = null;

        InputStream inputStream = new URL(url).openStream();

        // converte a InputStream para Bitmap

        bitmap = BitmapFactory.decodeStream(inputStream);
        inputStream.close();

        return bitmap;
    }

}
