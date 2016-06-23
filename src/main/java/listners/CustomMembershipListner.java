package listners;

import com.hazelcast.core.*;
import com.hazelcast.nio.Address;

import java.util.Set;

/**
 * Created by bijoy on 18/6/16.
 */
public class CustomMembershipListner implements MembershipListener {
    @Override
    public void memberAdded(MembershipEvent membershipEvent) {
        System.out.println("#####################"+ membershipEvent);
        membersString(membershipEvent.getCluster());
    }

    @Override
    public void memberRemoved(MembershipEvent membershipEvent) {
        System.out.println("#####################"+ membershipEvent);
        membersString(membershipEvent.getCluster());
    }

    @Override
    public void memberAttributeChanged(MemberAttributeEvent memberAttributeEvent) {
        System.out.println("##################### Member "+ memberAttributeEvent.getMember().getAddress()+", attribute "+memberAttributeEvent.getValue());
        membersString(memberAttributeEvent.getCluster());
    }

    private void membersString(Cluster cluster){
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
