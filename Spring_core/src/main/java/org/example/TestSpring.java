package org.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
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
