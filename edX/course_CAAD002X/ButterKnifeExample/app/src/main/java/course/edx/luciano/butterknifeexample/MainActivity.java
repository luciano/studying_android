package course.edx.luciano.butterknifeexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView text;

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // we need to add “ButterKnife.bind(this);” inside the On Create method
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void clickButton() {
        text.setText("Button pressed!");
    }

}
