package tracker.services;

import jdev.dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Егор on 19.11.2017.
 */
@Service
public class DataPeekService {

    private static Logger log = LoggerFactory.getLogger(DataPeekService.class);

    private BlockingDeque<PointDTO> queue =  new LinkedBlockingDeque<>(100);

    public BlockingDeque<PointDTO> getQueue() {
        return queue;
    }

    public int getQueueSize() {
        return queue.size();
    }

    public PointDTO take() throws InterruptedException{
        return queue.take();
    }

    public void put(PointDTO newpoint) throws InterruptedException {
        PointDTO point = new PointDTO();
        point.setAutoId(newpoint.getAutoId());
        point.setLat(newpoint.getLat());
        point.setLon(newpoint.getLon());
        point.setAzim(newpoint.getAzim());
        point.setSpeed(newpoint.getSpeed());
        point.setTime(newpoint.getTime());
        log.info("DataPeekService.put " + point.getAutoId() + " " + point.getLat() + " " + point.getLon());
        queue.put(point);
    }

    public void putFirst(PointDTO record) throws  InterruptedException{
        queue.putFirst(record);
    }
}
