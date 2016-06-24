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
    }

    @Override
    public void memberRemoved(MembershipEvent membershipEvent) {
        System.out.println("#####################"+ membershipEvent);
    }

    @Override
    public void memberAttributeChanged(MemberAttributeEvent memberAttributeEvent) {
        System.out.println("##################### Member "+ memberAttributeEvent.getMember().getAddress()+", attribute "+memberAttributeEvent.getValue());
    }

}
