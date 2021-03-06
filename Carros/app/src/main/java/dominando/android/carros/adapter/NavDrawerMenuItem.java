package dominando.android.carros.adapter;

import java.util.ArrayList;
import java.util.List;

import dominando.android.carros.R;

/**
 *
 * Created by Luciano on 02/10/2015.
 */
public class NavDrawerMenuItem {
    // titulo: R.string.xxx
    public int title;
    // Figura: R.drawable.xxx
    public int img;
    // para colocar um fundo cinza quando a linha está selecionada
    public boolean selected;

    public NavDrawerMenuItem(int title, int img) {
        this.title = title;
        this.img = img;
    }

    //cria a lista com os itens de menu
    public static List<NavDrawerMenuItem> getList() {

        List<NavDrawerMenuItem> list = new ArrayList<>();

        list.add(new NavDrawerMenuItem(R.string.carros, R.drawable.ic_drawer_carro));
        list.add(new NavDrawerMenuItem(R.string.site_livro, R.drawable.ic_drawer_site_livro));
        list.add(new NavDrawerMenuItem(R.string.configuracoes, R.drawable.ic_drawer_settings));

        return list;
    }
}
