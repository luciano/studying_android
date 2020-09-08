package dominando.android.carros;

import android.app.Application;
import android.util.Log;

/**
 *
 * Created by Luciano on 02/10/2015.
 */
public class CarrosApplication extends Application {

    private static final String TAG = "CarrosApplication";

    private static CarrosApplication instance = null;

    public static CarrosApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "CarrosApplication.onCreate");
        // salva a instancia para termos acesso como Singleton
        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "CarrosApplication.onTerminate");
    }
}
