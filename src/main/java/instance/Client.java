package instance;


import com.hazelcast.core.HazelcastInstance;

/**
 * Created by bijoy on 15/6/16.
 */
public class Client {
    public static void main(String[] args){
        HazelcastInstance hazelcastInstance = ClientInstance.instance();
        while (true){
            try {
                 System.out.println("Receive ==>> " + hazelcastInstance.getQueue("testQueue").take());
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

}
