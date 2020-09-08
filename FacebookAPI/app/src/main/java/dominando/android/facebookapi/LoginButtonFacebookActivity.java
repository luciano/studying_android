package dominando.android.facebookapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Referencia:
 * http://code.tutsplus.com/tutorials/quick-tip-add-facebook-login-to-your-android-app--cms-23837?ec_unit=dropdown-language
 */
public class LoginButtonFacebookActivity extends AppCompatActivity {

    private String UserID, AuthToken;
    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login_button_facebook);


        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        //final ProfilePictureView profilePictureView = (ProfilePictureView) findViewById(R.id.img);
        //profilePictureView.setPresetSize(ProfilePictureView.LARGE);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            info.setText("Token: " + accessToken.getToken() + "\nID: " + accessToken.getUserId() + "\nExpirate: " + accessToken.getExpires().toString());
        }

        try {
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    UserID = loginResult.getAccessToken().getUserId();
                    AuthToken = loginResult.getAccessToken().getToken();

                    //Profile profile = Profile.getCurrentProfile(); //pega o perfil logado atualmente
                    //      profilePictureView.setProfileId(UserID);//imagem do perfil

                    info.setText(
                            "User ID: " + UserID + "\n" +
                                    "Auth Token: " + AuthToken + "\n"// +
                      //              "Primeiro Nome: " + profile.getFirstName() + "\n" +
                        //            "Ultimo Nome: " + profile.getLastName() + "\n" +
                          //          "Id: " + profile.getId()
                    );
                }

                @Override
                public void onCancel() {
                    info.setText("Login attempt canceled.");
                }

                @Override
                public void onError(FacebookException e) {
                    info.setText("Login attempt failed.");
                }

            });
        } catch (FacebookException e) {
            Log.i("LogLS", "Erros: " + e.toString());
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
