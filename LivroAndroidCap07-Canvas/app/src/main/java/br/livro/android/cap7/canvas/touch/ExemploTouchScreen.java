package br.livro.android.cap7.canvas.touch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author Ricardo Lecheta
 *
 */
public class ExemploTouchScreen extends Activity {
	private static final String CATEGORIA = "livro";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(new TouchScreenView(this));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i(CATEGORIA, "ExemploTouchScreen.onTouchEvent");
		return super.onTouchEvent(event);
	}
}
