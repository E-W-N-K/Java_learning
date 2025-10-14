package org.example;

public class MusicPlayer {
    private Music music;

    //IoC
    public MusicPlayer(Music music) {
        this.music = music;
    }

    public void play() {
        System.out.println("Playing: " + music.getSong());
    }
}
