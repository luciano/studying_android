package dominando.android.hotel.ex19_sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Luciano on 11/09/2015.
 */
public class HotelDialogFragment extends DialogFragment
        implements TextView.OnEditorActionListener {

    private static final String DIALOG_TAG = "editDialog";
    private static final String EXTRA_HOTEL = "hotel";

    private EditText txtNome;
    private EditText txtEndereco;
    private RatingBar rtgEstrelas;
    private Hotel mHotel;

    public static HotelDialogFragment newInstance(Hotel hotel) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_HOTEL, hotel);

        HotelDialogFragment hotelDialogFragment = new HotelDialogFragment();
        hotelDialogFragment.setArguments(bundle);
        return hotelDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHotel = (Hotel) getArguments().getSerializable(EXTRA_HOTEL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_dialog_hotel, container, false);

        txtNome = (EditText) layout.findViewById(R.id.txtNome);
        txtNome.requestFocus();
        txtEndereco = (EditText) layout.findViewById(R.id.txtEndereco);
        txtEndereco.setOnEditorActionListener(this);
        rtgEstrelas = (RatingBar) layout.findViewById(R.id.rtbEstrelas);

        if (mHotel != null) {
            txtNome.setText(mHotel.nome);
            txtEndereco.setText(mHotel.endereco);
            rtgEstrelas.setRating(mHotel.estrelas);
        }

        // exibe o teclado virtual ao exibir o Dialog

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        getDialog().setTitle(R.string.acao_novo);

        return layout;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (EditorInfo.IME_ACTION_DONE == actionId) {
            Activity activity = getActivity();
            if (activity instanceof AoSalvarHotel) {
                if (mHotel == null) {
                    mHotel = new Hotel(txtNome.getText().toString(), txtEndereco.getText().toString(), rtgEstrelas.getRating());
                } else {
                    mHotel.nome = txtNome.getText().toString();
                    mHotel.endereco = txtEndereco.getText().toString();
                    mHotel.estrelas = rtgEstrelas.getRating();
                }

                AoSalvarHotel listener = (AoSalvarHotel) activity;
                listener.salvouHotel(mHotel);

                // fecha dialog
                dismiss();
                return true;
            }
        }

        return false;
    }

    public void abrir (FragmentManager fm) {
        if (fm.findFragmentByTag(DIALOG_TAG) == null) {
            show(fm, DIALOG_TAG);
        }
    }

    public interface AoSalvarHotel {
        void salvouHotel(Hotel hotel);
    }
}
