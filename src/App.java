import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import dominio.Album;
import dominio.Artista;
import dominio.Musica;

public class App {
     public static void main(String[] args) throws Exception {
          Musica musica1 = new Musica();
          musica1.setnomeMusica("Count-Em");
          musica1.setGenero("Rock");
          musica1.setDuracao(180);
          musica1.setArquivoAudio("./assets/Brandon-Lake_Count-Em.wav");

          Musica musica2 = new Musica();
          musica2.setnomeMusica("Tear-Off-The-Roof");
          musica2.setGenero("Rock");
          musica2.setDuracao(180);
          musica2.setArquivoAudio("./assets/Brandon-Lake_Tear-Off-The-Roof.wav");

          Album album1 = new Album();
          album1.setNomeAlbum("Primeiro artista");
          album1.setAno(2023);
          album1.addMusica(musica1);
          
          Artista BrandonLake = new Artista();
          BrandonLake.setNome("Brandon Lake");
          BrandonLake.addAlbum(album1);

          System.out.println("Abrindo PlayMusic...");
          BrandonLake.getAlbuns().get(0).getMusicas().get(0).getArquivoAudio();

          AudioPlayer player = new AudioPlayer();
          player.loadAudio(BrandonLake.getAlbuns().get(0).getMusicas().get(0).getArquivoAudio());

           // Cria o botão Play/Stop e configura sua ação
          JButton playStopButton = new JButton("Play");
          playStopButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    if (!player.isPlaying) {
                         player.playAudio();
                         playStopButton.setText("Stop");
                    } else {
                         player.stopAudio();
                         playStopButton.setText("Play");
                    }
               }
          });

          ImageIcon icon = new ImageIcon("./assets/imgMusic.png");
          //Exibe um JOptionPane com o botão Play/Stop
          JOptionPane.showOptionDialog(
                null,
                BrandonLake.getAlbuns().get(0).getMusicas().get(0).getnomeMusica(),
                "PlayMusic",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                icon,
                new Object[] { playStopButton }, playStopButton);
          
          // Fecha o clip de áudio ao encerrar o programa
          if (player.audioClip != null) {
               player.audioClip.close();
          }
     }
}
