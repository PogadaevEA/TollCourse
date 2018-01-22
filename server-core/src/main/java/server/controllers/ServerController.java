package server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;
import java.io.PrintWriter;



/**
 * Created by Egor on 11.10.2017.
 */
@RestController
public class ServerController {

    private static Logger log = LoggerFactory.getLogger(ServerController.class);

    private PrintWriter printWriter;

    @PostConstruct
    private void init(){

        try {
            printWriter = new PrintWriter("coordinates.txt");
        }
        catch(FileNotFoundException e) {
            e.getMessage();
        }

    }

    @RequestMapping(value = "/coords", method = RequestMethod.POST, consumes = "application/json")
    public Boolean getCoordinates (@RequestBody String jsonCoords){
        try {
            ObjectMapper mapper = new ObjectMapper();
            PointDTO coords = mapper.readValue(jsonCoords, PointDTO.class);
            log.info("getCoordinates: lat = " + coords.getLat() + "; lon = " + coords.getLon());
            printWriter.println(coords.getLon() + "," + coords.getLat());
            printWriter.flush();
            return true;
        }
        catch (Exception e){
            e.getStackTrace();
            return false;
        }
    }

    @PreDestroy
    private void destroy(){
        printWriter.close();
    }

}
