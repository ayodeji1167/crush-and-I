package com.example.crushandi;

import com.example.crushandi.utils.Scheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrushAndIApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrushAndIApplication.class, args);
    }
@Bean
    public Scheduler scheduler(){
        return new Scheduler();
}

}
