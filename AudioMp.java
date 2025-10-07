import java.io.File;
import java.io.IOException;
 import javax.sound.sampled.*;
import java.util.Scanner;

public class AudioMp {
    public static void main(String[] args) {
        // Use try-with-resources for the Scanner to ensure it gets closed
        try (Scanner sc = new Scanner(System.in)) {
            // Specify the audio file to be played
            File file = new File("C:\\Users\\User\\Desktop\\Audio Player\\No. 1 Party Anthem.mp3"); 

            // Use try-with-resources for the AudioInputStream and Clip to ensure they get closed
            try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                 Clip clip = AudioSystem.getClip()) {

                // Open the audio stream in the clip
                clip.open(audioStream);

                // Display options to the user
                System.out.println("P = play, S = stop, R = reset, Q = quit");
                String response = "";

                // Loop until the user chooses to quit
                while (!response.equals("Q")) {
                    System.out.print("Enter your choice: ");
                    response = sc.next().toUpperCase();

                    // Handle user input
                    switch (response) {
                        case "P":
                            // Play the audio
                            clip.start();
                            break;
                        case "S":
                            // Stop the audio
                            clip.stop();
                            break;
                        case "R":
                            // Reset the audio to the beginning
                            clip.setMicrosecondPosition(0);
                            break;
                        case "Q":
                            // Close the audio clip and exit the loop
                            clip.close();
                            break;
                        default:
                            // Handle invalid input
                            System.out.println("Invalid input");
                    }
                }

                // Indicate that playback has ended
                System.out.println("Playback ended.");
                
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                // Print stack trace in case of an exception
                e.printStackTrace();
            }
        }
    }
}