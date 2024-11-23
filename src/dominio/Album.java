package dominio;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private String nomeAlbum;
    private int ano;
    private List<Musica> musicas = new ArrayList<>();

    public String getNomeAlbum() {
        return nomeAlbum;
    }
    public void setNomeAlbum(String nomeAlbum) {
        this.nomeAlbum = nomeAlbum;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public List<Musica> getMusicas() {
        return musicas;
    }

    public void addMusica(Musica musica) {
        musicas.add(musica);
    }

    public void addMusicas(List<Musica> musicas) {
        this.musicas.addAll(musicas);
    }
    

}
 