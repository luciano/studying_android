package dominando.android.volley;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout =  inflater.inflate(R.layout.fragment_main, container, false);

        ListView listView = (ListView) layout.findViewById(R.id.listView);

        listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getList()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getContext(), VolleyStringActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), VolleyJsonActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), VolleyStringSendctivity.class));
                        break;
                }
            }
        });

        return layout;
    }

    private List<String> getList() {
        ArrayList<String> list = new ArrayList<>();

        list.add("GET String");
        list.add("POST Json");
        list.add("POST String");

        return list;
    }
}
