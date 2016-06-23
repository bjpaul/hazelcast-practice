package instance;


import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by bijoy on 15/6/16.
 */
public class Client {
    public static void main(String[] args){
        ClientConfig clientConfig = new ClientConfig();
        HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
        while (true){
            try {
                 System.out.println("Receive ==>> " + hazelcastInstance.getQueue("testQueue").take());
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

}
