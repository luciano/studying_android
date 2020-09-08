package dominando.android.testes.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Pega os logs do ciclo de vida do Fragment
 * <p>
 * Criado em: 07/10/2015
 * <p>
 * Ultima modificaçao: 07/10/2015
 */

public class BaseFragment extends Fragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        log(getClassName() + ".onViewCreated().");
    }

    @Override
    public void onStop() {
        super.onStop();
        log(getClassName() + ".onStop().");
    }

    @Override
    public void onStart() {
        super.onStart();
        log(getClassName() + ".onStart().");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        log(getClassName() + ".onSaveInstanceState().");
    }

    @Override
    public void onResume() {
        super.onResume();
        log(getClassName() + ".onResume().");
    }

    @Override
    public void onPause() {
        super.onPause();
        log(getClassName() + ".onPause().");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        log(getClassName() + ".onDetach().");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        log(getClassName() + ".onDestroyView().");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log(getClassName() + ".onDestroy().");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        log(getClassName() + ".onCreateView().");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log(getClassName() + ".onCreate().");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        log(getClassName() + ".onAttach().");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        log(getClassName() + ".onActivityCreated().");
    }

    public String getClassName() {
        // Retorna o nome da classe sem o pacote
        Class cls = ((Object) this).getClass();
        String s = cls.getSimpleName();
        return s;
    }

    protected void log(String msg) {
        Log.d("LogLS", msg);
    }
}
