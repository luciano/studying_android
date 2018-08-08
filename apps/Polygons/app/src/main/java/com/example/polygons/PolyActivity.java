package com.example.polygons;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import static com.example.polygons.R.id.map;

public class PolyActivity extends AppCompatActivity
        implements
                OnMapReadyCallback,
                GoogleMap.OnPolylineClickListener{

    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;

    private static final int POLYLINE_STROKE_WIDTH_PX = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng largoIda1 = new LatLng(-18.2470733, -43.6019483);
        LatLng largoIda2 = new LatLng(-18.2437268, -43.6030911);
        LatLng largoIda3 = new LatLng(-18.2416994, -43.6039799);
        LatLng largoIda4 = new LatLng(-18.2394417, -43.6049197);
        LatLng largoIda5 = new LatLng(-18.2359705, -43.6081575);
        LatLng largoIda6 = new LatLng(-18.2304570, -43.6065394);

        String titulo = "Praça Sagrado Coração de Jesus";
        String descricao = "Perto do Corpo de Bombeiro";

        googleMap.addMarker(new MarkerOptions()
                .position(largoIda1)
                .title(titulo)
                .snippet(descricao));

        titulo = "Rua Sebastião Jose Ferreira";
        descricao = "Igreja Nossa Senhora da Luz";
        googleMap.addMarker(new MarkerOptions()
                .position(largoIda2)
                .title(titulo)
                .snippet(descricao));

        titulo = "Rua do Bicame";
        descricao = "Bar do Amendoin";
        googleMap.addMarker(new MarkerOptions()
                .position(largoIda3)
                .title(titulo)
                .snippet(descricao));

        descricao = "DER - MG";
        googleMap.addMarker(new MarkerOptions()
                .position(largoIda4)
                .title(titulo)
                .snippet(descricao));

        titulo = "Rua da Liberdade";
        descricao = "Dil Móveis";
        googleMap.addMarker(new MarkerOptions()
                .position(largoIda5)
                .title(titulo)
                .snippet(descricao));

        titulo = "Avenida do Contorno";
        descricao = "Lava rápido - Borracharia";
        googleMap.addMarker(new MarkerOptions()
                .position(largoIda6)
                .title(titulo)
                .snippet(descricao));


        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        largoIda1,
                        largoIda2,
                        largoIda3,
                        largoIda4,
                        largoIda5,
                        largoIda6));

        polyline1.setTag("Largo Dom João - Ida");
        polyline1.setColor(COLOR_BLACK_ARGB);
        stylePolyline(polyline1);
//===========================================

        LatLng largoVolta1 = new LatLng(-18.2294067, -43.606682);
        LatLng largoVolta2 = new LatLng(-18.2393599, -43.6124544);
        LatLng largoVolta3 = new LatLng(-18.2415386, -43.6067309);
        LatLng largoVolta4 = new LatLng(-18.2437571, -43.6046512);
        LatLng largoVolta5 = new LatLng(-18.2470733, -43.6019483);

        titulo = "Avenida do contorno";
        descricao = "Trevo";
        googleMap.addMarker(new MarkerOptions()
                .position(largoVolta1)
                .title(titulo)
                .snippet(descricao));

        descricao = "Bonus";
        googleMap.addMarker(new MarkerOptions()
                .position(largoVolta2)
                .title(titulo)
                .snippet(descricao));

        titulo = "Avenida Silvio Felicio dos Santos";
        descricao = "Marque Center";
        googleMap.addMarker(new MarkerOptions()
                .position(largoVolta3)
                .title(titulo)
                .snippet(descricao));

        descricao = "Cultura Inglesa";
        googleMap.addMarker(new MarkerOptions()
                .position(largoVolta4)
                .title(titulo)
                .snippet(descricao));

        titulo = "Praça Sagrado Coração de Jesus";
        descricao = "Corpo de Bombeiro";
        googleMap.addMarker(new MarkerOptions()
                .position(largoVolta5)
                .title(titulo)
                .snippet(descricao));

        Polyline polyline2 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        largoVolta1,
                        largoVolta2,
                        largoVolta3,
                        largoVolta4,
                        largoVolta5));

        polyline2.setTag("Largo Dom João - Volta");
        polyline2.setColor(COLOR_BLUE_ARGB);
        stylePolyline(polyline2);
//===========================================

        LatLng rioGrande1 = new LatLng(-18.2395793, -43.5945181);
        LatLng rioGrande2 = new LatLng(-18.2371478, -43.5981840);
        LatLng rioGrande3 = new LatLng(-18.2364517, -43.6004465);
        LatLng rioGrande4 = new LatLng(-18.2295313, -43.6061029);

        titulo = "Rua Rio Grande";
        descricao = "Premoldados Diamante";
        googleMap.addMarker(new MarkerOptions()
                .position(rioGrande1)
                .title(titulo)
                .snippet(descricao));

        titulo = "Avenida Barão de Paraúna";
        descricao = "Copasa";
        googleMap.addMarker(new MarkerOptions()
                .position(rioGrande2)
                .title(titulo)
                .snippet(descricao));

        descricao = "Campo do Tijuco";
        googleMap.addMarker(new MarkerOptions()
                .position(rioGrande3)
                .title(titulo)
                .snippet(descricao));

        descricao = "Trevo";
        googleMap.addMarker(new MarkerOptions()
                .position(rioGrande4)
                .title(titulo)
                .snippet(descricao));

        Polyline polyline3 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        rioGrande1,
                        rioGrande2,
                        rioGrande3,
                        rioGrande4));

        polyline3.setTag("Rio Grande - Ida e Volta");
        polyline3.setColor(COLOR_GREEN_ARGB);
        stylePolyline(polyline3);
//===========================================

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(largoIda1, 14));
        googleMap.setOnPolylineClickListener(this);
    }

    private void stylePolyline(Polyline polyline) {
        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setJointType(JointType.ROUND);
    }

    @Override
    public void onPolylineClick(Polyline polyline) {
        Toast.makeText(this, "Rota do " + polyline.getTag().toString(),
                Toast.LENGTH_SHORT).show();
    }

}
