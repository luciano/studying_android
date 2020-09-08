package com.luciano.silva.oficinaandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.luciano.silva.oficinaandroid.adapter.MainExpandableAdapter;
import com.luciano.silva.oficinaandroid.textView.AutoLinkActivity;
import com.luciano.silva.oficinaandroid.textView.BackgroundActivity;
import com.luciano.silva.oficinaandroid.textView.FontesActivity;
import com.luciano.silva.oficinaandroid.textView.HTMLActivity;
import com.luciano.silva.oficinaandroid.textView.PaintFlagActivity;
import com.luciano.silva.oficinaandroid.webView.WebViewInterceptandoActivity;
import com.luciano.silva.oficinaandroid.webView.WebViewJavaScriptWithAndroidActivity;
import com.luciano.silva.oficinaandroid.webView.WebViewSimplesActivity;
import com.luciano.silva.oficinaandroid.webView.WebViewSwipeRefreshActivity;
import com.luciano.silva.oficinaandroid.wizard.AdMobBannerActivity;
import com.luciano.silva.oficinaandroid.wizard.AdMobIntersititialActivity;
import com.luciano.silva.oficinaandroid.wizard.BlankActivity;
import com.luciano.silva.oficinaandroid.wizard.EmptyActivity;
import com.luciano.silva.oficinaandroid.wizard.FullscreenActivity;
import com.luciano.silva.oficinaandroid.wizard.LoginActivity;
import com.luciano.silva.oficinaandroid.wizard.MapsActivity;
import com.luciano.silva.oficinaandroid.wizard.NavigationDrawerActivity;
import com.luciano.silva.oficinaandroid.wizard.ScrollingActivity;
import com.luciano.silva.oficinaandroid.wizard.SettingsActivity;
import com.luciano.silva.oficinaandroid.wizard.TabbedActionBarSpinnerActivity;
import com.luciano.silva.oficinaandroid.wizard.TabbedActionBarTabsActivity;
import com.luciano.silva.oficinaandroid.wizard.TabbedSwipeViewsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Fragment da Activity principal da aplicação
 *
 * Tem objetivo de mostrar os itens que podem ser
 * acessados para demonstração dos recursos.
 *
 * Ele inicia Activities de acordo com o que for
 * selecionado para abrir pelo usuario.
 */
public class MainActivityFragment extends Fragment {

    private HashMap<String, List<String>> dados;
    private List<String> keys;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ExpandableListView mExpandableListView = (ExpandableListView) view.findViewById(R.id.expandable_list_view);

        context = getActivity();
        dados = getDados();
        keys = new ArrayList<>(dados.keySet());

        MainExpandableAdapter mAdapter = new MainExpandableAdapter(dados);

        mExpandableListView.setAdapter(mAdapter);

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
               // Snackbar.make(v, "Clicado em: " + keys.get(groupPosition) + " > " + dados.get(keys.get(groupPosition)).get(childPosition), Snackbar.LENGTH_SHORT).show();


                // escolher activity para iniciar
                switch (keys.get(groupPosition)) {
                    // Wizard
                    case "Android Studio Wizard":
                        switch (childPosition) {
                            // Blank Activity
                            case 0:
                                startActivity(new Intent(context, BlankActivity.class));
                                break;
                            // Empty Activity
                            case 1:
                                startActivity(new Intent(context, EmptyActivity.class));
                                break;
                            // Fullscreen Activity
                            case 2:
                                startActivity(new Intent(context, FullscreenActivity.class));
                                break;
                            // AdMobBanner Activity
                            case 3:
                                startActivity(new Intent(context, AdMobBannerActivity.class));
                                break;
                            // AdMobIntersititial Activity
                            case 4:
                                startActivity(new Intent(context, AdMobIntersititialActivity.class));
                                break;
                            // Login Activity
                            case 5:
                                startActivity(new Intent(context, LoginActivity.class));
                                break;
                            // NavigationDrawer Activity
                            case 6:
                                startActivity(new Intent(context, NavigationDrawerActivity.class));
                                break;
                            // ScrollingActivity
                            case 7:
                                startActivity(new Intent(context, ScrollingActivity.class));
                                break;
                            // SettingsActivity
                            case 8:
                                startActivity(new Intent(context, SettingsActivity.class));
                                break;
                            // TabbedSwipeViewsActivity
                            case 9:
                                startActivity(new Intent(context, TabbedSwipeViewsActivity.class));
                                break;
                            // TabbedActionBarTabsActivity
                            case 10:
                                startActivity(new Intent(context, TabbedActionBarTabsActivity.class));
                                break;
                            // TabbedActionBarSpinnerActivity
                            case 11:
                                startActivity(new Intent(context, TabbedActionBarSpinnerActivity.class));
                                break;
                            // MapsActivity
                            case 12:
                                startActivity(new Intent(context, MapsActivity.class));
                                break;
                        }
                        break;

                    // TextView
                    case "TextView":
                        switch (childPosition) {
                            // Fontes
                            case 0:
                                startActivity(new Intent(context, FontesActivity.class));
                                break;
                            // AutoLink
                            case 1:
                                startActivity(new Intent(context, AutoLinkActivity.class));
                                break;
                            // PaintFlags
                            case 2:
                                startActivity(new Intent(context, PaintFlagActivity.class));
                                break;
                            //Background Personalizado
                            case 3:
                                startActivity(new Intent(context, BackgroundActivity.class));
                                break;
                            // HTML
                            case 4:
                                startActivity(new Intent(context, HTMLActivity.class));
                                break;
                        }
                        break;

                    // EditText NOVO
                    case "EditText":
                        switch (childPosition) {
                            case 0:
                                Toast.makeText(context, "Em Desenvolvimento", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;

                    // WebView
                    case "WebView":
                        switch (childPosition) {
                            // WebView Simples
                            case 0:
                                startActivity(new Intent(context, WebViewSimplesActivity.class));
                                break;
                            // WebView com Swipe to Refresh
                            case 1:
                                startActivity(new Intent(context, WebViewSwipeRefreshActivity.class));
                                break;
                            // WebView Interceptando paginas
                            case 2:
                                startActivity(new Intent(context, WebViewInterceptandoActivity.class));
                                break;
                            // WebView JavasCript interceptado pelo Android
                            case 3:
                                startActivity(new Intent(context, WebViewJavaScriptWithAndroidActivity.class));
                                break;
                        }
                        break;
                }

                return true;
            }
        });

        return view;
    }

    private HashMap<String, List<String>> getDados() {

        HashMap<String, List<String>> listHashMap = new HashMap<>();

//--------------------------------------------------------------------------------------------------
        // TextView OK - 12/10/2015
        List<String> listTextView = new ArrayList<>();
        listTextView.add("Fontes Diversificadas");
        listTextView.add("AutoLinks");
        listTextView.add("PaintFlags");
        listTextView.add("Background Personalizado");
        listTextView.add("Usando HTML");
//--------------------------------------------------------------------------------------------------

        List<String> listEditText = new ArrayList<>();
        listEditText.add("Teste 1");

//==================================================================================================
        // nao implementados
        List<String> listActivity = new ArrayList<>();
        listActivity.add("Teste 1");
        listActivity.add("Teste 2");

        List<String> listFragment = new ArrayList<>();
        listFragment.add("Teste 1");
        listFragment.add("Teste 2");
        listFragment.add("Teste 3");
        listFragment.add("Teste 4");
        listFragment.add("Teste 5");
//==================================================================================================

//--------------------------------------------------------------------------------------------------

        List<String> listWebView = new ArrayList<>();
        listWebView.add("WebView Simples");
        listWebView.add("WebView SwipeRefresh");
        listWebView.add("WebView Interceptar Requisição");
        listWebView.add("WebView JavaScript com Android");

//--------------------------------------------------------------------------------------------------
        // mostra activitys prontas do Android Studio OK - 12/10/2015
        List<String> listWizard = new ArrayList<>();
        listWizard.add("Blank Activity");
        listWizard.add("Empty Activity");
        listWizard.add("Fullscreen Activity");
        listWizard.add("AdMobBanner Activity");
        listWizard.add("AdMobIntersititial Activity");
        listWizard.add("Login Activity");
        listWizard.add("NavigationDrawer Activity");
        listWizard.add("Scrolling Activity");
        listWizard.add("Settings Activity");
        listWizard.add("TabbedSwipeViews Activity");
        listWizard.add("TabbedActionBarTabs Activity");
        listWizard.add("TabbedActionBarSpinner Activity");
        listWizard.add("Maps Activity");
//--------------------------------------------------------------------------------------------------

        // mostra em ordem aleatoria é um MAP!

        //listHashMap.put("Fragment", listFragment);
        //listHashMap.put("Activity", listActivity);

        listHashMap.put("Android Studio Wizard", listWizard);
        listHashMap.put("EditText", listEditText);
        listHashMap.put("TextView", listTextView);
        listHashMap.put("WebView", listWebView);

        return listHashMap;
    }
}
