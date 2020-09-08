package dominando.android.livroandroidcap7_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 *
 * Created by Luciano on 08/09/2015.
 */
public class TouchScreenView extends View {

    private Drawable img;
    int x, y;
    private boolean selecionou;
    private int larguraTela;
    private int alturaTela;
    private int larguraImg;
    private int alturaImg;

    public TouchScreenView(Context context) {
        super(context, null);

        // recupera a Imagem
        img = context.getResources().getDrawable(R.drawable.android);

        // recuperar a largura e altura da imagem
        larguraImg = img.getIntrinsicWidth();
        alturaImg = img.getIntrinsicHeight();

        // configura a View para receber o foco e tratar eventos de teclado
        setFocusable(true);
    }

    // chamando quando a tela é redimensionada, ou iniciada
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.larguraTela = w;
        this.alturaTela = h;

        x = w / 2 - (larguraTela / 2);
        y = h / 2 - (alturaTela / 2);

        Log.i("LogLS", "onSizeChaged x/y: " + x + "/" + y);
    }

    // desenha a tela
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // fundo branco
        Paint pincel = new Paint();
        pincel.setColor(Color.WHITE);

        canvas.drawRect(0, 0, larguraTela, alturaTela, pincel);

        // define os limites/area para desenhar
        img.setBounds(x, y, x + larguraImg, y + alturaImg);

        // desenha a imagem
        img.draw(canvas);
    }

    //move a imagem

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        Log.i("LogLS", "onTouchEvent: x/y > " + x + "/" + y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // inicia o movimento se pressionou a imagem
                selecionou = img.copyBounds().contains((int) x, (int) y);
                break;
            case MotionEvent.ACTION_MOVE:
                // arrasta o boneco
                if (selecionou) {
                    this.x = (int) x - (larguraImg / 2);
                    this.y = (int) y - (alturaImg / 2);
                }
                break;
            case MotionEvent.ACTION_UP:
                // finaliza movimento
                selecionou = false;
                break;
        }

        // o invalidate vai chamar o metodo onDraw(canvas) novamente
        invalidate();
        return true;
    }
}
