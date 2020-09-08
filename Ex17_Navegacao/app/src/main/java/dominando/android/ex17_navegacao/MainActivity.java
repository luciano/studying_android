package dominando.android.ex17_navegacao;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplo do livro Dominando Android
 *
 * Criado em: 12/09/2015
 *
 * Ultima modificação em: 15/09/2015
 */

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicializa os componentes do layout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // obtem referencia do Toolbar e a define como barra de ações invocando metodo
        // setSupportActionBar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // atributo serve para controlar a abertura e fechamento do menu lateral
        // inicializamos o mDrawerToggle passando a activity, o DrawerLayout, Toolbar
        // e os textos para quando o menu estiver aberto e fechado respectivamente
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar
        , R.string.app_name, R.string.app_name) {

            // esses metodos são chamados quando menu lateral é aberto e fechado
            // o metodo supportInvalidadeOptionsMenu fará com que as ações da action bar sejam
            // reconstruidas
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                supportInvalidateOptionsMenu();
            }
        };

        // seta o toggle no mDrawerLayout e configura desse modo a ligação do DrawerLayout com a Toolbar
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // configurando a ListView que vai exibir as opções do menu lateral é uma ListViewm comum
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.opcoes));

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(this);

        // mostra primeira opção do menu na primeira execução
        if (savedInstanceState == null) {
            exibirItem(0);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        exibirItem(position);
    }

    // os dois metodos são chamados todas as vezes que as ações da action bar são reconstruidas
    // isso ocorre quando abre ou fecha o menu lareral
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // desabilita os itens de menu se o drawer estiver aberto, para fazer essa verificação
    // usa o metodo isDrawerOpen(view) do objeto mDrawerLayout impedindo que qualquer
    // ação da action abr seja clicada quando o menu lateral estiver aberto
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        atualizarTitulo(drawerOpen);

        return super.onPrepareOptionsMenu(menu);
    }

    // sincroniza o estado da Toolbar ocm o do mDrawerToggle com metodo syncState()
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    // verifica se clicou no toggle e em caso positivo retorna true
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // inicializa um PrimeiroNivelFragment que é adicionado ao FrameLayout definido no
    // arquivo de layout da activity e fecha o menu lateral com metodo closeDrawer(view)
    private void exibirItem(int position) {
        String selecionado = mDrawerList.getItemAtPosition(position).toString();
        Fragment fragment = PrimeiroNivelFragment.novaInstancia(selecionado);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    // atualiza titulo da Toolbar
    private void atualizarTitulo(boolean drawerOpen) {
        if (drawerOpen) {
            mToolbar.setTitle(R.string.app_name);
        } else {
            int posicaoAtual = mDrawerList.getCheckedItemPosition();
            String opcaoAtual = mDrawerList.getItemAtPosition(posicaoAtual).toString();
            mToolbar.setTitle(opcaoAtual);
        }
    }
}
