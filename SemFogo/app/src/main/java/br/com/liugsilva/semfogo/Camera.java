package br.com.liugsilva.semfogo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Camera {
    private File imageFile;
    int width, height;
    private ImageView imageView;

    CarregarImagemTask carregarImagemTask;

    /**
     * Tirar a foto e salvar num diretorio publico PICTURES
     * no diretorio SemFogo
     */
    public static void tirarFoto(Context context) {

        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String data =  calendar.get(Calendar.DAY_OF_MONTH) + "_" +
                (calendar.get(Calendar.MONTH) + 1) + "_" +
                calendar.get(Calendar.YEAR) + "_" +
                calendar.get(Calendar.HOUR_OF_DAY) + "_" +
                calendar.get(Calendar.MINUTE) + "_" +
                calendar.get(Calendar.SECOND);

        Debug.log("Camera.tirarFoto() Data: " + data);

        File picsDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM);
        File imageFile = new File(picsDir, "SemFogo/foto_" + data + ".jpg");

        Debug.log("Camera.tirarFoto() Path: " + imageFile.getAbsoluteFile());

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        context.startActivity(i);
    }

    public static int numeroFotos() {
        int num = 0;

        File picsDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM);
        File directory = new File(picsDir + "/SemFogo/");

        for (File s: directory.listFiles()) {
            Debug.log("Camera.numeroFotos() listDirectory: " + s.getAbsoluteFile());
            if (s.isFile())
                ++num;
        }
        Debug.log("Camera.numeroFotos() numero fotos: " + num);

        return num;
    }

    // teste
    public static ArrayList<String> pathFotos() {
        ArrayList<String> pathFotos = new ArrayList<>();
        if (numeroFotos() > 0) {
            File picsDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM);
            File directory = new File(picsDir + "/SemFogo/");

            for (File s: directory.listFiles()) {
                Debug.log("Camera.numeroFotos() listDirectory: " + s.getAbsoluteFile());
                if (s.isFile())
                    pathFotos.add(s.getAbsolutePath());
            }
        }
        return pathFotos;
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        imageView = (ImageView) findViewById(R.id.imageView);
//        imageView.setVisibility(View.VISIBLE);
//
//        width = imageView.getWidth();
//        height = imageView.getHeight();
//
//        carregarImagem();
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

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

  //  }

    public void carregarImagem() {
        if (imageFile != null && imageFile.exists()) {
            if (carregarImagemTask == null || carregarImagemTask.getStatus() != AsyncTask.Status.RUNNING) {
                carregarImagemTask = new CarregarImagemTask();
                carregarImagemTask.execute();
            }
        }
    }

    public static Bitmap carregarImagem(File file, int width, int height) {
        return carregarImagem(file.getAbsolutePath(), width, height);
    }

    public static Bitmap carregarImagem(String file, int width, int height) {
        if (width == 0 || height == 0) return null;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file, options);

        int larguraFoto = options.outWidth;
        int alturaFoto = options.outHeight;

        int escala = Math.min(
                larguraFoto / width,
                alturaFoto / height
        );

        options.inJustDecodeBounds = false;
        options.inSampleSize = escala;

        return BitmapFactory.decodeFile(file, options);
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
