package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );

//        Music music = context.getBean("rockMusic", Music.class);
//
//
//        MusicPlayer musicPlayer = new MusicPlayer(music);
//
//        musicPlayer.play();
//
//        Computer computer = context.getBean("computer", Computer.class);
//        System.out.println(computer);

        MusicPlayer musicPlayer = (MusicPlayer) context.getBean("musicPlayer");
        System.out.println(musicPlayer.getName());



        context.close();
    }
}
