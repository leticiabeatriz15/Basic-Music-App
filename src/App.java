import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

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

          JButton changeAlbumButton = new JButton("Change Album");
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

          JPanel panel = new JPanel();
          JFrame frame = new JFrame();

          //JLabel iconLabel = new JLabel(icon);

          JButton startButton = new JButton("Start");
          startButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    panel.remove(startButton);
                    player.listAlbuns(Crowder.getAlbuns());
          
                    panel.add(previousAudioButton);
                    panel.add(playStopButton);
                    panel.add(nextAudioButton);
                    panel.add(changeAlbumButton);
                    panel.revalidate(); // Atualiza o layout painel
                    panel.repaint();
               }
          });

          panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
          panel.setLayout(new GridLayout(0, 1));

          //panel.add(iconLabel);
          panel.add(startButton);

          frame.add(panel, BorderLayout.CENTER);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setTitle("Play Music");
          frame.pack();
          frame.setVisible(true);

          JLabel textLabel = new JLabel("Bem-vindo ao Play Music!");
          textLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto
          panel.add(textLabel);

          // Fecha o clip de áudio ao encerrar o programa
          if (player.audioClip != null) {
               player.audioClip.close();
          }
     }
}