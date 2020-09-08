package br.com.liugsilva.semfogo;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import br.com.liugsilva.semfogo.activity.BaseActivity;

/**
 * Vai lidar com as localizacoes do projeto
 *
 * Created by Luciano on 19/12/2015.
 */
public class Localizacao {

    private Context context;
    private static Localizacao mInstance;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private double latitude;
    private double longitude;

    private Localizacao(Context context) {
        this.context = context;
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public static Localizacao newInstance(Context context) {

        if (mInstance == null) {
            mInstance = new Localizacao(context);
        }

        return mInstance;
    }

    public boolean isGPSEnabled() {
        return mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public boolean isNeteworkLocationEnabled() {
        return mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void ligarGPSAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("O GPS está desligado, deseja ligar agora?");
        builder.setCancelable(false);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ligarGPSIntent();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Responsavel por chamar uma activity das configurações do celular
     * para ligar o GPS. É necessario ligar o GPS com o usuário permitindo e ligando manualmente.
     *
     */
    public void ligarGPSIntent() {
        Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(callGPSSettingIntent);
    }

    public void requestLocation(boolean request) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (request) {
            mLocationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    log("onLocationChanged: " + location.toString());
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    log("onStatusChanged: " + provider);
                }

                @Override
                public void onProviderEnabled(String provider) {
                    log("onProviderEnabled: " + provider);
                }

                @Override
                public void onProviderDisabled(String provider) {
                    log("onProviderDisabled: " + provider);
                }
            };

            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
        } else {
            if (mLocationListener != null)
                mLocationManager.removeUpdates(mLocationListener);
        }

    }

    private LocationListener onListenerLocation() {

        return mLocationListener;
    }

    private void log(String message) {
        Log.d(BaseActivity.TAGLOG, message);
    }

    public String getLocation() {
        log(latitude + "," + longitude);
        return "\nLatitude: " + latitude + "\nLongitude: " + longitude;
        //return latitude + "," + longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
