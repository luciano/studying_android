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
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * http://code.tutsplus.com/tutorials/quick-tip-add-facebook-login-to-your-android-app--cms-23837?ec_unit=dropdown-language
 *
 * https://developers.facebook.com/docs/android/graph
 * https://developers.facebook.com/docs/graph-api/reference/user
 * https://developers.facebook.com/docs/facebook-login/permissions
 */
public class GraphAPIActivity extends AppCompatActivity {

    private String UserID, AuthToken;
    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    ProfilePictureView profilePictureView;
//    private LinearLayout layoutTeste;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login_button_facebook);

        //teste fica com imagens com ?
//        layoutTeste = (LinearLayout) findViewById(R.id.teste);

        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);

        // se fosse pegar  a imagem de pergim
        profilePictureView = (ProfilePictureView) findViewById(R.id.img);
        profilePictureView.setPresetSize(ProfilePictureView.LARGE);
        //profilePictureView.setProfileId(UserID);//imagem do perfil

        // se tiver logado, ele salva o token e nao precisa logar novamente
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            info.setText("Token: " + accessToken.getToken() + "\nID: " + accessToken.getUserId() + "\nExpirate: " + accessToken.getExpires().toString());

            requestData(accessToken);
            testeFriendsListRequest();
            testeFriendsRequest();
            testeHomeRequest();
            testeInboxRequest();
        }

        try {
            // ainda estou testando as permissoes, coloquei todas que estao no site do facebook
            loginButton.setReadPermissions("public_profile", "read_mailbox", "read_stream",
                    "user_friends",
                    "email",
                    "user_about_me",
                    "user_actions.books",
                    "user_actions.fitness",
                    "user_actions.music",
                    "user_actions.news",
                    "user_actions.video",
//                    "user_actions:{app_namespace}",
                    "user_birthday",
                    "user_education_history",
                    "user_events",
                    "user_games_activity",
                    "user_hometown",
                    "user_likes",
                    "user_location",
                    "user_managed_groups",
                    "user_photos",
                    "user_posts",
                    "user_relationships",
                    "user_relationship_details",
                    "user_religion_politics",
                    "user_tagged_places",
                    "user_videos",
                    "user_website",
                    "user_work_history",
                    "read_custom_friendlists",
                    "read_insights",
                    "read_audience_network_insights",
                    "read_page_mailboxes",
                    "pages_show_list",
                    "pages_manage_cta",
                    "ads_read");
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    // depois que faz login e da certo ele faz isso aqui
                    UserID = loginResult.getAccessToken().getUserId();
                    AuthToken = loginResult.getAccessToken().getToken();

                    requestData(loginResult.getAccessToken());

                }

                @Override
                public void onCancel() {
                    info.setText("Login attempt canceled.");
                }

                @Override
                public void onError(FacebookException e) {
                    info.setText("Login attempt failed. Erro: " + e.toString());
                }

            });
        } catch (FacebookException e) {
            Log.i("LogLS", "Erros: " + e.toString());
        }

    }

    // faz requisição e recebe um JSONObject
    private void requestData(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {

                            info.setText(
                                    "\nID: " + object.getString("id") +
                                    "\nNome: " + object.getString("name") +
                                    "\nEmail: " + object.getString("email") +
                                    "\nGender: " + object.getString("gender") +
                                    "\nBirthDay: " + object.getString("birthday") +
                                    "\nfirst_name: " + object.getString("first_name") +
                                    "\nlast_name: " + object.getString("last_name") +
                                    "\nlink: " + object.getString("link") +
                                    "\nlocation: " + object.getJSONObject("location").getString("name") +
                                    "\nlocale: " + object.getString("locale") +
                                    "\nupdated_time: " + object.getString("updated_time")
                            );

                            profilePictureView.setProfileId(object.getString("id"));
                            profilePictureView.getDrawingCache();

                            Log.d("LogLS", "Object: " + object.toString() + "\nResponse: " + response.toString());
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday,first_name" +
                ",last_name,link,location,locale,updated_time,about,picture" +
                ",age_range,bio,context,currency,education,favorite_athletes" +
                ",favorite_teams,hometown,relationship_status,religion" +
                ",quotes,work,public_key,cover"
        );
        request.setParameters(parameters);
        request.executeAsync();
    }

    // mostra apenas a quantidade de amigos
    private void testeFriendsRequest() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.d("LogLS", "Friends Response.Object: " + response.getJSONObject());
                        Log.d("LogLS", "Friends Response: " + response);
                    }
                }
        ).executeAsync();
    }

    // mostra alguns IDs e pagina pra proximos json
    private void testeFriendsListRequest() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/"+ AccessToken.getCurrentAccessToken().getUserId() + "/friendlists",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.d("LogLS", "FriendLists Response.Object: " + response.getJSONObject());
                        Log.d("LogLS", "Friendlists Response: " + response);

                        // teste mas nao pega as imagens
//                        try {
//                            JSONArray jsonArray = response.getJSONObject().getJSONArray("data");
//                            for (int i = 0; i < jsonArray.length(); ++i) {
//                                String s = jsonArray.getJSONObject(i).getString("id");
//                                ProfilePictureView pictureView = new ProfilePictureView(GraphAPIActivity.this);
//                                pictureView.setPresetSize(ProfilePictureView.SMALL);
//                                pictureView.setProfileId(s);
//                                layoutTeste.addView(pictureView);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
        ).executeAsync();
    }

    private void testeHomeRequest() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/home",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.d("LogLS", "Home Response.Object: " + response.getJSONObject());
                        Log.d("LogLS", "Home Response: " + response);
                    }
                }
        ).executeAsync();
    }

    private void testeInboxRequest() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/inbox",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.d("LogLS", "Home Response.Object: " + response.getJSONObject());
                        Log.d("LogLS", "Home Response: " + response);
                    }
                }
        ).executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
// Exemplo de objeto retornado
//Object: {
//        "picture":{"data":{"url":"https:\/\/fbcdn-profile-a.akamaihd.net\/hprofile-ak-xaf1\/v\/t1.0-1\/p50x50\/11887969_900583823368653_4312923900073613335_n.jpg?oh=91403ceb077ca7a2efe55b3d65d53947&oe=57215115&__gda__=1456937122_9c6df2894aa6dd745ecf26f090bcc44b","is_silhouette":false}}
//        "birthday":"02\/25\/1995",
//        "hometown":{"id":"105552089477252","name":"Diamantina, Minas Gerais"},
//        "location":{"id":"146844145368532","name":"Couto de Magalhães de Minas"},
//        "locale":"en_US",
//        "link":"https:\/\/www.facebook.com\/app_scoped_user_id\/959034504190251\/",
//        "education":[{"type":"College","school":{"id":"104857209614204","name":"Diamantina - UFVJM"}}],
//        "relationship_status":"Single",
//        "updated_time":"2015-08-19T14:17:24+0000",
//        "age_range":{"min":18,"max":20},
//        "currency":{"usd_exchange_inverse":4.0171687364,"usd_exchange":0.24893154,"user_currency":"BRL","currency_offset":100},
//        "religion":"Testemunha de Jeová",
//        "id":"959034504190251",
//        "first_name":"Luciano",
//        "cover":{"source":"https:\/\/fbcdn-sphotos-c-a.akamaihd.net\/hphotos-ak-xtl1\/v\/t1.0-9\/s720x720\/11866374_900588050034897_339949064860829382_n.jpg?oh=0a6e9d6359e3159fa4bf635cdf93c1ef&oe=56D7C01D&__gda__=1461095157_84ffd6f09a642cfda031bb7b3b5826a6","id":"900588050034897","offset_y":0},
//        "email":"lucianodtna@gmail.com",
//        "name":"Luciano Silva",
//        "context":{"id":"dXNlcl9jb250ZAXh0OgGQAByCmGSWPFRZCpRUocPYu5mhk1OgH5A9hDPceeVhX9JMCa70suyLYZA8DyD20Qn4SWyY6JULJqrjsAZA4OktXwjOwcOqsVsu1G3AuBEM3RGiuUZD","mutual_likes":{"summary":{"total_count":100},"data":[{"id":"131201123703426","name":"Downs Hacker"},{"id":"852255664873240","name":"Next Step - Empresa Júnior de Sistemas de Informação"},{"id":"138982069490613","name":"GUJ.com.br - Grupo de Usuários Java"},{"id":"639813036038511","name":"Neri Neitzke"},{"id":"255159214606919","name":"Ricardo Lecheta"},{"id":"1030928773595802","name":"Diamond Valley"},{"id":"1511883025775666","name":"Partiu"},{"id":"470146673075338","name":"San Pedro Valley"},{"id":"923233494377611","name":"Ministério da Ciência, Tecnologia e Inovação"},{"id":"253098401432387","name":"SBGames"},{"id":"1740453602847319","name":"AndroidPro"},{"id":"1475450702721106","name":"Não chora é #Print."},{"id":"213425875361184","name":"Câmera Record"},{"id":"419276404838494","name":"Be Mundus - Brazil Europe Erasmus Mundus"},{"id":"263698113830938","name":"EBW Plus"},{"id":"1728412624053250","name":"Startup Weekend Diamantina"},{"id":"1655020501445903","name":"Sindicato Dos Docentes UFVJM"},{"id":"455220987888129","name":"Seu guarda-roupa"},{"id":"310387485643000","name":"Engenharia Depressão"},{"id":"172903046191125","name":"Primeiros Acordes"},{"id":"1622229188060466","name":"Comando Local Greve - Diamantina"},{"id":"157534474346209","name":"UpInside Treinamentos"},{"id":"733239280131809","name":"Churros QD Diamantina"},{"id":"113688725384567","name":"IEL Estágio - Minas"},{"id":"386318038083108","name":"Record News"}],"paging":{"cursors":{"after":"Mzg2MzE4MDM4MDgzMTA4","before":"MTMxMjAxMTIzNzAzNDI2"},"next":"https:\/\/graph.facebook.com\/v2.5\/dXNlcl9jb250ZAXh0OgGQoJEv9IvojhGJ8GMzo3wpTYzR2a3TC3Q9StQBs1KATeY9IaLLFmrUzb0xsdPMM95On41dTHBHZB92W920AjZAlOgUfJBI4ig9NZBLD3Vn6GyaJIZD\/mutual_likes?access_token=CAAXvI7b97TIBABbfGJ5877Jlyj8dG4b0j1ZAfFYOC6ZAZC4ZAnRbwGDkggWVOdZBmtyWFz1yBfUWVSmhBogtrZAT8FetnPZAihjyOiXcQkRbhJQtrgudtBvi1B1bxcHOmJrYi15pZAMZABElcRLBp0ZB8jG0hnb5ybk7E00HvRn0C6CiyGjIajdwnk4HRUgVbw7tQZD&limit=25&after=Mzg2MzE4MDM4MDgzMTA4"}},"mutual_friends":{"summary":{"total_count":386},"data":[]}},
//        "last_name":"Silva",
//        "gender":"male",
//        "favorite_athletes":[{"id":"1525578657660238","name":"Futsal feminino de Diamantina"}]}