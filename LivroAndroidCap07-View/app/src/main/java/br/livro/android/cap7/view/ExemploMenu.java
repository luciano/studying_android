package br.livro.android.cap7.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import br.livro.android.cap7.R;

/**
 * Exemplo de menu
 * 
 * @author ricardo
 * 
 */
@SuppressLint("NewApi")
public class ExemploMenu extends Activity {
	public static final int NOVO = 0;
	public static final int SALVAR = 1;
	public static final int EXCLUIR = 2;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		TextView view = new TextView(this);
		view.setText("Exemplo de Menu");
		setContentView(view);
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		// Via inflate xml
		getMenuInflater().inflate(R.menu.menu_exemplo, menu);
		
		// Adiciona três opções no menu (via API)
//		MenuItem item = menu.add(0, NOVO, 0, "Novo");
//		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//		item.setIcon(br.livro.android.cap7.R.drawable.novo);
//		item = menu.add(0, SALVAR, 0, "Salvar");
//		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//		item.setIcon(br.livro.android.cap7.R.drawable.salvar);
//		item = menu.add(0, EXCLUIR, 0, "Excluir");
//		item.setIcon(br.livro.android.cap7.R.drawable.excluir);
//		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case NOVO:
		case R.id.menu_novo:
			Toast.makeText(ExemploMenu.this, "Novo!", Toast.LENGTH_SHORT).show();
			return true;
		case SALVAR:
		case R.id.menu_salvar:
			Toast.makeText(ExemploMenu.this, "Salvar!", Toast.LENGTH_SHORT).show();
			return true;
		case EXCLUIR:
		case R.id.menu_excluir:
			Toast.makeText(ExemploMenu.this, "Excluir!", Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}
}
