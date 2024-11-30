import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import dominio.Album;
import dominio.Musica;

public class AudioPlayer {
    private List<Album> listaDeAlbuns;
    private int indiceAlbum;
    private List<Musica> listaDeReproducao;
    private int indiceMusica;
    public Clip audioClip;
    public boolean isPlaying = false;

  
    public void loadAudio(String filePath) {
      System.out.println("loadAudio: " + filePath);
      try {

        // Se um áudio já estiver carregado, pare e libere o recurso
        if (audioClip != null && audioClip.isOpen()) {
          audioClip.stop();
          audioClip.close();
        }

        // Carrega o arquivo de áudio
        File audioFile = new File(filePath);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
  
        // Configura o Clip
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        audioClip = (Clip) AudioSystem.getLine(info);
  
        audioClip.open(audioStream);
      } catch (UnsupportedAudioFileException e) {
        System.out.println("O formato do arquivo de áudio não é suportado.");
        e.printStackTrace();
      } catch (LineUnavailableException e) {
        System.out.println("A linha de áudio não está disponível.");
        e.printStackTrace();
      } catch (IOException e) {
        System.out.println("Erro ao ler o arquivo de áudio.");
        e.printStackTrace();
      }
    }

    public void listAlbuns(List<Album> albuns){
      this.listaDeAlbuns = albuns;
      this.indiceAlbum = 0;
      listMusic(albuns.get(indiceAlbum).getMusicas());
    }

    public void listMusic(List<Musica> musicas){
      this.listaDeReproducao = musicas;
      this.indiceMusica = 0;
      loadAudio(musicas.get(indiceMusica).getArquivoAudio());
    }    
  
    public void playAudio() {
      System.out.println("playAudio");
      
      if (audioClip != null && !isPlaying) {
        audioClip.setFramePosition(0); // Reinicia o áudio do começo
        System.out.println("playAudio start");
        audioClip.start();
        isPlaying = true;
      }
    }
  
    public void stopAudio() {
      System.out.println("stopAudio");

      if (audioClip != null && isPlaying) {
        System.out.println("stopAudio stop");
        audioClip.stop();
        isPlaying = false;
      }
    }

    public void previousAudio (){

      System.out.println("previousAudio");
      stopAudio();

      if(this.indiceMusica == 0){
        indiceMusica = listaDeReproducao.size() - 1;
        loadAudio(listaDeReproducao.get(indiceMusica).getArquivoAudio());
        playAudio();

      }else if(this.indiceMusica <= listaDeReproducao.size() - 1){
        indiceMusica --;
        loadAudio(listaDeReproducao.get(indiceMusica).getArquivoAudio());
        playAudio();

      }
    }

    public void nextAudio (){

      System.out.println("nextAudio");
      stopAudio();

      if(this.indiceMusica < listaDeReproducao.size() - 1){
        indiceMusica ++;
        loadAudio(listaDeReproducao.get(indiceMusica).getArquivoAudio());
        playAudio();

      }else if(this.indiceMusica == listaDeReproducao.size() - 1){
        indiceMusica = 0;
        loadAudio(listaDeReproducao.get(indiceMusica).getArquivoAudio());
        playAudio();

      }else{
        System.out.println("Sua lista de reprodução está vazia!\nAdicione músicas para tocar...");

      }
    }

    public void nextAlbum(){
      System.out.println("nextAlbum");

      if(this.indiceAlbum < listaDeAlbuns.size() - 1){
        indiceAlbum ++;
        listMusic(listaDeAlbuns.get(indiceAlbum).getMusicas());

      }else{
        indiceAlbum = 0;
        listMusic(listaDeAlbuns.get(indiceAlbum).getMusicas());
      }
    }

  }  