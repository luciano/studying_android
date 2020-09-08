package br.com.liugsilva.semfogo;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class Registro implements Serializable {
    public static final String REGISTRO = "registro";

    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String LOCALIZACAO = "localizacao";
    public static final String HORARIO = "horario";
    public static final String DATA = "data";
    public static final String INFORMACAO = "informacao";

    private String id_usuario; // so ter id_usuario ou usuario?
    private String latitude;
    private String longitude;
    private String localizacao;
    private String horario;
    private String data;
    private String informacao;
    private List<String> linksImagem;

    public Registro(String latitude, String longitude, String localizacao, String horario, String data, String informações, List<String> linkImagem) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.localizacao = localizacao;
        this.horario = horario;
        this.data = data;
        this.informacao = informações;
        this.linksImagem = linkImagem;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getHorario() {
        return horario;
    }

    public String getData() {
        return data;
    }

    public String getInformacao() {
        return informacao;
    }

    public List<String> getLinksImagem() {
        return linksImagem;
    }

    public void setLinksImagem(List<String> linksImagem) {
        this.linksImagem = linksImagem;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return latitude + ";" + longitude + ";" + localizacao + ";" + horario + ";" + data + ";" + informacao + ";" + linksImagem + ";";
    }
}
