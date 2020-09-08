package br.livro.android.cap6.layout;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Exemplo do WebView
 * 
 * @author rlecheta
 *
 * Estudado em 26 de Março de 2015
 *
 * WebView: funcionamento é identico ao Browser do Android. Usado caso seja necessario abrir
 * uma pagina da internet dentro da aplicacao.
 */
public class ExemploWebView extends Activity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		WebView web = new WebView(this);

		WebSettings webSettings = web.getSettings();
		webSettings.setSavePassword(false);
		webSettings.setSaveFormData(false);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);

		web.loadUrl("http://www.google.com.br");

		setContentView(web);
	}
}