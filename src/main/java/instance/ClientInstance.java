package instance;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by bijoy on 16/6/16.
 */
public class ClientInstance{
    private static ClientConfig clientConfig ;
    private static HazelcastInstance hazelcastInstance ;

    static {
        clientConfig = clientConfig();
    }

    public static HazelcastInstance instance(){
        while (hazelcastInstance == null || !hazelcastInstance.getLifecycleService().isRunning()){
            try{
                hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
            }catch (Exception ex){
                System.out.println("Exception "+ex.getMessage());
            }
        }
        return hazelcastInstance;
    }

    private static ClientConfig clientConfig(){
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getGroupConfig()
                .setName("intellimeet")
                .setPassword("june");
        clientConfig.getNetworkConfig()
                .addAddress("192.168.1.101")
                .addAddress("192.168.1.102")
                .setConnectionAttemptLimit(5) // by default 2
                .setConnectionAttemptPeriod(1000) // by default 3000 ms
                .setConnectionTimeout(1000); // by default 5000 ms
        return clientConfig;
    }

}
