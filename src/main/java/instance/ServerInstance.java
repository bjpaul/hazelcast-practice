package instance;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.*;

/**
 * Created by bijoy on 17/6/16.
 */
public class ServerInstance implements LifecycleListener{
    private static HazelcastInstance hazelcastInstance;
    private static String instanceName;
    private static IAtomicLong memberId;
    private static IQueue<String> queue;

    static {
        hazelcastInstance = serverInstance();
        hazelcastInstance.getLifecycleService().addLifecycleListener(new ServerInstance());
        memberId = hazelcastInstance.getAtomicLong("memberId");
        instanceName = "Instance " + memberId.incrementAndGet();
        queue = hazelcastInstance.getQueue("testQueue");
    }

    public static IQueue<String> getQueue() {
        return queue;
    }

    public static String getInstanceName() {
        return instanceName;
    }

    private static HazelcastInstance serverInstance() {
        return Hazelcast.newHazelcastInstance(serverConfig());
    }

    public static HazelcastInstance instance(){ return hazelcastInstance;}
    private static Config serverConfig() {
        Config config = new Config();
        config.getGroupConfig()
                .setName(System.getProperty("user.name"))
                .setPassword("june");
//        config.setLiteMember(true);
        return config;
    }



    @Override
    public void stateChanged(LifecycleEvent event) {
        System.out.println("Server -> " + event);
    }


}
