package instance;

import com.hazelcast.config.Config;
import com.hazelcast.core.Cluster;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import com.hazelcast.nio.Address;
import listners.CustomMembershipListner;

import java.util.Set;

/**
 * Created by bijoy on 23/6/16.
 */
public class Server {

    public static HazelcastInstance instance(){
        Config config = new Config();
        config.getMemberAttributeConfig().setStringAttribute("user", System.getProperty("user.name"));
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        hazelcastInstance.getCluster().addMembershipListener(new CustomMembershipListner());
        membersString(hazelcastInstance.getCluster());
        return hazelcastInstance;
    }

    private static void membersString(Cluster cluster){
        Set<Member> members = cluster.getMembers();
        Address address;
        System.out.println("Members [" + members.size() + "] {");
        StringBuilder sb ;
        for (Member member : members) {
            sb= new StringBuilder();
            address = member.getAddress();
            sb.append("    [");
            sb.append(member.getStringAttribute("user"));
            sb.append(" -> Instance ");
            sb.append(address.getPort() % 10);
            sb.append("] [");
            sb.append(address.getHost());
            sb.append("]");
            sb.append(":");
            sb.append(address.getPort());
            if (member.localMember()) {
                sb.append(" this");
            }
            if (member.isLiteMember()) {
                sb.append(" lite");
            }
            System.out.println(sb);
        }
        System.out.println("}");
    }
}
