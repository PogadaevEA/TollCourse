package tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


/**
 * Created by Егор on 18.11.2017.
 */
@Service
public class DataSendService {
    private static Logger log = LoggerFactory.getLogger(DataSendService.class);

    @Autowired
    private DataPeekService dataPeekService;


    @Scheduled(cron = "${cron.prop.get}")
    private void send () throws InterruptedException{

        int messageQuantity = dataPeekService.getQueueSize();

        for(int i = 0; i < messageQuantity; i++){
            PointDTO record = dataPeekService.take();
            try {
                log.info(record.toJson());
            } catch (JsonProcessingException jpe) {
                jpe.printStackTrace();
            }
        }

    }
}
