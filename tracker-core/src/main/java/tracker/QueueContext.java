package tracker;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import tracker.services.DataSendService;
import tracker.services.DataPeekService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import tracker.services.GPSService;

/**
 * Created by Egor on 05.10.2017.
 */
@Configuration
@EnableScheduling
@ComponentScan("tracker.services")
@PropertySource("classpath:/app.properties")
class QueueContext {

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        scheduler.setThreadNamePrefix("poolSheduler");
        return scheduler;
    }
}