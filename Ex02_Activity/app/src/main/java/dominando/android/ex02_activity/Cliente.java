package dominando.android.ex02_activity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Luciano on 10/08/2015.
 *
 * Utilizar a interface Parcelable é a ação mais completa, performatica e recomendada pelo Google.
 * Parceable é bem mais rapida que apenas utilizar interface Serializable
 *
 */
public class Cliente implements Parcelable {

    private String nome;
    private int codigo;

    public Cliente(String nome, int codgio) {
        this.nome = nome;
        this.codigo = codgio;
    }

    // construtor privado que le de um Parcel os dados
    private Cliente(Parcel from) {
        this.nome = from.readString();
        this.codigo = from.readInt();
    }

    // valor inteiro identificador unico dessa classe no projeto.
    // cada classe do projeto deve ter um identificador com valor diferente
    @Override
    public int describeContents() {
        return 0;
    }

    // serializa (transforma em bytes) os atributos da classe
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeInt(codigo);
    }

    // atributo estatico OBRIGATORIO para todas as Parcelable
    // responsavel por criar o objeto a partir de um Parcel para
    // desserializar este. Chama o construtor privado para ler os bytes do Parcel
    public static  final Parcelable.Creator<Cliente> CREATOR = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel source) {
            return new Cliente(source); // chama construtor privado
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }
}
