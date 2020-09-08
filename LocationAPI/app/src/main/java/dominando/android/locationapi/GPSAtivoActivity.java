package dominando.android.locationapi;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class GPSAtivoActivity extends AppCompatActivity {

    boolean ativo = false;
    String texto = "Status GPS: ";
    TextView mTextView, detalhe;
    LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsativo);

        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setText(texto);

        detalhe = (TextView) findViewById(R.id.textView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        boolean ligado = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (ligado) {
            mTextView.setText(texto + "Ligado!");
        } else {
            mTextView.setText(texto + "Desligado!");
        }
    }

    public void onClickLigarViaIntent(View view) {
        Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(callGPSSettingIntent);
    }

    // isso nao funciona
    // A partir da versão 2.3.3, você não pode mais ativar/desativar o gps sem a aprovação do usuário
    //public void onClickLigarViaCode(View view) {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                detalhe.setText(detalhe.getText() + "\nOnLocationChanged:\tLatitude: " + location.getLatitude() + " , Longitude: " + location.getLongitude());
//
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//                detalhe.setText(detalhe.getText() + "\nOnStatusChanged");
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//                detalhe.setText(detalhe.getText() + "\nOnProviderEnabled");
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//                detalhe.setText(detalhe.getText() + "\nOnProviderDesabled");
//            }
//        });
//    }
}
