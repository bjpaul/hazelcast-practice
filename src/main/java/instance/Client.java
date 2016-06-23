package instance;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by bijoy on 23/6/16.
 */
public class Client {
    public static HazelcastInstance instance(){
        return HazelcastClient.newHazelcastClient();
    }
}
