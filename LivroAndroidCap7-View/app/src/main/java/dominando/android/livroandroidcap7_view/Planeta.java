package dominando.android.livroandroidcap7_view;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Luciano on 08/09/2015.
 */
public class Planeta {
    public String nome;
    public int img;

    public Planeta(String nome, int img) {
        this.nome = nome;
        this.img = img;
    }

    public static List<Planeta> getPlanetas() {
        List<Planeta> planetas = new ArrayList<>();
        planetas.add(new Planeta("Mercúrio", R.drawable.planeta_01_mercurio));
        planetas.add(new Planeta("Vênus", R.drawable.planeta_02_venus));
        planetas.add(new Planeta("Terra", R.drawable.planeta_03_terra));
        planetas.add(new Planeta("Marte", R.drawable.planeta_04_marte));
        planetas.add(new Planeta("Jupter", R.drawable.planeta_05_jupiter));
        planetas.add(new Planeta("Saturno", R.drawable.planeta_06_saturno));
        planetas.add(new Planeta("Urano", R.drawable.planeta_07_urano));
        planetas.add(new Planeta("Neturno", R.drawable.planeta_08_neptuno));
        planetas.add(new Planeta("Plutão", R.drawable.planeta_09_plutao));

//        planetas.add(new Planeta("Jupter", R.drawable.jupiter));
//        planetas.add(new Planeta("Neturno", R.drawable.neturno));
//        planetas.add(new Planeta("Saturno", R.drawable.saturno));
//        planetas.add(new Planeta("Urano", R.drawable.urano));
//        planetas.add(new Planeta("Venus", R.drawable.venus));

        return planetas;
    }
}
