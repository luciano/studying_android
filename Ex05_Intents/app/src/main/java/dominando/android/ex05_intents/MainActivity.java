package dominando.android.ex05_intents;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplo livro Dominando Android
 *
 * Usando intents implicitas que chama aplicações do aparelho
 * para executarem determinadas funções. E como fazer com
 * que essa aplicacao responda a uma ação de intent customizada.
 *
 * Se existe intent pra tratar a ação o sistema android chama
 * essa aplicacao que trata a ação caso contrario lança uma
 * exceçao.
 *
 * Criado em: 13/08/2015
 *
 * Ultima modificação: 13/08/2015
 */

public class MainActivity extends ListActivity {

    private static final String[] OPCOES = {
            "Browser",
            "Realizando uma chamada",
            "Mapa",
            "Tocar musica",
            "SMS",
            "Compartilhar",
            "Minha ação 1",
            "Minha ação 2",
            "Sair"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, OPCOES);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Uri uri = null;
        Intent intent = null;

        switch (position) {
            // abrindo URL
            case 0:
                uri = Uri.parse("http://nglauber.blogspot.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            // realizar chamada
            case 1:
                uri = Uri.parse("tel:0153898597334");
                intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;

            // pesquisa posicao no mapa
            case 2:
                uri = Uri.parse("geo:0,0?q=Avenida+Diamantina, Couto+de+Magalhães+de+Minas");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            // executar musica (com base no meu celular S II )
            case 3:
                uri = Uri.parse("file:///storange/extSdCard/Music/Nova Pasta/4 Non Blondes  What's Up.mp3");
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "audio/mp3");
                startActivity(intent);
                break;

            // abrir editor de SMS
            case 4:
                uri = Uri.parse("sms:+553899444287");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.putExtra("sms_body", "Corpo do SMS");
                startActivity(intent);
                break;

            // compartilhar
            case 5:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Compartilhando via Intent.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;

            // ação customizada 1
            case 6:
                intent = new Intent("dominando.android.ACAO_CUSTOMIZADA");
                startActivity(intent);
                break;

            // ação customizada 2
            case 7:
                uri = Uri.parse("produto://Notebook/Slim");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            default:
                finish();
        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
