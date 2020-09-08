package dominando.android.livroandroidcap7_view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 * Created by Luciano on 08/09/2015.
 */
public class MinhaCanvas extends View {

    // para definir a cor RGB
    private Paint pincelVermelho;
    private Paint pincelPreto;
    private  Paint pincelAzul;

    public MinhaCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor(Color.LTGRAY);

        // vermelho
        pincelVermelho = new Paint();
        pincelVermelho.setARGB(255, 255, 0, 0);

        // preto
        pincelPreto = new Paint();
        pincelPreto.setARGB(255, 0, 0, 0);

        // azul
        pincelAzul = new Paint();
        pincelAzul.setARGB(255, 0, 0, 255);

        // configura a view para receber foco e tratar eventos de teclado
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // desenha um quadrado
        canvas.drawRect(toPixels(20), toPixels(20), toPixels(100), toPixels(100), pincelAzul);

        // desenha uma linha
        canvas.drawLine(toPixels(100), toPixels(100), toPixels(200), toPixels(300), pincelPreto);

        // desenha um circulo
        canvas.drawCircle(toPixels(200), toPixels(300), toPixels(50), pincelVermelho);
    }

    // converter um valor em dp para pixels
    public float toPixels(float dp) {
        Resources r = getContext().getResources();
        float densidade = r.getDisplayMetrics().density; // densidade da tela

        int px = (int) (dp * densidade + 0.5f);

        return px;
    }
}
