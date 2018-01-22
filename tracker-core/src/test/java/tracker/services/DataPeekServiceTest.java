package tracker.services;

import jdev.dto.PointDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DataPeekServiceTest {

    @InjectMocks
    private DataPeekService dataPeekService;

    @Before
    public void setUp() throws Exception{
        PointDTO point = new PointDTO();
        point.setLat(56);
        point.setLon(74);
        point.setAzim(123);
        point.setSpeed(40);
        point.setAutoId("k310ms70");
        point.setTime(System.currentTimeMillis());

        dataPeekService.put(point);
    }

    @Test
    public void getQueue() throws Exception {
        assertNotNull(dataPeekService.getQueue());
    }

    @Test
    public void take() throws Exception {
        PointDTO result = dataPeekService.take();
        assertNotNull(result);
        assertEquals(56, result.getLat(), 0);
        assertEquals(74, result.getLon(), 0);
        assertEquals(123, result.getAzim(), 0);
        assertEquals(40, result.getSpeed(), 0);
        assertEquals("k310ms70", result.getAutoId());
        assertTrue(result.toJson().contains("\"time\":"));
    }

}