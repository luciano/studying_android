package dominando.android.ex17_navegacao;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Usou classes SlidingTabLayout e SlidingTabStrip que podem ser baixadas: http://goo.gl/XlEce5
 *
 * Fazem parte do aplicativo do Google I/O 2014 mas ainda não estão no SDK e são recomendadas
 * para  usar abas na tela pois tem maior flexibilidade e podem ser adicionadas em qualquer
 * ponto do layout
 *
 * Foi alterado no metodo createDefaultTabView da classe SlidingTabLayout a
 * cor do texto da aba para branco
 */
public class TelaAbasActivity extends AppCompatActivity {

    ViewPager mViewPager;
    SlidingTabLayout mSlidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_abas);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        // define quantas abas existirao na tela e retorna o fragment para cada aba
        AbasPagerAdapter pagerAdapter = new AbasPagerAdapter(this, getSupportFragmentManager());

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // permite mudar de aba deslizando o conteudo na horizontal
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(pagerAdapter);

        // atribui o ViewPager ao SlidingTabLayout assim ao clicar na aba a pagina ViewPager
        // sera alterada da mesma forma
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        mSlidingTabLayout.setDistributeEvenly(true); // abas deverao ter mesmo tamanho
        mSlidingTabLayout.setViewPager(mViewPager);
    }

}
