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
          musica1.setArquivoAudio("aula-de-poo-playmusic/assets/Brandon-Lake_Count-Em.wav");


          Musica musica2 = new Musica();
          musica2.setnomeMusica("That's who i praise");
          musica2.setGenero("Rock");
          musica2.setDuracao(180);
          musica2.setArquivoAudio("aula-de-poo-playmusic/assets/Brandon-Lake_Thats-Who-I-Praise.wav");

          Musica musica3 = new Musica();
          musica3.setnomeMusica("Tear-Off-The-Roof");
          musica3.setGenero("Rock");
          musica3.setDuracao(180);
          musica3.setArquivoAudio("aula-de-poo-playmusic/assets/Brandon-Lake_Tear-Off-The-Roof.wav");

          Album album1 = new Album();
          album1.setNomeAlbum("Primeiro artista");
          album1.setAno(2023);
          album1.addMusica(musica1);
          album1.addMusica(musica2);
          
          Album album2 = new Album();
          album2.addMusica(musica3);

          Artista BrandonLake = new Artista();
          BrandonLake.setNome("Brandon Lake");
          BrandonLake.addAlbum(album1);
          BrandonLake.addAlbum(album2);


          System.out.println("Abrindo PlayMusic...");

          AudioPlayer player = new AudioPlayer();

           // Cria o botão Play/Stop e configura sua ação
          JButton playStopButton = new JButton("Play");

          player.listAlbuns(BrandonLake.getAlbuns());
          
          //player.listMusic(BrandonLake.getAlbuns().get(0).getMusicas());

          playStopButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    if (!player.isPlaying) {
                         player.playAudio();
                         playStopButton.setText("Stop");
                    }else{
                         player.stopAudio();
                         playStopButton.setText("Play");
                    }
               }
          });

          JButton nextAudioButton = new JButton("Next Audio");
          nextAudioButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    if(player.isPlaying){
                         player.nextAudio();
                    }else{
                         player.nextAudio();
                         playStopButton.setText("Stop");
                    }
               }
          });

          JButton previousAudioButton = new JButton("Previous Audio");
          previousAudioButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    if(player.isPlaying){
                         player.previousAudio();
                    }else{
                         player.previousAudio();
                         playStopButton.setText("Stop");
                    }
               }
          });

          JButton changeAlbumButton = new JButton("Change ALbum");
          changeAlbumButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                         player.nextAlbum();
                         if (!player.isPlaying) {
                              player.playAudio();
                              playStopButton.setText("Stop");
                         }else{
                              player.stopAudio();
                              playStopButton.setText("Play");
                         }

               }
          });

          ImageIcon icon = new ImageIcon("aula-de-poo-playmusic/assets/imgMusic.png");
          //Exibe um JOptionPane com o botão Play/Stop
          JOptionPane.showOptionDialog(
                null,
                player,
                "PlayMusic",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                icon,
                new Object[] { previousAudioButton, playStopButton, nextAudioButton, changeAlbumButton }, playStopButton);

          // Fecha o clip de áudio ao encerrar o programa
          if (player.audioClip != null) {
               player.audioClip.close();
          }
     }
}
