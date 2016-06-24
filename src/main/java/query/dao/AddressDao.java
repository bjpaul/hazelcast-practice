package query.dao;

import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import query.data.Address;

/**
 * Created by bijoy on 24/6/16.
 */
public class AddressDao {
    public static Predicate<String, Address> find(String attribute,Comparable... zips){
        return Predicates.in(attribute, zips);
    }

}
