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
          Musica musica1 = new Musica("Good God Almighty", "Rock", 180, "./assets/Crowder/Crowder-Good_God_Almighty.wav");

          Musica musica2 = new Musica("He Is", "Rock", 180, "./assets/Crowder/Crowder-He_Is.wav");

          Musica musica3 = new Musica("Milk and Honey", "Rock", 180, "./assets/Crowder/Crowder-MilkAndHoney.wav");

          Album MilkAndHoney = new Album("Milk and Honey", 2023);
          MilkAndHoney.setNomeAlbum("Milk and Honey");
          MilkAndHoney.setAno(2023);
          MilkAndHoney.addMusica(musica1);
          MilkAndHoney.addMusica(musica2);
          MilkAndHoney.addMusica(musica3);
          
          Musica musica4 = new Musica("Even in Exile", "Rock", 180, "./assets/Crowder/Crowder-Even_In_Exile.wav");

          Musica musica5 = new Musica("Grave Robber", "Rock", 180, "./assets/Crowder/Crowder-Grave_Robber.wav");

          Musica musica6 = new Musica("Still", "Rock", 180, "./assets/Crowder/Crowder-Still.wav");

          Album TheExile = new Album("The Exile", 2023);
          TheExile.addMusica(musica4);
          TheExile.addMusica(musica5);
          TheExile.addMusica(musica6);

          Artista Crowder = new Artista("Crowder");
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

          ImageIcon icon = new ImageIcon("./assets/imgMusic.png");
          JLabel iconLabel = new JLabel(icon);

          JButton startButton = new JButton("Start");
          startButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    panel.remove(startButton);
                    player.listAlbuns(Crowder.getAlbuns());
          
                    panel.add(iconLabel);
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