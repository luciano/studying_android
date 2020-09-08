package conversa.seria.talk;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class ListaVisual {

    static LinearLayout getListaVisual(Context context) {

        LinearLayout linearLayout = new LinearLayout(context);

        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        linearLayout.setPadding(16, 16, 16, 16);

        linearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.setBackgroundColor(0xffffffff);

        return linearLayout;
    }

    static ImageButton getImageButton(Context context) {

        ImageButton imageButton = new ImageButton(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(0, 6, 0, 0);

        params.gravity = Gravity.CENTER;

        imageButton.setLayoutParams(params);
        imageButton.setImageResource(R.drawable.ic_add_circle_outline_black_36dp);
        imageButton.setBackgroundColor(0xffffffff);
        imageButton.setPadding(15, 15, 15, 15);

        return imageButton;
    }

}

/*

        android:layout_gravity="center"

*/