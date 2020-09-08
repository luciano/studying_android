package dominando.android.diretorios;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int MB = 1024 * 1024;
        //Runtime runtime = Runtime.getRuntime();
        //double total = runtime.maxMemory() / MB;

        //double inicio = total - (runtime.freeMemory() / MB);

        StringBuilder texto = new StringBuilder();
        TextView textView = (TextView) findViewById(R.id.tv);


        File[] listRoots = File.listRoots();
        texto.append("File.listRoots:\n");
        for(File f : listRoots) {
            texto.append(f.getAbsolutePath() + "\n");
        }
        texto.append("\n");

        File externalDCIM = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        texto.append("Environment.DirectoryDCIM:\n");
        texto.append(externalDCIM.getAbsolutePath() + "\n");
        texto.append("\n");

        File fileData = Environment.getDataDirectory();
        texto.append("Environment.getDataDirectory:\n");
        texto.append(fileData.getAbsolutePath() + "\n");
        texto.append("\n");

        File fileRoot = Environment.getRootDirectory();
        texto.append("Environment.getRootDirectory:\n");
        texto.append(fileRoot.getAbsolutePath() + "\n");
        texto.append("\n");

        File fileExternal = Environment.getExternalStorageDirectory();
        texto.append("Environment.getExternalStorageDirectory:\n");
        texto.append(fileExternal.getAbsolutePath() + "\n");
        texto.append("\n");


        File fileSamsung = new File("storage/extSdCard");
        texto.append("storage/extSdCard:\n");
        texto.append(fileSamsung.getAbsolutePath() + "\n");
        texto.append("canRead: " + fileSamsung.canRead() + "\n");
        texto.append("canWrite: " + fileSamsung.canWrite() + "\n");
        texto.append("Length:: " + fileSamsung.length() + "KB\n");
        texto.append("TotalSpace: " + fileSamsung.getTotalSpace()/MB + "MB\n");
        texto.append("FreeSpace: " + fileSamsung.getFreeSpace()/MB + "MB\n");
        texto.append("UsableSpace: " + (fileSamsung.getTotalSpace() - fileSamsung.getFreeSpace())/MB + "MB\n");
        texto.append("\n");

        texto.append("Tentativa de write true:\n");
        fileSamsung.setWritable(true, false);
        texto.append("canRead: " + fileSamsung.canRead() + "\n");
        texto.append("canWrite: " + fileSamsung.canWrite() + "\n");

        File tentativa = new File(fileSamsung.getAbsolutePath() + "/TestesSD/teste.txt");

        if (tentativa.getParentFile().exists()) {
            texto.append("Existe dir: " + tentativa.getParentFile().getAbsolutePath());

            if (tentativa.exists()) {

                texto.append("\n\nArquivo existe.\n");
                texto.append("canRead: " + tentativa.canRead() + "\n");
                texto.append("canWrite: " + tentativa.canWrite() + "\n");

            } else {
                try {
                    tentativa.createNewFile();
                    texto.append("\n\nArquivo criado.\n");
                    tentativa.setReadable(true);
                    texto.append("canRead: " + tentativa.canRead() + "\n");
                    texto.append("canWrite: " + tentativa.canWrite() + "\n");
                } catch (IOException e) {
                    texto.append("Erro no createNewFile");
                }

            }
        } else {

            tentativa.getParentFile().mkdir();
            tentativa.getParentFile().setReadable(true, false);
            tentativa.getParentFile().setWritable(true);
            tentativa.getParentFile().setWritable(true, false);
            texto.append("Criou dir: " + tentativa.getParentFile().getAbsolutePath());

        }






        fileData = null;
        fileExternal = null;
        fileRoot = null;
        fileSamsung = null;
        listRoots = null;
        externalDCIM = null;

//        runtime.runFinalization();
 //       runtime.gc();

   //     double fim = total - (runtime.freeMemory() / MB);



        File dirRoot = new File("/storage");
        File[] f = dirRoot.listFiles();
        texto.append("\n\n---------------------\nlist /storage:\n");
        for (File file : f) {
            texto.append("\n>" + file.getAbsolutePath() + ": " + size(file.getTotalSpace()) + ". Length: " + size(file.length()) + "\n");
        }

        dirRoot = new File("/mnt");
        f = dirRoot.listFiles();
        texto.append("\n\n---------------------\nlist /mnt:\n");
        for (File file : f) {
            texto.append("\n>" + file.getAbsolutePath() + ": " + size(file.getTotalSpace()) + ". Length: " + size(file.length()) + "\n");

        }

        dirRoot = new File("/sdcard");
        f = dirRoot.listFiles();
        texto.append("\n\n---------------------\nlist /sdcard:\n");
        for (File file : f) {
            texto.append("\n>" + file.getAbsolutePath() + ": " + size(file.getTotalSpace()) + ". Length: " + size(file.length()) + "\n");
        }

        File file1 = new File("/storage/emulated/0/WhatsApp/");
        texto.append("\n>" + file1.getAbsolutePath() + ": " + size(file1.getTotalSpace()) + ". Length: " + size(file1.length()) + "\n");


        dirRoot = new File("/mnt/extSdCard");
        f = dirRoot.listFiles();
        texto.append("\n\n---------------------\nlist /mnt/extSdCard:\n");
        if (dirRoot.exists())
        for (File file : f) {
            texto.append("\n>" + file.getAbsolutePath() + ": " + size(file.getTotalSpace()) + ". Length: " + size(file.length()) + "\n");

        }

        dirRoot = new File("/mnt/sdcard");
        f = dirRoot.listFiles();
        texto.append("\n\n---------------------\nlist /mnt/sdcard:\n");
        if (dirRoot.exists())
        for (File file : f) {
            texto.append("\n>" + file.getAbsolutePath() + ": " + size(file.getTotalSpace()) + ". Length: " + size(file.length()) + "\n");

        }


        dirRoot = new File("/storage/extSdCard");
        f = dirRoot.listFiles();
        texto.append("\n\n---------------------\nlist /storage/extSdCard:\n");
        if (dirRoot.exists())
        for (File file : f) {
            texto.append("\n>" + file.getAbsolutePath() + ": " + size(file.getTotalSpace()) + ". Length: " + size(file.length()) + "\n");

        }

        dirRoot = new File("/storage/sdcard0");
        f = dirRoot.listFiles();
        texto.append("\n\n---------------------\nlist /storage/sdcard0:\n");
        if (dirRoot.exists())
        for (File file : f) {
            texto.append("\n>" + file.getAbsolutePath() + ": " + size(file.getTotalSpace()) + ". Length: " + size(file.length()) + "\n");

        }

        dirRoot = new File("/storage/emulated");
        f = dirRoot.listFiles();
        texto.append("\n\n---------------------\nlist /storage/emulated:\n");
        if (dirRoot.exists())
        for (File file : f) {
            texto.append("\n>" + file.getAbsolutePath() + ": " + size(file.getTotalSpace()) + ". Length: " + size(file.length()) + "\n");

        }




        /***
         *
         * Diretorios comuns de SD Card
         *
         *

         /emmc
         /mnt/sdcard/external_sd
         /mnt/external_sd
         /sdcard/sd
         /mnt/sdcard/bpemmctest
         /mnt/sdcard/_ExternalSD
         /mnt/sdcard-ext
         /mnt/Removable/MicroSD
         /Removable/MicroSD
         /mnt/external1
         /mnt/extSdCard
         /mnt/extsd
         /mnt/usb_storage  <-- usb flash mount
         /mnt/extSdCard  <-- usb flash mount
         /mnt/UsbDriveA  <-- usb flash mount
         /mnt/UsbDriveB  <-- usb flash mount

         /storage/sdcard1 //!< Motorola Xoom
         /storage/extsdcard  //!< Samsung SGS3
         /storage/sdcard0/external_sdcard  // user request
         /mnt/extsdcard
         /mnt/sdcard/external_sd  //!< Samsung galaxy family
         /mnt/external_sd
         /mnt/media_rw/sdcard1   //!< 4.4.2 on CyanogenMod S3
         /removable/microsd              //!< Asus transformer prime
         /mnt/emmc
         /storage/external_SD            //!< LG
         /storage/ext_sd                 //!< HTC One Max
         /storage/removable/sdcard1      //!< Sony Xperia Z1
         /data/sdext
         /data/sdext2
         /data/sdext3
         /data/sdext4

         *
         *
         */


        File dirStorege = new File("/storage");
        File dirMnt = new File("/mnt");

        long timeInicial = System.currentTimeMillis();
        long maior = 0;
        String pathMaior = "";
        File[] diretorios = dirStorege.listFiles();
        if (diretorios != null)
        for (File file : diretorios) {

            if( file.getTotalSpace() > maior) {
                maior = file.getTotalSpace();
                pathMaior = file.getAbsolutePath();
            }

        }
        long timeFinal = System.currentTimeMillis();
        final int MIL = 1000;

        String tempo = String.format("%.3f", ((double)(timeFinal - timeInicial)/MIL) );
        texto.append("maior storage: " + pathMaior + ": " + size(maior) + " -> " + tempo + "\n");

        timeInicial = System.currentTimeMillis();
        maior = 0;
        pathMaior = "";
        diretorios = dirMnt.listFiles();
        if (diretorios != null)
        for (File file : diretorios) {

            if( file.getTotalSpace() > maior) {
                maior = file.getTotalSpace();
                pathMaior = file.getAbsolutePath();
            }

        }
        timeFinal = System.currentTimeMillis();
        tempo = String.format("%.3f", ((double) (timeFinal - timeInicial) / MIL));
        texto.append("maior mnt:" + pathMaior + ": " + size(maior) + " -> " + tempo + "\n");


        //timeInicial = System.currentTimeMillis();

        //String whatsapp = procuraPasta(dirStorege, "WhatsApp");
        //String whatsapp = procuraPasta(Environment.getExternalStorageDirectory(), "WhatsApp");

        //File w = new File(whatsapp, "Profile Pictures");
        //if (w.exists()) {
          //  w.delete();
       // }

//        limpaDiretorio(new File(whatsapp));

//        File wts = new File(whatsapp);
 //       if(!wts.exists()) {
  //          wts.mkdir();
    //        wts.setReadable(true, false);
      //      wts.setWritable(true, false);
        //}

     //   timeFinal = System.currentTimeMillis();
     //   tempo = String.format("%.3f", ((double) (timeFinal - timeInicial) / MIL));
      //  texto.append(whatsapp + ": " + sizeFolder(new File(whatsapp)) + " -> " + tempo + "\n");



        texto.append("\n\nInformações aparelho:\n" +
                "Board: " + Build.BOARD +
                "\nBootLoader: " + Build.BOOTLOADER +
                "\nBrand: " + Build.BRAND +
                "\nDevice: " + Build.DEVICE +
                "\nDisplay: " + Build.DISPLAY +
                "\nFINGERPRINT: " + Build.FINGERPRINT +
                "\nHardware: " + Build.HARDWARE +
                "\nManufacturer: " + Build.MANUFACTURER +
                "\nModel: " + Build.MODEL +
                "\nProduct: " + Build.PRODUCT +
                "\nID: " + Build.ID +
                "\nSDK_INT: " + Build.VERSION.SDK_INT +
                "\nVersao Android: " + versao(Build.VERSION.SDK_INT)
        );


        //File k = Environment.getExternalStoragePublicDirectory("TesteSD");
        //k.setReadable(true, false);
        //k.setWritable(true, false);

        //File kF = new File(k, "teste.txt");
        //try {
            //kF.createNewFile();
           // kF.setReadable(true, false);
         //   kF.setWritable(true, false);
       // } catch (IOException e) {
       //     e.printStackTrace();
       // }

        // usando Context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            File[] f2 = getExternalFilesDirs(null);

            texto.append("\n\n---------------------\nlist /storage/emulated:\n");
            for (File file : f2) {
                texto.append("\n>" + file.getAbsolutePath() + ": " + size(file.getTotalSpace()) + ". Length: " + size(file.length()) + "\n");

            }

            File wts = new File("/storage/external_SD/Teste1SD");
            if(!wts.exists()) {
               wts.mkdir();
               wts.setReadable(true, false);
               wts.setWritable(true, false);
            }


            File k = Environment.getExternalStoragePublicDirectory("TesteSD");
            k.setReadable(true, false);
            k.setWritable(true, false);

            File kF = new File(k, "teste.txt");
            try {
                kF.createNewFile();
                kF.setReadable(true, false);
                kF.setWritable(true, false);
                texto.append("\n<getExternalPublic>" + kF.getAbsolutePath() + ": " + size(kF.getTotalSpace()) + ". Length: " + size(kF.length()) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

       }

        File[] f1 = new File("/storage/external_SD").listFiles();

        texto.append("\n\n---------------------\nlist /storage/external_SD:\n");
        if (f1 != null)
        for (File file : f1) {
            texto.append("\n>" + file.getAbsolutePath() + ": " + size(file.getTotalSpace()) + ". Length: " + size(file.length()) + "\n");

        }

        textView.setText(texto.toString());
    }

    private String versao(int sdkInt) {
        String version = "";

        switch(sdkInt) {
            case 9:
                version = "GINGERBREAD: Android 2.3";
                break;
            case 10:
                version = "GINGERBREAD_MR1: Android 2.3.3";
                break;
            case 11:
                version = "HONEYCOMB: Android 3.0";
                break;
            case 12:
                version = "HONEYCOMB_MR1: Android 3.1";
                break;
            case 13:
                version = "HONEYCOMB_MR2: Android 3.2";
                break;
            case 14:
                version = "ICE_CREAM_SANDWICH: Android 4.0";
                break;
            case 15:
                version = "ICE_CREAM_SANDWICH_MR1: Android 4.0.1";
                break;
            case 16:
                version = "JELLY_BEAN: Android 4.1";
                break;
            case 17:
                version = "JELLY_BEAN_MR1: Android 4.2";
                break;
            case 18:
                version = "JELLY_BEAN_MR2: Android 4.3";
                break;
            case 19:
                version = "KITKAT: Android 4.4";
                break;
            case 20:
                version = "KITKAT_WATCH: Android 4.4W";
                break;
            case 21:
                version = "LOLLIPOP?: Android 5.0";
                break;
            case 22:
                version = "LOLLIPOP_MR1";
                break;
        }

        return version;
    }

    private void limpaDiretorio(File dir) {
        File[] diretorios = dir.listFiles();
        if (diretorios != null) {
            for (File f : diretorios) {
                if(f.isDirectory())
                   if (!f.delete())
                       limpaDiretorio(f);
            }
        }
        if(dir.isDirectory())
            dir.delete();
        return;
    }


    private String sizeFolder(File dir) {
        return size(tamanho(dir));
    }

    private long tamanho (File dir) {
        long ret = 0;
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                ret += tamanho (f);
            } else {
                ret += f.length();
            }
        }
        return ret;
    }

    private String procuraPasta(File dir, String pasta) {

        if (new File(dir , pasta).exists()) {
            return dir.getAbsolutePath() + "/WhatsApp";
        }

        File[] d = dir.listFiles();
        if (d != null)
        for (File file : d) {

            if (new File(file , pasta).exists()) {
                return file.getAbsolutePath() + "/WhatsApp";
            } else {
                if(file.isFile())
                    continue;
                procuraPasta(file, pasta);
            }
        }

        return null;
    }

    private String size(long tamanho) {

        int KB = 1024;
        int MB = KB * KB;
        int GB = 1024 * MB;
        double result;

        String quantificador = (tamanho >= MB? "MB" : "KB");

        if("MB".equals(quantificador)) {
            quantificador = (tamanho >= GB ? "GB" : "MB");

            if ("MB".equals(quantificador)) {
                result = (double)tamanho / MB;
                return String.format("%.2f MB", result);
            }
            else {
                result = (double)tamanho / GB;
                return String.format("%.2f GB", result);
            }
        }
        result = (double)tamanho / KB;
        return String.format("%.2f KB", result);
    }

}
