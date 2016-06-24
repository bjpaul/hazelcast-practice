package query;


import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import instance.ClientInstance;
import query.data.Address;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bijoy on 15/6/16.
 */
public class Client {
    private static HazelcastInstance hazelcastInstance;
    public static void main(String[] args){
        hazelcastInstance = ClientInstance.instance();
        ExecutorService executor = Executors.newFixedThreadPool(15);

        System.out.println("Starting ...");

        executor.submit(addZipCodes("Delhi"));
        executor.submit(addZipCodes("Kolkata"));
        executor.submit(addZipCodes("Mumbai"));
        executor.submit(addZipCodes("Bengaluru"));
        executor.submit(addZipCodes("Chennai"));

        executor.shutdown();

        try {
            executor.awaitTermination(5, TimeUnit.MINUTES);
            System.out.println("---------Complete---------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IMap<String, Address> addressIMap = hazelcastInstance.getMap("cityAddress");

        for(Map.Entry<String, Address> entry : addressIMap.entrySet()){
            System.out.println(entry);
        }
    }

    public static Runnable addZipCodes(final String city){
        return new Runnable() {
            IMap<String, Address> addressIMap = hazelcastInstance.getMap("cityAddress");
            @Override
            public void run() {
                for(int i = 110001; i < 210010; i++){
                    addressIMap.put(city+" -> "+i, new Address(i, city));
                }
            }
        };
    }

}
