package dominando.android.hotel.ex20_provider;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;


/**
 * Created by Luciano on 11/09/2015.
 */
public class GenericDialogFragment extends DialogFragment
            implements DialogInterface.OnClickListener {

    private static final String EXTRA_ID = "id";
    private static final String EXTRA_MENSAGEM = "message";
    private static final String EXTRA_TITULO = "title";
    private static final String EXTRA_BOTOES = "buttons";
    private static final String DIALOG_TAG = "SimpleDialog";

    private int mDialogId;

    public static GenericDialogFragment novoDialog(
            int id, int title, int message, int[] buttonTexts ) {

        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_ID, id);
        bundle.putInt(EXTRA_TITULO, title);
        bundle.putInt(EXTRA_MENSAGEM, message);
        bundle.putIntArray(EXTRA_BOTOES, buttonTexts);

        GenericDialogFragment genericDialogFragment = new GenericDialogFragment();
        genericDialogFragment.setArguments(bundle);
        return genericDialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mDialogId = getArguments().getInt(EXTRA_ID);
        int titulo = getArguments().getInt(EXTRA_TITULO);
        int mensagem = getArguments().getInt(EXTRA_MENSAGEM);
        int[] botoes = getArguments().getIntArray(EXTRA_BOTOES);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(titulo);
        builder.setMessage(mensagem);

        switch (botoes.length) {
            case 3:
                builder.setNeutralButton(botoes[2], this);
            case 2:
                builder.setNegativeButton(botoes[1], this);
            case 1:
                builder.setPositiveButton(botoes[0], this);
        }

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Activity activity = getActivity();
        if (activity instanceof AoClicarNoDialog) {
            AoClicarNoDialog listernet = (AoClicarNoDialog) activity;
            listernet.aoClicar(mDialogId, which);
        }
    }

    public void abrir(FragmentManager fragmentManager) {
        Fragment dialog = fragmentManager.findFragmentByTag(DIALOG_TAG);

        if (dialog == null) {
            show(fragmentManager, DIALOG_TAG);
        }
    }

    public interface AoClicarNoDialog {
        void aoClicar(int id, int botao);
    }
}
