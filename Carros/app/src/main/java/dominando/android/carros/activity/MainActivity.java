package dominando.android.carros.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.List;

import dominando.android.carros.R;
import dominando.android.carros.adapter.NavDrawerMenuAdapter;
import dominando.android.carros.adapter.NavDrawerMenuItem;
import dominando.android.carros.fragment.CarrosFragment;
import dominando.android.carros.fragment.SiteLivroFragment;
import livroandroid.lib.fragment.NavigationDrawerFragment;

/**
 * Exemplo Livro Google Android
 *
 * Criando em: 02/10/2015
 *
 * Ultima modificação: 02/10/2015
 */

public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private NavDrawerMenuAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();

        // Nav Drawer
        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drawer_fragment);

        // configura o drawer layout
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment.setUp(drawerLayout);
        drawerLayout.setStatusBarBackground(R.color.primary_dark);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            toast("Clicou em Sobre");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNavDrawerItemSelected(NavigationDrawerFragment navDrawerFrag, int position) {
        // metodo chamado ao selecionar um item do ListView
        List<NavDrawerMenuItem> list = NavDrawerMenuItem.getList();
        NavDrawerMenuItem selectedItem = list.get(position);

        // seleciona linha
        this.listAdapter.setSelected(position, true);
        if (position == 0){
            replaceFragment(new CarrosFragment());
        } else if (position == 1) {
            replaceFragment(new SiteLivroFragment());
        } else if (position == 2) {
            toast("Configurações");
        } else {
            Log.e("livroandroid", "Item de menu inválido");
        }
        //toast("Clicou no item: " + getString(selectedItem.title));
    }

    // adiciona o fragment no centro da tela
    private void replaceFragment(Fragment frag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_drawer_container, frag, "TAG").commit();
    }

    @Override
    public ListAdapter getNavDrawerListAdapter(NavigationDrawerFragment navDrawerFrag) {
        // Esse metodo deve retornar o adapter que vai preencher o ListView
        List<NavDrawerMenuItem> list = NavDrawerMenuItem.getList();

        // deixa o primeiro item selecionado
        list.get(0).selected = true;
        this.listAdapter = new NavDrawerMenuAdapter(this, list);

        return listAdapter;
    }

    @Override
    public NavigationDrawerFragment.NavDrawerListView getNavDrawerView(NavigationDrawerFragment navDrawerFrag, LayoutInflater inflater, ViewGroup container) {
        // deve retornar a view e o identificador do ListView
        View view = inflater.inflate(R.layout.nav_drawer_listview, container, false);

        // preencher o cabecalho com a foto, nome, email
        navDrawerFrag.setHeaderValues(view, R.id.listViewContainer,
                R.drawable.nav_drawer_header, R.drawable.ic_logo_user, R.string.nav_drawer_username,
                R.string.nav_drawer_email);

        return new NavigationDrawerFragment.NavDrawerListView(view, R.id.listView);
    }
}
