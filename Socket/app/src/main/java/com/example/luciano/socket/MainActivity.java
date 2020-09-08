package com.example.luciano.socket;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MainActivity extends Activity {

    private static final String CAT = "depuracao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final EditText ip = (EditText) findViewById(R.id.ip);
        //final EditText porta = (EditText) findViewById(R.id.porta);
        //final EditText msg = (EditText) findViewById(R.id.msg);
        //final Button buttonOk = (Button) findViewById(R.id.buttonOk);
        //final Button buttonEnviar = (Button) findViewById(R.id.buttonEnviar);


        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.i(CAT, "Iniciando thread");
                            Socket cliente = new Socket();
                            Log.i(CAT, "Criou socket");
                            SocketAddress socketAddress = new InetSocketAddress("169.254.134.077", 12345);
                            Log.i(CAT, "Criou SocketAddress");
                            //cliente.bind(socketAddress);
                            Log.i(CAT, "Cliente: " + cliente.getInetAddress());
                            //cliente.connect(socketAddress); erro
                            Log.i(CAT, "Connect: " + cliente.isConnected());

                            //Toast.makeText(MainActivity.this, "Abriu porta com servidor [cliente]", Toast.LENGTH_LONG).show();

                            cliente.close();

                        } catch (Exception e) {
                            Log.e(CAT, "Erro : " + e.getMessage());
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Erro ao abrir porta com servidor", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        thread.start();


        //buttonOk.setOnClickListener((View v) -> {
        //try {
          //      String IP = ip.getText().toString();
          //      int PORTA = Integer.parseInt(porta.getText().toString());

          //      Log.i(CAT, "Ip: " + IP);
          //      Log.i(CAT, "Porta: " + PORTA);
                // roda ate aqui



                    // erro aki nao reconhece nem erros
                   // Socket s = new Socket();
                   // SocketAddress address = new InetSocketAddress(IP, PORTA);
                   // s.connect(address);
                   // Toast.makeText(MainActivity.this, "Abriu porta com servidor [s]", Toast.LENGTH_LONG).show();
                   // Log.i(CAT, "Abriu porta: " + s.getInetAddress() + ". Porta: " + s.getLocalPort());

//                    final Socket cliente = new Socket(IP, PORTA);
            //final Socket cliente;// = new Socket("169.254.134.077", 12345);
            //if(cliente.isConnected())
              //  Toast.makeText(MainActivity.this, "Abriu porta com servidor [cliente]", Toast.LENGTH_LONG).show();

//            Log.i(CAT, "Abriu porta");
  //                  Toast.makeText(MainActivity.this, "Abriu porta com servidor [cliente]", Toast.LENGTH_LONG).show();
                    //msg.setText("Abriu porta com servidor [cliente]");
                //    final PrintStream saida = new PrintStream(cliente.getOutputStream());

                //} catch(Exception e) {
                //    Log.e(CAT, "Erro IOE: " + e.getMessage());
                 //   Toast.makeText(MainActivity.this, "Erro ao abrir porta com servidor", Toast.LENGTH_LONG).show();
                //}
            //});

    }
}
/*

                    buttonEnviar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                                final String mensagem = msg.getText().toString();
                                final Scanner s = new Scanner(mensagem);

                                Log.i(CAT, "Mensagem: " + mensagem);
                                while (s.hasNextLine()) {
                                    saida.println(s.nextLine());
                                }
                                s.close();
                                saida.close();


                        }
                    });

                    cliente.close();

                } catch (UnknownHostException u) {
                    Log.e(CAT, "Erro UHE" + u.getMessage());
                    Toast.makeText(MainActivity.this, "Erro ao abrir porta com servidor", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Log.e(CAT, "Erro IOE: " + e.getMessage());
                    Toast.makeText(MainActivity.this, "Erro ao abrir porta com servidor", Toast.LENGTH_LONG).show();
                }

            }
        });
 */