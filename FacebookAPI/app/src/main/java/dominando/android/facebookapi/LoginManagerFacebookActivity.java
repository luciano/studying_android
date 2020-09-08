package dominando.android.facebookapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginManagerFacebookActivity extends AppCompatActivity {

    private TextView info;
    ProfilePictureView profilePictureView;
    private CallbackManager callbackManager;
    boolean ativo = true;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login_manager_facebook);

        info = (TextView)findViewById(R.id.info);
        button = (Button) findViewById(R.id.buttonLog);

        profilePictureView = (ProfilePictureView) findViewById(R.id.img);
        profilePictureView.setPresetSize(ProfilePictureView.LARGE);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            requestData(accessToken);
        }

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        requestData(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    private void requestData(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {

                            info.setText(
                                    "\nID: " + object.getString("id") +
                                            "\nNome: " + object.getString("name") +
                                            "\nEmail: " + object.getString("email")
                            );

                            profilePictureView.setProfileId(object.getString("id"));

                            Log.d("LogLS", "Object: " + object.toString() + "\nResponse: " + response.toString());
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void login(View view) {
        if (ativo) {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
            ativo = !ativo;
            button.setText("Logout");
        } else {
            LoginManager.getInstance().logOut();
            ativo = !ativo;
            button.setText("Login");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
