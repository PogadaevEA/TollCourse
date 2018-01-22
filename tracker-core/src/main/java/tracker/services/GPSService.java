package tracker.services;

import jdev.dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import de.micromata.opengis.kml.v_2_2_0.*;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Егор on 18.11.2017.
 */
@Service
public class GPSService {
    private static Logger log = LoggerFactory.getLogger(GPSService.class);
    private int size;
    private int count;
    private List<Coordinate> coordinateList;
    private static final String TRACKER_ID = "К310МС70";
    private static final double SPEED = 40d;
    private  long time;
    double lat =0, lon=0;


    @Autowired
    private DataPeekService dataPeekService;

    @PostConstruct
    private void init() throws Exception{
        coordinateList = getGps();
        size = coordinateList.size();
    }

    @Scheduled(cron = "${cron.prop.put}")
    private void trackDTO(){
        double currentLat = 0;
        double currentLong = 0;
        double currentAltitude = 0;
        double azimuth = 0;
        double speed = 0;
        if (coordinateList.iterator().hasNext()) {
            Coordinate coordinate = coordinateList.iterator().next();
            PointDTO dto = new PointDTO();
            dto.setAutoId(TRACKER_ID);
            time= System.currentTimeMillis();
            dto.setTime(time);
            lat = coordinate.getLatitude();
            lon = coordinate.getLongitude();
            dto.setLat(lat);
            dto.setLon(lon);
            dto.setSpeed(SPEED);

            try {
                dataPeekService.put(dto);
            }
            catch (InterruptedException ie){
                ie.printStackTrace();
            }

            log.info("Gps point number: " + count + " Time " + time);
            coordinateList.remove(coordinate);

            count++;
        } else {
            log.info("The coordinates is over.");

        }

        }




    //Получение списка коодинат из kml-файла
    public ArrayList<Coordinate> getGps(){

        ArrayList<Coordinate> coordinateList = new ArrayList<Coordinate>();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("10164.kml").getFile());

        final Kml kml = Kml.unmarshal(file);
        final Folder folder = (Folder) kml.getFeature();
        List<Feature> featureList = folder.getFeature();
        Placemark placemark = null;

        for (Feature f : featureList){
            if (f instanceof Placemark){
                placemark = (Placemark) f;
                LineString lineString = (LineString) placemark.getGeometry();
                List<Coordinate> coordinates = lineString.getCoordinates();
                coordinateList.addAll(coordinates);
            }
        }

        return coordinateList;
    }


}
