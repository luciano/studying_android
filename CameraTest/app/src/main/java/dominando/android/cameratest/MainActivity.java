package dominando.android.cameratest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * http://www.softblue.com.br/blog/home/postid/15/UTILIZANDO_CAMERAS_EM_APLICACOES_ANDROID
 * http://www.klebermota.eti.br/2012/08/23/usando-a-camera-no-android-traducao-da-documentacao-oficial/
 */

public class MainActivity extends AppCompatActivity {

    private File imageFile;
    int width, height;
    private ImageView imageView;

    CarregarImagemTask carregarImagemTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void tirarFoto(View v) {
        // qualidade terrivel se so pegar o bitmap
        // e nao salva a imagem
//        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(it, 10);

        // segundo metodo
        // salva a imagem antes de mostrar no ImageView
        // desse jeito nao faz reduzir drasticamente a qualidade
        File picsDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        imageFile = new File(picsDir, "testes/foto_teste.jpg");

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setVisibility(View.VISIBLE);

        width = imageView.getWidth();
        height = imageView.getHeight();

        carregarImagem();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // segundo metodo - salva imagem e depois mostra

//        imageView = (ImageView) findViewById(R.id.imageView);
//        imageView.setVisibility(View.VISIBLE);
//
//        width = imageView.getWidth();
//        height = imageView.getHeight();
//
//        imageView.setImageBitmap(carregarImagem(imageFile, width, height));
//
//        carregarImagem();

        //if (requestCode == 10) {
            //if (resultCode == RESULT_OK) {
                // modo Thiengo
                // desse modo imagem fica pessima de resolucao
//                if(data != null){
//                    Bundle bundle = data.getExtras();
//                    if(bundle != null){
//                        Bitmap img = (Bitmap) bundle.get("data");
//
//                        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//                        imageView.setVisibility(View.VISIBLE);
//                        imageView.setImageBitmap(img);
//                    }
//                }
          //  }
        //}

    }

    public void carregarImagem() {
        if (imageFile != null && imageFile.exists()) {
            if (carregarImagemTask == null || carregarImagemTask.getStatus() != AsyncTask.Status.RUNNING) {
                carregarImagemTask = new CarregarImagemTask();
                carregarImagemTask.execute();
            }
        }
    }

    public static Bitmap carregarImagem(File file, int width, int height){
        if (width == 0 || height == 0) return null;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);

        int larguraFoto = options.outWidth;
        int alturaFoto = options.outHeight;

        int escala = Math.min(
                larguraFoto / width,
                alturaFoto / height
        );

        options.inJustDecodeBounds = false;
        options.inSampleSize = escala;


        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }

    class CarregarImagemTask extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... params) {
            return carregarImagem(imageFile, width, height);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
