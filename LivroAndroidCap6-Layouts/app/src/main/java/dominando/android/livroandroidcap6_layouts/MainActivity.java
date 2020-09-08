package dominando.android.livroandroidcap6_layouts;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplo livro Google Android
 *
 * Criado em: 29/08/2015
 *
 * Ultima modificação: 07/09/2015
 */

public class MainActivity extends ListActivity {

    int layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] opcoes = new String[] {
                "Exemplo wrap_content",
                "Exemplo match_parent",
                "Exemplo match_parent_imagem",
                "Exemplo textview_wrap_content",
                "FrameLayout 1",
                "FrameLayout 2",
                "LinearLayout 1",
                "LinearLayout 2",
                "LinearLayout Gravity",
                "LinearLayout Weight",
                "LinearLayout Weight 2",
                "TableLayout shrink",
                "TableLayout stretch",
                "TableLayout Formulário",
                "GridLayout",
                "Relative Layout",
                "LinearLayout Aninhado",
                "LinearLayout API",
                "TableLayout API",
                "ScrollView",
                "Default"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        switch (position) {
            case 0:
                layout = R.layout.exemplo_wrap_content;
                break;
            case 1:
                layout = R.layout.exemplo_match_parent;
                break;
            case 2:
                layout = R.layout.exemplo_match_parent_imagem;
                break;
            case 3:
                layout = R.layout.exemplo_textview_wrap_content;
                break;
            case 4:
                layout = R.layout.exemplo_frame_layout_1;
                break;
            case 5:
                layout = R.layout.exemplo_frame_layout_2;
                break;
            case 6:
                layout = R.layout.exemplo_linear_layout_1h;
                break;
            case 7:
                layout = R.layout.exemplo_linear_layout_1v;
                break;
            case 8:
                layout = R.layout.exemplo_linear_layout_2_gravity;
                break;
            case 9:
                layout = R.layout.exemplo_linear_layout_3_weight;
                break;
            case 10:
                layout = R.layout.exemplo_linear_layout_4_weight;
                break;
            case 11:
                layout = R.layout.exemplo_table_layout_shrink;
                break;
            case 12:
                layout = R.layout.exemplo_table_layout_strech;
                break;
            case 13:
                layout = R.layout.exemplo_table_layout_form;
                break;
            case 14:
                layout = R.layout.exemplo_grid_layout;
                break;
            case 15:
                layout = R.layout.exemplo_relative_layout;
                break;
            case 16:
                layout = R.layout.exemplo_linear_layout_form_aninhado;
                break;
            case 17:
                layout = 1;
                break;
            case 18:
                layout = 2;
                break;
            case 19:
                layout = R.layout.exemplo_scroll_view;
                break;
            default:
                layout = R.layout.activity_layout;
        }
        startActivity(new Intent(this, LayoutActivity.class).putExtra("layout", layout));
    }
}
