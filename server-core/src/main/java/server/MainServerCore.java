package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Egor on 08.10.2017.
 */

@SpringBootApplication
@ComponentScan("server.controllers")
public class MainServerCore {
    public static void main (String... args){
        SpringApplication.run(MainServerCore.class);
    }
}
