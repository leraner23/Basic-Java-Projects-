# 🎵 Swing Audio Player

A simple Java Swing-based audio player that allows users to select an `.mp3` music file and a background image, then play the music with a visual interface.

---

## 📦 Requirements

To run this project, you'll need the following libraries:

- [JLayer](http://www.javazoom.net/javalayer/javalayer.html) – for decoding and playing MP3 files  
- `mp3spi` – for MP3 stream support  
- `tritonus` – for audio system extensions

---

## 📁 Project Structure

- `AudioJ.java` → Main GUI and playback logic  
- `AudioMP.java` → Minimal audio player (MP3-only)  
- `images/` and `music/` → Sample image and MP3 files for testing

---

## 🖼️ Output Preview

Here’s a screenshot of the `AudioJ` interface:

![App Screenshot](Screenshot%202025-10-07%20162719.png)

---

## 🔮 Future Updates

The following improvements are planned for upcoming versions of the Swing Audio Player:

1. **Make the progress bar more relative and flexible**  
   - Instead of a fixed duration, dynamically reflect the actual length of the selected MP3 file.

2. **Display the song name in the output**  
   - Show the currently playing track name in the UI for better user feedback.

3. **Improve the overall UI design**  
   - Replace `null` layout with responsive layout managers (e.g., `BorderLayout`, `GridBagLayout`)  
   - Add icons, spacing, and styling for a more polished look.

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/leraner23/Basic-Java-Projects-.git
