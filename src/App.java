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
          musica1.setnomeMusica("Good God Almighty");
          musica1.setGenero("Rock");
          musica1.setDuracao(180);
          musica1.setArquivoAudio("aula-de-poo-playmusic/assets/Crowder/Crowder-Good_God_Almighty.wav");


          Musica musica2 = new Musica();
          musica2.setnomeMusica("He Is");
          musica2.setGenero("Rock");
          musica2.setDuracao(180);
          musica2.setArquivoAudio("aula-de-poo-playmusic/assets/Crowder/Crowder-He_Is.wav");

          Musica musica3 = new Musica();
          musica3.setnomeMusica("Milk and Honey");
          musica3.setGenero("Rock");
          musica3.setDuracao(180);
          musica3.setArquivoAudio("aula-de-poo-playmusic/assets/Crowder/Crowder-MilkAndHoney.wav");

          Album MilkAndHoney = new Album();
          MilkAndHoney.setNomeAlbum("Milk and Honey");
          MilkAndHoney.setAno(2023);
          MilkAndHoney.addMusica(musica1);
          MilkAndHoney.addMusica(musica2);
          MilkAndHoney.addMusica(musica3);
          
          Musica musica4 = new Musica();
          musica4.setnomeMusica("Even in Exile");
          musica4.setGenero("Rock");
          musica4.setDuracao(180);
          musica4.setArquivoAudio("aula-de-poo-playmusic/assets/Crowder/Crowder-Even_In_Exile.wav");

          Musica musica5 = new Musica();
          musica5.setnomeMusica("Grave Robber");
          musica5.setGenero("Rock");
          musica5.setDuracao(180);
          musica5.setArquivoAudio("aula-de-poo-playmusic/assets/Crowder/Crowder-Grave_Robber.wav");

          Musica musica6 = new Musica();
          musica6.setnomeMusica("Still");
          musica6.setGenero("Rock");
          musica6.setDuracao(180);
          musica6.setArquivoAudio("aula-de-poo-playmusic/assets/Crowder/Crowder-Still.wav");

          Album TheExile = new Album();
          TheExile.addMusica(musica4);
          TheExile.addMusica(musica5);
          TheExile.addMusica(musica6);

          Artista Crowder = new Artista();
          Crowder.setNome("Crowder");
          Crowder.addAlbum(MilkAndHoney);
          Crowder.addAlbum(TheExile);


          System.out.println("Abrindo PlayMusic...");

          AudioPlayer player = new AudioPlayer();

           // Cria o botão Play/Stop e configura sua ação
          JButton playStopButton = new JButton("Play");

          player.listAlbuns(Crowder.getAlbuns());
          
          //player.listMusic(Crowder.getAlbuns().get(0).getMusicas());

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
