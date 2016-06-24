package query;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import instance.ClientInstance;
import query.data.Address;

/**
 * Created by bijoy on 24/6/16.
 */
public class AddressFinder {
    public static void main(String[] args){
        HazelcastInstance hazelcastInstance = ClientInstance.instance();

        IMap<String, Address> addressIMap = hazelcastInstance.getMap("cityAddress");


    }
}
