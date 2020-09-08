package android.teste.leitor.sms;

import android.util.Log;

public class Dicionario {

    public String getResposta(String mensagem) {

        String resposta = "";

        mensagem = mensagem.toLowerCase();

        Log.i(ReceberSms.CATEGORIA, "Mensagem LowerCase: " + mensagem);

        mensagem = simplificandoMensagem(mensagem);

        Log.i(ReceberSms.CATEGORIA, "Mensagem simplificada: " + mensagem);

        switch(mensagem) {

            case "ei":
            case "ei!":
            case "ei.":
            case "ola":
            case "ola!":
            case "ola.":
            case "oi":
            case "oi.":
            case "oi!":
                Log.i(ReceberSms.CATEGORIA, "Entrou case oi ");
                resposta += "oii";
                break;

            case "boa noite":
            case "boa noite!":
            case "boa noite,":
            case "boa noite.":
                Log.i(ReceberSms.CATEGORIA, "Entrou case boa noite ");
                resposta += "boa noite";
                break;
            case "":
                Log.i(ReceberSms.CATEGORIA, "Entrou case Como vc esta ");
                resposta += "Eu to bem e vc?";
                break;
            case "to bem":
            case "eu to bem":
            case "bem":
            case "bm":
            case "otimo":
            case "otima":
            case "ben":
            case "bn":
                Log.i(ReceberSms.CATEGORIA, "Entrou case to bem");
                resposta += "que bom q ta bem";
                break;
        }

        if(mensagem.contains("me liga")) {
            resposta += "estou ocupado agora\nretorno assim que puder.";
        }

        Log.i(ReceberSms.CATEGORIA, "Mensagem resposta: " + resposta);
        return resposta;
    }

    private String simplificandoMensagem(String mensagem) {
        String simplificada = "";
        char c;
        for (int i = 0; i < mensagem.length(); ++i) {

            c = mensagem.charAt(i);
            if(i < mensagem.length() - 1) {
                if(c != mensagem.charAt(i + 1)) {
                    simplificada += String.valueOf(c);
                }
            } else {
                simplificada += String.valueOf(c);
            }

        }

        return simplificada;
    }
}
