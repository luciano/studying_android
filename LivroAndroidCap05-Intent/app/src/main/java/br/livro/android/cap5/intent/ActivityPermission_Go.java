package br.livro.android.cap5.intent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Iniciar uma Activity com permiss�o
 * 
 * @author ricardo
 *
 */
public class ActivityPermission_Go extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		TextView text = new TextView(this);
		text.setText("Se voce esta vendo isto..");
		setContentView(text);
	}
}
