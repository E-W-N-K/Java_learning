package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ClassicalMusic implements Music {

    @PostConstruct
    public void doMyInit(){
        System.out.println("ClassicalMusic doMyInit");
    }

    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }
}
