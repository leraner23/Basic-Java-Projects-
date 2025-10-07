
// hello coders 


import javazoom.jl.player.Player; //  using player class from javazoom which helps in playing the MP# extension file

import javax.swing.*; // for all swing components 

import java.io.*; // for all input and output related class (eg: FileInputSystem)

import java.awt.Graphics; // for graphics displaying like shapes
import java.awt.Graphics2D; // in 2D version (advanced version)

import java.awt.Image; // for importing images related class 

// for action performed when clicking the buttons
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

public class AudioJ extends JPanel { // Jpanel is extended as it supports class to behave like panel and draw custom graphics
    
    private Image image;

    private static Player player;
    
    private static Thread playerThread; // for using thread 
    // why thread is used here ? ==> for running simulatneously , in this case with the help of Thread 
    // the music is running in the background while GUI stays interactive 

    private static File SelectMusic; // for loading music by JFileChooser before music playback
    private static File SelectImage; // for loading image by JFileChooser before music playback

    // Approximate length of the track in seconds 
    private static final int TRACK_SECONDS = 240; // 4 minutes

    private static JProgressBar progressBar; // for showing  horizontal progress

    private static Timer progressTimer; // for updating the progress while playing the music

    private static long startTime; // for storing  system time whhen playback starts




    public void setImage(File SelectImage){
        image = new ImageIcon(SelectImage.getAbsolutePath()).getImage(); // getAbsolutePath provides full path of the selected image
        repaint(); // tells the paint to redraw itself
    }
  
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
       if(image != null){
         Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 20, 10, 400, 300, this);
       }
    }
    //Main UI and Audio Controller
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AudioJ::createAndShowGUI);  // for  invoking and handling the different threads 
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Audio Player");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(new AudioJ());

        // single background panel
        AudioJ backgroundPanel = new AudioJ();
        backgroundPanel.setBounds(20, 70, 440, 300);
        frame.add(backgroundPanel);


        JLabel trackLabel = new JLabel();
        trackLabel.setBounds(20, 330, 400, 20);
        frame.add(trackLabel);

        JButton playButton = new JButton("Play");
        playButton.setBounds(60, 420, 80, 30);
        frame.add(playButton);

        JButton stopButton = new JButton("Stop");
        stopButton.setBounds(150, 420, 80, 30);
        frame.add(stopButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(240, 420, 80, 30);
        frame.add(resetButton);

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(330, 420, 80, 30);
        frame.add(quitButton);

// for selecting music
        JButton ChooseMusic  = new JButton("Select Music");  
        ChooseMusic.setBounds(30, 30, 180, 30);
        frame.add(ChooseMusic);
        ChooseMusic.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e){ 
                JFileChooser fc = new JFileChooser(); // JFileChooser, a swing component that opens file dialog window
                if(fc.showOpenDialog(ChooseMusic) == JFileChooser.APPROVE_OPTION){
                    SelectMusic = fc.getSelectedFile();
                }
            }
        });



// for selecting the image
        JButton ChooseImage = new JButton("Select Image");
        ChooseImage.setBounds(250, 30, 180, 30);
        frame.add(ChooseImage);

        ChooseImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFileChooser fc = new JFileChooser();
               if( fc.showOpenDialog(ChooseImage) == JFileChooser.APPROVE_OPTION){
                    SelectImage = fc.getSelectedFile();
                      backgroundPanel.setImage(SelectImage);
               }
            }
        });



        // ---- Horizontal Progress Bar ----
        progressBar = new JProgressBar(0, TRACK_SECONDS);
        progressBar.setBounds(30, 380, 400, 20);
        progressBar.setStringPainted(false); // show percentage/time
        frame.add(progressBar);

        // Timer to update progress every 500 ms
        progressTimer = new Timer(500, e -> updateProgress());

        // Button actions
        playButton.addActionListener(e -> playSong());
        stopButton.addActionListener(e -> stopSong());
        resetButton.addActionListener(e -> resetProgress());
        quitButton.addActionListener(e -> {
            stopSong();
            System.exit(0);
        });

        frame.setVisible(true);
    }

    // Threads handling 
    private static void playSong() {
        if (playerThread != null && playerThread.isAlive())
            return;

        try {
            if(SelectMusic == null){
                JOptionPane.showMessageDialog(null, "empty Music file");
                return;
            }
            FileInputStream fis = new FileInputStream(SelectMusic);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);

            // reset and start timer
            progressBar.setValue(0);
            startTime = System.currentTimeMillis();
            progressTimer.start();

            playerThread = new Thread(() -> {
                try {
                    player.play();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    // stop timer when playback finishes
                    SwingUtilities.invokeLater(() -> progressTimer.stop());
                }
            });
            playerThread.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // for stop button
    private static void stopSong() {
        if (player != null) {
            player.close();
        }
        progressTimer.stop();
    }

    // for reset button
    private static void resetProgress() {
        stopSong();
        progressBar.setValue(0);
    }


    // for update button
    private static void updateProgress() {
        long elapsedSec = (System.currentTimeMillis() - startTime) / 1000;
        if (elapsedSec > TRACK_SECONDS) {
            elapsedSec = TRACK_SECONDS;
        }
        progressBar.setValue((int) elapsedSec);
    }
}


