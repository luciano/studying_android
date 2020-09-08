package dominando.android.testes.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends ListFragment {

    private List<String> lista;
    private ArrayAdapter<String> mAdapter;
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        lista = getOpcoes();
        
        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, lista);
        
        setListAdapter(mAdapter);
    }

    private List<String> getOpcoes() {

        List<String> list = new ArrayList<>();

        list.add("Fragment1");
        list.add("");
        list.add("");

        return list;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new Fragment1();
                break;
            case 1:
                break;
            case 2:
                break;
        }


        Activity activity = getActivity();

        if (activity instanceof listaItemClick) {
            listaItemClick itemClicado = ((listaItemClick) activity);
            itemClicado.clickItem(frag);
        }
    }

    public interface listaItemClick {
        void clickItem(Fragment fragment);
    }
}
