package dominio;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String nomePlaylist;
    private List<Musica> musicas= new ArrayList<>();
    
    public String getNomePlaylist() {
        return nomePlaylist;
    }
    public void setNomePlaylist(String nomePlaylist) {
        this.nomePlaylist = nomePlaylist;
    }
    public List<Musica> getMusicas() {
        return musicas;
    }
    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    

}
