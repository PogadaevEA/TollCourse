package server.controllers;

import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Egor on 24.11.2017.
 */
public class ServerControllerTest {

    @Test
    public void getCoordinates() throws Exception {

        ServerController serverController = new ServerController();
        serverController.init();
        assertTrue(serverController.getCoordinates("{\"lat\":47.345,\"lon\":54.3345}"));
        assertFalse(serverController.getCoordinates("{\"lat\":text,\"lon\":text}"));
        assertFalse(serverController.getCoordinates(null));
        serverController.destroy();
    }

    @After
    public void tearDown() throws Exception{

        File file = new File("coordinates.txt");
        if (file.exists()) {
            file.delete();
        }
    }

}