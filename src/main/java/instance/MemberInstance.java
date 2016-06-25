package instance;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by bijoy on 17/6/16.
 */
public class MemberInstance {

    public static HazelcastInstance memberInstance() {
        return ServerInstance.serverInstance();
    }


    private static class ServerInstance {
        private static HazelcastInstance hazelcastInstance;
        private static Config config;

        static {
            config = config();
        }


        private static HazelcastInstance serverInstance() {
            if (hazelcastInstance == null) {
                hazelcastInstance = createInstance();
            }
            return hazelcastInstance;
        }

        private static HazelcastInstance createInstance() {
            hazelcastInstance = Hazelcast.newHazelcastInstance(config);
            return hazelcastInstance;
        }

        private static Config serverConfig() {
            return config;
        }

        private static Config config() {
            Config config = new Config();
            config.getGroupConfig()
                    .setName("intellimeet")
                    .setPassword("june");
            config.getMemberAttributeConfig()
                    .setStringAttribute("user", System.getProperty("user.name"));

            config.getMapConfig("cityAddress")
                    .addMapIndexConfig(new MapIndexConfig("zipCode", true))
                    .addMapIndexConfig(new MapIndexConfig("city", true));
            return config;
        }


    }

}
