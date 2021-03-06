package dominando.android.hotel.ex20_provider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 *
 * Created by Luciano on 09/09/2015.
 */
public class HotelDetalheFragment extends Fragment {

    public static final String TAG_DETALHE = "tagDetalhe";
    public static final String EXTRA_HOTEL = "hotel";

    TextView mTextNome;
    TextView mTextEndereco;
    RatingBar mRatingEstrelas;

    Hotel mHotel;

    ShareActionProvider mShareActionProvider;

    public static HotelDetalheFragment novaInstancia(Hotel hotel) {
        Bundle paramentros = new Bundle();
        paramentros.putSerializable(EXTRA_HOTEL, hotel);

        HotelDetalheFragment fragment = new HotelDetalheFragment();
        fragment.setArguments(paramentros);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHotel = (Hotel) getArguments().getSerializable(EXTRA_HOTEL);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_detalhe_hotel, container, false);

        mTextNome = (TextView) layout.findViewById(R.id.textNome);
        mTextEndereco = (TextView) layout.findViewById(R.id.textEndereco);
        mRatingEstrelas = (RatingBar) layout.findViewById(R.id.rtbEstrelas);

        if (mHotel != null) {
            mTextNome.setText(mHotel.nome);
            mTextEndereco.setText(mHotel.endereco);
            mRatingEstrelas.setRating(mHotel.estrelas);
        }
        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_hotel_detalhe, menu);

        MenuItem sharedItem = menu.findItem(R.id.action_shared);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(sharedItem);

        String texto = getString(R.string.texto_compartilhar, mHotel.nome, mHotel.estrelas);

        Intent it = new Intent(Intent.ACTION_SEND);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        it.setType("text/plain");
        it.putExtra(Intent.EXTRA_TEXT, texto);

        mShareActionProvider.setShareIntent(it);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.acao_editar) {
            Activity activity = getActivity();
            if (activity instanceof AoEditarHotel) {
                AoEditarHotel aoEditarHotel = (AoEditarHotel) activity;
                aoEditarHotel.aoEditarhotel(mHotel);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public Hotel getHotel() {
        return mHotel;
    }

    public interface AoEditarHotel {
        void aoEditarhotel(Hotel hotel);
    }
}
