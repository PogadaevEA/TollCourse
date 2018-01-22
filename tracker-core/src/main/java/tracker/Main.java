package tracker;



import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Egor on 05.10.2017.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException{
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(QueueContext.class);

    }
}

