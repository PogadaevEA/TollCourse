package jdev.dto;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jdev on 06.03.2017.
 */
public class PointDTOTest {

    private String expected = "{\"lat\":56.0,\"lon\":74.0,\"azimut\":20.0,\"speed\":40.0,\"autoId\":\"o567gfd\",\"time\":1509896189290}";
    private String autoId = "o567gfd";

    @Test
    public void toJson() throws Exception {
        PointDTO point = new PointDTO();
        point.setLat(56);
        point.setLon(74);
        point.setAutoId("o567gfd");
        point.setAzim(20.0);
        point.setSpeed(40.0);
        point.setTime(System.currentTimeMillis());

        assertTrue(point.toJson().contains("\"lat\":56"));
        assertTrue(point.toJson().contains("\"time\":"));
        System.out.println(point.toJson());
    }

    @Test
    public void decodeDto() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        PointDTO dto = mapper.readValue(expected, PointDTO.class);
        assertEquals(autoId, dto.getAutoId());
        assertEquals(1509896189290L, dto.getTime());

    }
}