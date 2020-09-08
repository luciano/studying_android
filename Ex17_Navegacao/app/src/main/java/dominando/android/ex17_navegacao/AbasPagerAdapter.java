package dominando.android.ex17_navegacao;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

public class AbasPagerAdapter extends FragmentPagerAdapter {

    String[] titulosAbas;
    TypedArray bgColors;
    TypedArray textColors;

    public AbasPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);

        titulosAbas = context.getResources().getStringArray(R.array.secoes);
        bgColors = context.getResources().obtainTypedArray(R.array.cores_bg);
        textColors = context.getResources().obtainTypedArray(R.array.cores_texto);

    }

    // retorna um fragmente de acordo com posição passada
    @Override
    public Fragment getItem(int position) {

        SegundoNivelFragment fragment = SegundoNivelFragment.novaInstancia(
                titulosAbas[position],
                bgColors.getColor(position, 0),
                textColors.getColor(position, 0)
        );

        return fragment;
    }

    // quantas abas terá
    @Override
    public int getCount() {
        return 3;
    }

    // titulo da aba
    @Override
    public CharSequence getPageTitle(int position) {
        Locale locale = Locale.getDefault();
        return titulosAbas[position].toUpperCase(locale);
    }
}
