package dominando.android.ex10_adapter;

/**
 *
 * Created by Luciano on 25/08/2015.
 *
 */
public class Carro {

    public String modelo;
    public int ano;
    public int fabricante; // 0=vw; 1=gm; 2=fiat; 3=ford
    public boolean gasolina;
    public boolean etanol;

    public Carro(String modelo, int ano, int fabricante, boolean gasolina, boolean etanol) {
        this.modelo = modelo;
        this.ano = ano;
        this.fabricante = fabricante;
        this.gasolina = gasolina;
        this.etanol = etanol;
    }

}
