package br.com.liugsilva.semfogo.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.facebook.FacebookSdk;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.liugsilva.semfogo.Arquivos;
import br.com.liugsilva.semfogo.Camera;
import br.com.liugsilva.semfogo.Debug;
import br.com.liugsilva.semfogo.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * https://github.com/hdodenhof/CircleImageView
 *
 * http://www.iai.art.br/radar/android-development-carregar-imagem-da-galeria-ou-camera/
 * http://pt.stackoverflow.com/questions/13247/como-abrir-galeria-do-android
 * http://stackoverflow.com/questions/6147884/onactivityresult-not-being-called-in-fragment
 *
 * http://stackoverflow.com/questions/5841710/get-user-image-from-facebook-graph-api
 *  Foto por url: http://graph.facebook.com/959034504190251/picture?type=large
 *
 *  http://respostas.guj.com.br/11197-salvar-imagem-bitmap
 */
public class PerfilActivityFragment extends Fragment {

    boolean editar = true;
    private Context context;
    private CircleImageView circleImageView;
    private EditText tvNome;
    private EditText tvEmail;
    private EditText tvTelefone;
    private EditText tvSenha;
    private Button btnEditDados;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.perfil_layout, container, false);

        context = getActivity();

        btnEditDados = (Button) layout.findViewById(R.id.edit_dados);
        circleImageView = (CircleImageView) layout.findViewById(R.id.profile_image);
        tvNome = (EditText) layout.findViewById(R.id.tv_perfil_nome);
        tvEmail = (EditText) layout.findViewById(R.id.tv_perfil_email);
        tvTelefone = (EditText) layout.findViewById(R.id.tv_perfil_telefone);
        tvSenha = (EditText) layout.findViewById(R.id.tv_perfil_senha);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("cadastro", FragmentActivity.MODE_PRIVATE);

        //Debug.log("Shared inicial: " + sharedPreferences.toString());

        String nome = sharedPreferences.getString("nome", null);
        String email = sharedPreferences.getString("email", null);
        String telefone = sharedPreferences.getString("telefone", null);
        String senha = sharedPreferences.getString("senha", null);
        String id = sharedPreferences.getString("id", null);
        final String picture = sharedPreferences.getString("picture", null);

        Debug.log("Shared: nome: " + sharedPreferences.getString("nome", null));
        Debug.log("Shared: email: " + sharedPreferences.getString("email", null));
        Debug.log("Shared: telefone: " + sharedPreferences.getString("telefone", null));
        Debug.log("Shared: endereco: " + sharedPreferences.getString("senha", null));

        if (nome != null) {
            tvNome.setText(nome);
            tvEmail.setText(email);
            tvTelefone.setText(telefone == null ? "" : telefone);
            tvSenha.setText(senha == null ? "" : senha);

            File picsDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM);
            File file = new File(picsDir.getAbsolutePath() + "/SemFogo/Profile/", "perfil_picture.jpg");
            //boolean mkdisr = file.mkdir();
            //Debug.log("Mkdisr inicial = " + mkdisr);
            Debug.log("File path: " + file.getAbsoluteFile());

            if (file.exists() && file.isFile()) {
                Debug.log("File picture: " + file.isFile());
                circleImageView.setImageBitmap(Arquivos.lerPicture(context));

            } else
            if (picture != null) {

                Debug.log("Picasso picture: " + picture);

                Picasso.with(getActivity().getApplicationContext()).load(picture)
                        .placeholder(com.facebook.R.drawable.com_facebook_profile_picture_blank_square).error(R.drawable.icon_perfil)
                        .into(circleImageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            Debug.log("Picasso escrevendo.");
                                            escreveImagens(Picasso.with(getActivity().getApplicationContext()).load(picture).get());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                            }

                            @Override
                            public void onError() {
                                Debug.log("Picasso error.");
                            }
                        });
            }
        }

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 1);
            }
        });

        btnEditDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editar) {
                    editarCampos(editar);
                    editar = !editar;
                    btnEditDados.setText("salvar");

                } else {
                    editar = !editar;
                    btnEditDados.setText("editar");

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("cadastro", FragmentActivity.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    String nome = tvNome.getText().toString();
                    String email = tvEmail.getText().toString();
                    String telefone = tvTelefone.getText().toString();
                    String senha = tvSenha.getText().toString();

                    Debug.log("Editando: nome: " + nome);
                    Debug.log("Editando: email: " + email);
                    Debug.log("Editando: telefone: " + telefone);
                    Debug.log("Editando: senha: " + senha);

                    editor.putString("nome", nome);
                    editor.putString("email", email);
                    editor.putString("telefone", telefone);
                    editor.putString("senha", senha);
                    boolean edit = editor.commit();
                    Debug.log("SharedPreferences edit: " + edit);
                    editarCampos(!editar);

                    Debug.log("Shared: nome: " + sharedPreferences.getString("nome", null));
                    Debug.log("Shared: email: " + sharedPreferences.getString("email", null));
                    Debug.log("Shared: telefone: " + sharedPreferences.getString("telefone", null));
                    Debug.log("Shared: senha: " + sharedPreferences.getString("senha", null));

                }
            }
        });

        ImageButton ibLigarBombeiros = (ImageButton) layout.findViewById(R.id.ib_ligar_bombeiros);
        ibLigarBombeiros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FuncionamentoActivityFragment.bombeiros(context);
            }
        });

        ImageButton btnFacebook = (ImageButton) layout.findViewById(R.id.btn_facebook);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivityFragment.facebook(context);
            }
        });

        ImageButton btnInstagram = (ImageButton) layout.findViewById(R.id.btn_instagram);
        btnInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivityFragment.instagram(context);
            }
        });

        ImageButton btnLinkedin = (ImageButton) layout.findViewById(R.id.btn_linkedin);
        btnLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivityFragment.linkedin(context);
            }
        });

        return layout;
    }

    private void editarCampos(boolean editar) {
        if (editar) {
            tvNome.setEnabled(true);
            tvEmail.setEnabled(true);
            tvTelefone.setEnabled(true);
            tvSenha.setEnabled(true);
        } else {
            tvNome.setEnabled(false);
            tvEmail.setEnabled(false);
            tvTelefone.setEnabled(false);
            tvSenha.setEnabled(false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Debug.log("OnActivityResult: resultCode: " + resultCode);

        if (resultCode == FragmentActivity.RESULT_OK) {
            Debug.log("OnActivityResult: atualizando foto.");
            Uri selectedImageUri = data.getData();
    //        Log.d("LogLS", "Uri: " + selectedImageUri);

            String selectedImagePath = getPath(selectedImageUri);
    //        Log.d("LogLS", "String: " + selectedImagePath);

            final Bitmap bitmap = Camera.carregarImagem(selectedImagePath, circleImageView.getWidth(), circleImageView.getHeight());
            circleImageView.setImageBitmap(bitmap);
            new Thread() {
                @Override
                public void run() {
                    try {
                        Debug.log("OnActivityResult: salvando foto.");
                        escreveImagens(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else {
            Debug.log("OnActivityResult: Cancelado.");
        }

    }

//    public static Bitmap carregarImagem(String file, int width, int height){
//        if (width == 0 || height == 0) return null;
//
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(file, options);
//
//        int larguraFoto = options.outWidth;
//        int alturaFoto = options.outHeight;
//
//        int escala = Math.min(
//                larguraFoto / width,
//                alturaFoto / height
//        );
//
//        options.inJustDecodeBounds = false;
//        options.inSampleSize = escala;
//
//
//        return BitmapFactory.decodeFile(file, options);
//    }

    /**
     * auxiliar para saber o caminho de uma imagem URI
     */
    public String getPath(Uri uri) {

        if( uri == null ) {
            // TODO realizar algum log ou feedback do utilizador
            return null;
        }


        // Tenta recuperar a imagem da media store primeiro
        // Isto só irá funcionar para as imagens selecionadas da galeria

        String[] projection = {MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }

        return uri.getPath();
    }

    public static void escreveImagens(Bitmap bmp){
        if (bmp == null)
            Debug.log("Bitmap null");
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            byte[] bytes = stream.toByteArray();

            File picsDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM);
            File imageFile = new File(picsDir + "/SemFogo/Profile/", "perfil_picture.jpg");
            if (!imageFile.exists()) {
                boolean create = imageFile.createNewFile();
                Debug.log("escreveImagens(): picture nao exite.\n Create = " + create);
            }

            File profileFile = new File(picsDir + "/SemFogo/Profile/", ".nomedia");
            if (!profileFile.exists()) {
                boolean create = profileFile.createNewFile();
                Debug.log("escreveImagens(): nomedia nao exite.\n Create nomedia = " + create);
            }

            Debug.log("Escrevendo em: " + imageFile.getAbsoluteFile());

            imageFile.setReadable(true, false);


            if(!imageFile.exists()) {
                Debug.log("Escrevendo: File nao exite.");
            }

            FileOutputStream fos = new FileOutputStream(imageFile.getAbsolutePath());
            fos.write(bytes);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
