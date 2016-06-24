package instance;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import listners.CustomMembershipListner;
/**
 * Created by bijoy on 23/6/16.
 */
public class Server {

    public static HazelcastInstance instance(){
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        hazelcastInstance.getCluster().addMembershipListener(new CustomMembershipListner());
        return hazelcastInstance;
    }

}
