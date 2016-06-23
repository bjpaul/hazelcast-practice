package instance;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by bijoy on 16/6/16.
 */
public class ClientInstance{
    public static HazelcastInstance instance(){
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getGroupConfig()
                .setName("intellimeet")
                .setPassword("june");
        clientConfig.getNetworkConfig()
                .addAddress("192.168.1.101")
                .addAddress("192.168.1.102")
                .setConnectionAttemptLimit(5) // by default 2
                .setConnectionAttemptPeriod(1000) // by default 3000 ms
                .setConnectionTimeout(3000); // by default 5000 ms
        return HazelcastClient.newHazelcastClient(clientConfig);
    }

}
