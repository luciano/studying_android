package dominando.android.helloactionbar;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.widget.Toast;

/**
 *
 * Exemplo do livro Google Android
 *
 * Criado em: 14/08/2015
 *
 * Ultima modificação: 28/08/2015
 *
 */

public class MainActivity extends AppCompatActivity {

    boolean ativoCuston = false;
    boolean ativoTitle = false;
    boolean ativoIcon = false;
    boolean ativoDisplayTitle = false;
    boolean ativoDisplayHome = false;
    boolean ativoHomeUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // alguns metodos da classe android.app.ActionBar

        final ActionBar actionBar = getSupportActionBar();

        // cada botao vai executar um dos metodos apresentados no livro
        // pagina 130, 131

        // permite adicionar uma view customizada na actionbar
        // exemplo botao de busca
        Button btnCustom = (Button) findViewById(R.id.buttonCustomView);
        btnCustom.setText("setCustomView");
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ativoCuston = !ativoCuston;
                if (actionBar != null) {
                    actionBar.setDisplayShowCustomEnabled(ativoCuston);
                    actionBar.setDisplayShowTitleEnabled(!ativoCuston);
                    EditText editText = new EditText(MainActivity.this);
                    editText.setHint("Inserir texto");
                    editText.setHintTextColor(Color.WHITE);
                    editText.setTextColor(Color.BLUE);
                    editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    actionBar.setCustomView(editText);
                }
                if (!ativoCuston && actionBar != null) {
                    actionBar.setDisplayShowCustomEnabled(ativoCuston);
                    actionBar.setDisplayShowTitleEnabled(!ativoCuston);
                }
            }
        });

        // altera o titulo da action bar
        Button btnTitle = (Button) findViewById(R.id.buttonTitle);
        btnTitle.setText("setTitle");
        btnTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ativoTitle = !ativoTitle;
                if (actionBar != null && ativoTitle)
                    actionBar.setTitle("Capitulo 5");
                if (actionBar != null && !ativoTitle)
                    actionBar.setTitle(R.string.app_name);
            }
        });

        // altera o icone "home" que por padrao mostra icone do projeto
        Button btnIcon = (Button) findViewById(R.id.buttonIcon);
        btnIcon.setText("setIcon");
        btnIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ativoIcon = !ativoIcon;
                if (actionBar != null && ativoIcon)
                    actionBar.setIcon(android.R.drawable.title_bar);
                if (actionBar != null && !ativoIcon)
                    actionBar.setIcon(R.mipmap.ic_launcher);
            }
        });

        // configura se é para exibir o titulo na action bar
        Button btnDisplayTitle = (Button) findViewById(R.id.buttonDisplayTitle);
        btnDisplayTitle.setText("setDisplayShowTitleEnabled");
        btnDisplayTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ativoDisplayTitle = !ativoDisplayTitle;
                if (actionBar != null)
                    actionBar.setDisplayShowTitleEnabled(ativoDisplayTitle);
            }
        });

        // configura se é para exibir icone na action bar chamado home
        Button btnDisplayHome = (Button) findViewById(R.id.buttonDisplayHome);
        btnDisplayHome.setText("setDisplayShowHomeEnabled");
        btnDisplayHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ativoDisplayHome = !ativoDisplayHome;
                if (actionBar != null)
                    actionBar.setDisplayShowHomeEnabled(ativoDisplayHome);
            }
        });

        // exibe setinha pra esquerda "up navegation"
        Button btnHomeUp = (Button) findViewById(R.id.buttonHomeAsUp);
        btnHomeUp.setText("setDisplayHomeAsUpEnabled");
        btnHomeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ativoHomeUp = !ativoHomeUp;
                if (actionBar != null)
                    actionBar.setDisplayHomeAsUpEnabled(ativoHomeUp);
            }
        });


        /**
         * Action Provider
         *
         * De modo similar ao Action View, um action provider substitui algum botao por um layout
         * customizado. O action provider mais famoso de todos é o de compartlhar.
         *
         * Para configura-lo em algum botao da action bar basta colocar tag
         * app:actionProviderClass e informar alguma subclasse de android.view.ActionProvider
         *
         * Deve configurar Intent que sera utilizada para disparar a mensagem
         * ao sistema operacional a fim de compartilhar o conteudo
         *
          */

        /**
         *
         * Split action bar
         *
         * Deixa botoes da action bar na parte inferior da tela
         * só e possivel adiciona la no arquivo AndroidManifest.xml
         * acrescentando as tag necessarias
         *
         * E essa configuraçao foi descontinuada no Material Design no Android 5.0
         *
         * Funciona no tema Holo sem AppCompat
         */

        /**
         *
         */


        // depracated
        // usar Toolbar ou
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab().setText("Tab 1").setTabListener(new MyTabListener(this, 1)));

        actionBar.addTab(actionBar.newTab().setText("Tab 2").setTabListener(new MyTabListener(this, 2)));

        actionBar.addTab(actionBar.newTab().setText("Tab 3").setTabListener(new MyTabListener(this, 3)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // infla o menu com os botoes da action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // SearchView
        // obtem objeto SearchView
        MenuItem seachItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(seachItem);
        searchView.setOnQueryTextListener(onSearch());

        // ShareActionProvider
        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider share = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        share.setShareIntent(getDefaultIntent());


        return true;
    }

    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_TEXT, "Texto para compartilhar");
        return intent;
    }

    /**
     *
     * Quando usuario tocar no botao de busca, o campo de texto sera expandido para o usuario
     * fazer a busca. A classe SearchView faz isso e para utilizar coloca no arquivo XML
     * o atributo app:actionViewClass
     *
     * o SearchView é uma view customizada que pode ser inserida na action bar, ela é chamada de
     * action view
     * um Action View tem por objetivo substituir o botao da action bar por alguma view customizada.
     */
    private SearchView.OnQueryTextListener onSearch() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // usuario fez a busca
                toast("Buscou o texto: " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // mudou o texto digitado
                toast("Texto sendo digitado");
                return false;
            }
        };
    }

    // trata os eventos dos botoes
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_search) {
            toast("Clicou no Search!");
            return true;
        } else if (id == R.id.action_refresh) {
            toast("Clicou no Refresh!");
            return true;
        } else if (id == R.id.action_settings) {
            toast("Clicou no Settings!");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
