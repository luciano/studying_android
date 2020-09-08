package dominando.android.hotel.ex23_webservice;

import java.io.Serializable;

public class Hotel implements Serializable {

    public long id;
    public String nome;
    public String endereco;
    public float estrelas;
    public Status status;
    public long idServidor;

    public Hotel(long id, String nome, String endereco,
                 float estrelas, long idServidor, Status status) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.estrelas = estrelas;
        this.status = status;
        this.idServidor = idServidor;
    }

    public Hotel(String nome, String endereco, float estrelas) {
        this(0, nome, endereco, estrelas, 0, Status.INSERIR);
    }

    @Override
    public String toString() {
        return nome;
    }

    public enum Status {
        OK, INSERIR, ATUALIZAR, EXCLUIR
    }
}
