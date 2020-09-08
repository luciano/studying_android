package dominando.android.locationapi;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;

// atualizacao de tempos em tempos
public class UpdateLocationActivity extends ActionBarActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    private GoogleApiClient mGoogleApiClient; // pq LocationService é do google service
    private LocationRequest mLocationRequest; // usado exclusivamente para listener de update
    private TextView tvUpdateLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);
        Log.i("LOG", "UpdateLocationActivity.onCreate()");

        tvUpdateLocation = (TextView) findViewById(R.id.tv_update_location);

        callConnection();
    }


    @Override
    public void onResume(){
        super.onResume();

        if(mGoogleApiClient !=null && mGoogleApiClient.isConnected()){
            startLocationUpdate();
        }
    }


    @Override
    public void onPause(){
        super.onPause();

        if(mGoogleApiClient != null){
            stopLocationUpdate();
        }
    }


    private synchronized void callConnection(){
        Log.i("LOG", "UpdateLocationActivity.callConnection()");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    private void initLocationRequest(){
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);//atualizacao de 5 em 5 segundos
        mLocationRequest.setFastestInterval(2000);// garantir a correta atualizacao de tempo minimo para trabalhar, atualizacao no minimo de 2 em 2 segundos da ultima atualizacao
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);// prioridade usar GPS é de melhor precisao
    }


    private void startLocationUpdate(){
        initLocationRequest();
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    // liberar recursos
    private void stopLocationUpdate(){
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }


    // LISTENERS
        @Override
        public void onConnected(Bundle bundle) {
            Log.i("LOG", "UpdateLocationActivity.onConnected(" + bundle + ")");

            Location l = LocationServices
                    .FusedLocationApi
                    .getLastLocation(mGoogleApiClient); // PARA JÁ TER UMA COORDENADA PARA O UPDATE FEATURE UTILIZAR

            startLocationUpdate();
        }

        @Override
        public void onConnectionSuspended(int i) {
            Log.i("LOG", "UpdateLocationActivity.onConnectionSuspended(" + i + ")");
        }

        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {
            Log.i("LOG", "UpdateLocationActivity.onConnectionFailed(" + connectionResult + ")");
        }

        @Override
        public void onLocationChanged(Location location) {
            tvUpdateLocation.setText(Html.fromHtml("Location: " + location.getLatitude() + "<br />" +
                    "Longitude: " + location.getLongitude() + "<br />" +
                    "Bearing: " + location.getBearing() + "<br />" +
                    "Altitude: " + location.getAltitude() + "<br />" +
                    "Speed: " + location.getSpeed() + "<br />" +
                    "Provider: " + location.getProvider() + "<br />" +
                    "Accuracy: " + location.getAccuracy() + "<br />" +
                    "Speed: " + DateFormat.getTimeInstance().format(new Date()) + "<br />"));
        }
}
