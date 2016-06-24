package instance;


import com.hazelcast.core.IMap;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Created by bijoy on 15/6/16.
 */
public class Client {
    private static Random random = new Random();
    private static String mapName = System.getProperty("user.name")+"testLock";
    public static void main(String[] args){

        IMap<String, Long> map = ClientInstance.instance().getMap(mapName);
        map.put("key", 0l);
        ExecutorService executor = Executors.newFixedThreadPool(15);

        System.out.println("Starting ...");
        long start = System.currentTimeMillis();

        for (int i = 0; i < 15; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    update();
                }
            });
        }
        executor.shutdown();

        try {
            executor.awaitTermination(2, TimeUnit.MINUTES);
            System.out.println("---------Complete---------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));

        System.out.println(map.getOrDefault("key", 0l));
    }

    private static void update() {
        IMap<String, Long> map = ClientInstance.instance().getMap(mapName);
        Long l ;
        for (int i = 0; i < 100; i++) {
            l = map.get("key");
            try {
                Thread.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put("key", ++l);
        }
    }


}
