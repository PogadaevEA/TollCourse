package tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Created by Егор on 18.11.2017.
 */
@Service
public class DataSendService {
    private static Logger log = LoggerFactory.getLogger(DataSendService.class);

    @Autowired
    private DataPeekService dataPeekService;

    @Autowired
    RestTemplate restTemplate;


    @Scheduled(cron = "${cron.prop.get}")
    private void send () throws InterruptedException{

        int messageQuantity = dataPeekService.getQueueSize();

        for(int i = 0; i < messageQuantity; i++){
            PointDTO record = dataPeekService.take();
            try {
                PointDTO pointDTO = new PointDTO();
                pointDTO.setAutoId(record.getAutoId());
                pointDTO.setLon(record.getLon());
                pointDTO.setLat(record.getLat());
                pointDTO.setAzim(record.getAzim());
                pointDTO.setSpeed(record.getSpeed());
                pointDTO.setTime(record.getTime());
                String request = pointDTO.toJson();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> coordinates = new HttpEntity<String>(request, headers);
                ResponseEntity<Boolean> answer = restTemplate.exchange("http://localhost:8080/coords", HttpMethod.POST, coordinates, Boolean.class);

                if(!answer.getBody()){
                    log.info("Bad answer. Returning of coordinates to queue");
                    dataPeekService.putFirst(record);
                }

                log.info(record.toJson());
            } catch (JsonProcessingException jpe) {
                jpe.printStackTrace();
            }
        }

    }
}
