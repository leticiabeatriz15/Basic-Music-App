package dominio;

public class Musica {
    private String nomeMusica;
    private String genero;
    private String arquivoAudio;
    private int duracao;

    public Musica(String nomeMusica, String genero, int duracao, String arquivoAudio){
        this.nomeMusica = nomeMusica;
        this.genero = genero;
        this.duracao = duracao;
        this.arquivoAudio = arquivoAudio;
    }

    public String getnomeMusica() {
        return nomeMusica;
    }
    public void setnomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getArquivoAudio() {
        return arquivoAudio;
      }
    
    public void setArquivoAudio(String arquivoAudio) {
        this.arquivoAudio = arquivoAudio;
    }
    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    
}
