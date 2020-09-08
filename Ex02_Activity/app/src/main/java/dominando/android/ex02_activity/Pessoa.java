package dominando.android.ex02_activity;

import java.io.Serializable;

/**
 * Created by Luciano on 10/08/2015.
 *
 * Para enviar via intent esse objeto so precisa implementar de Serializable
 * e quando obter o objeto da intent usar metodo intent.getSerializableExtra()
 *
 * Mais lento e não aconselhado pelo Google
 */
public class Pessoa implements Serializable {

    private String nome;
    private int idade;

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

}
