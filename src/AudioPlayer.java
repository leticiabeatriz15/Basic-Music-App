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

import dominio.Musica;

public class AudioPlayer {
    private List<Musica> listaDeReproducao;
    private int indice;
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

    public void listMusic(List<Musica> musicas){
      this.listaDeReproducao = musicas;
      this.indice = 0;
      loadAudio(musicas.get(indice).getArquivoAudio());
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

      if(this.indice == 0){
        indice = listaDeReproducao.size() - 1;
        loadAudio(listaDeReproducao.get(indice).getArquivoAudio());
        playAudio();

      }else if(this.indice <= listaDeReproducao.size() - 1){
        indice --;
        loadAudio(listaDeReproducao.get(indice).getArquivoAudio());
        playAudio();
        
      }
    }

    public void nextAudio (){
      System.out.println("nextAudio");
      stopAudio();
      if(this.indice < listaDeReproducao.size() - 1){
        indice ++;
        loadAudio(listaDeReproducao.get(indice).getArquivoAudio());
        playAudio();
      }else if(this.indice == listaDeReproducao.size() - 1){
        indice = 0;
        loadAudio(listaDeReproducao.get(indice).getArquivoAudio());
        playAudio();
      }else{
        System.out.println("Sua lista de reprodução está vazia!\nAdicione músicas para tocar...");
      }
    }

  }  