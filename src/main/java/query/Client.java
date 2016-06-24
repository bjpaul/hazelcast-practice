package query;


import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import instance.ClientInstance;
import query.data.Address;

import java.util.Map;

/**
 * Created by bijoy on 15/6/16.
 */
public class Client {
    private static HazelcastInstance hazelcastInstance;
    public static void main(String[] args){
        hazelcastInstance = ClientInstance.instance();
        addZipCodes("Delhi");
        addZipCodes("Kolkata");
        addZipCodes("Mumbai");
        addZipCodes("Bengaluru");
        addZipCodes("Chennai");

        IMap<String, Address> addressIMap = hazelcastInstance.getMap("cityAddress");

        for(Map.Entry<String, Address> entry : addressIMap.entrySet()){
            System.out.println(entry);
        }
    }

    public static void addZipCodes(String city){
        IMap<String, Address> addressIMap = hazelcastInstance.getMap("cityAddress");
        for(int i = 110001; i < 111001; i++){
            addressIMap.put(city+" -> "+i, new Address(i, city));
        }
    }

}
