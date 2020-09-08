package br.com.liugsilva.semfogo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import br.com.liugsilva.semfogo.Debug;
import br.com.liugsilva.semfogo.R;
import br.com.liugsilva.semfogo.Registro;
import br.com.liugsilva.semfogo.fragment.UltimosRegistrosDetalhesFragment;
import br.com.liugsilva.semfogo.fragment.UltimosRegistrosFragment;

public class UltimosRegistrosActivity extends BaseActivity
        implements UltimosRegistrosFragment.OnItemListClick {

    FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransition;
    UltimosRegistrosFragment fragmentList;
    UltimosRegistrosDetalhesFragment fragmentDetalhes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimos_registros);

        fragmentList = new UltimosRegistrosFragment();

        // chama fragment dinamicamente
        fragmentManager = getSupportFragmentManager();
        fragmentTransition = fragmentManager.beginTransaction();
        fragmentTransition.replace(R.id.ultimos_registros_fragment_block, fragmentList);

//        if (fragmentTransition != null) {
//            fragmentTransition.addToBackStack(null);
//            //fragmentTransition.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        }
        fragmentTransition.commit();
    }

    @Override
    public void onItemClick(Registro registro) {
        Debug.log("Entrou onItemClick activity");
        // muda fragment com novas informações
        fragmentTransition = fragmentManager.beginTransaction();
        fragmentDetalhes = UltimosRegistrosDetalhesFragment.newInstance(registro);
        fragmentTransition.replace(R.id.ultimos_registros_fragment_block, fragmentDetalhes);
        if (fragmentTransition != null) {
            fragmentTransition.addToBackStack(null);
        }
        fragmentTransition.commit();
    }

    // testar aqui a mudanca de detalhes para lista
    // chamar novamente o fragment aqui vai da certo !
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Toast.makeText(this, "In the activity ", Toast.LENGTH_SHORT).show();
//    }
}
