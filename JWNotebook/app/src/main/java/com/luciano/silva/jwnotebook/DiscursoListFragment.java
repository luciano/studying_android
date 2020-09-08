package com.luciano.silva.jwnotebook;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Luciano on 16/09/2015.
 */
public class DiscursoListFragment extends ListFragment
         {

    ListView mListView;
    List<Discurso> mList;
    DiscursoAdapter mDiscursoAdapter;

             @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        mList = new ArrayList<>();
        mList.add(new Discurso("Tema", "Orador", "Data", "Discurso"));

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = getListView();

        // View layout = LayoutInflater.from(getActivity()).inflate(R.layout.list_empty, null);
        // mListView.setEmptyView(layout.findViewById(R.id.vaziaView));
        setEmptyText("Nenhum discurso foi encontrado.\nFaça novas anotações clicando em + no topo da tela.");

        mDiscursoAdapter = new DiscursoAdapter(getActivity(), mList);
        setListAdapter(mDiscursoAdapter);
    }

     @Override
     public void onListItemClick(ListView l, View v, int position, long id) {
         super.onListItemClick(l, v, position, id);

     }
 }
