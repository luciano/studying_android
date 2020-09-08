package dominando.android.ex21_http;

import java.io.Serializable;

/**
 * Created by Luciano on 22/10/2015.
 */
public class Livro implements Serializable {
    public String titulo;
    public String categoria;
    public String autor;
    public int ano;
    public int paginas;
    public String capa;

    public Livro(String titulo, String categoria, String autor,
                 int ano, int paginas, String capa) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.autor = autor;
        this.ano = ano;
        this.paginas = paginas;
        this.capa = capa;
    }

    @Override
    public String toString() {
        return this.titulo;
    }
}
