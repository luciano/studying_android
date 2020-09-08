package br.com.liugsilva.semfogo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.liugsilva.semfogo.R;
import br.com.liugsilva.semfogo.Registro;

/**
 *
 */
public class UltimosRegistrosDetalhesFragment extends Fragment {

    Context context;
    // Map
    GoogleMap googleMap;
    LatLng latLng;

    private SupportMapFragment mapFragment;
    private GoogleMap mMapView;

    Registro mRegistro;

    // recebe parametros
    public static UltimosRegistrosDetalhesFragment newInstance(Registro registro) {
        Bundle extras = new Bundle();
        extras.putSerializable(Registro.REGISTRO, registro);

        UltimosRegistrosDetalhesFragment fragment = new UltimosRegistrosDetalhesFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRegistro = (Registro) getArguments().getSerializable(Registro.REGISTRO);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mapFragment).commit();
        }
    }

    // tentar fazer isso aqui em AsyncTask
    @Override
    public void onResume() {
        super.onResume();
        if (googleMap == null) {
            // googleMap = mapFragment.getMap();

            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                    double latitude = Double.parseDouble(mRegistro.getLatitude());
                    double longiitude = Double.parseDouble(mRegistro.getLongitude());

                    //latLng = new LatLng(-18.073951228134856, -43.4722351368883);
                    latLng = new LatLng(latitude, longiitude);
                    atualizarMapa(googleMap);
                }
            });
//            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//
//            latLng = new LatLng(-18.073951228134856, -43.4722351368883);
//            atualizarMapa();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // usa dados recebidos para mostrar
        View layout =  inflater.inflate(R.layout.fragment_ultimos_registros_detalhes, container, false);

        context = getActivity();

        TextView tvLocalizacao = (TextView) layout.findViewById(R.id.tv_ultimos_registros_detalhes_localizacao);
        TextView tvHorario = (TextView) layout.findViewById(R.id.tv_ultimos_registros_detalhes_horario);
        TextView tvData = (TextView) layout.findViewById(R.id.tv_ultimos_registros_detalhes_data);
        TextView tvInformacao = (TextView) layout.findViewById(R.id.tv_ultimos_registros_detalhes_adicional);
        Button buttonImagens = (Button) layout.findViewById(R.id.ultimos_registros_detalhes_imagem);

        //GridView gvImagens = (GridView) layout.findViewById(R.id.gridview);

//        List<Registro> listLinks = new ArrayList<>();
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));
//        listLinks.add(new Registro("", "", "", "", "", "", null));

        //gvImagens.setAdapter(new UltimosRegistrosGridAdapter(getContext(), listLinks));

        tvLocalizacao.setText(mRegistro.getLocalizacao().isEmpty() ? "Localização mostrada no Mapa!" : mRegistro.getLocalizacao());
        tvHorario.setText(mRegistro.getHorario());
        tvData.setText(mRegistro.getData());
        tvInformacao.setText( mRegistro.getInformacao().isEmpty()? "Sem Informações Adicionais." : mRegistro.getInformacao());

        // fazer imagens em outro fragment
        buttonImagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> links = mRegistro.getLinksImagem();

                if (links.isEmpty() || links.get(0).equals("") ) {
                    Toast.makeText(context, "Não possui imagens!", Toast.LENGTH_SHORT).show();
                } else {
                    // ai pegou kkkkkk
                    // mostra outra tela
                }
            }
        });

        return layout;
    }

    private void atualizarMapa(GoogleMap googleMap) {
        this.googleMap = googleMap;
        // zoom
        this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));

        // adicionar ponto no mapa
        this.googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Localização do Incêndio")
                .snippet("Incêncio combatido com ajuda do Sem Fogo!")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ultimos_registros_firefighter)));
    }

}
