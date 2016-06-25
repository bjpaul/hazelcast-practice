package query;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.Member;
import com.hazelcast.core.Partition;
import com.hazelcast.query.PagingPredicate;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import instance.ClientInstance;
import query.dao.AddressDao;
import query.data.Address;
import query.filter.CitySorter;
import query.filter.Order;
import query.filter.ZipCodeSorter;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by bijoy on 24/6/16.
 */
public class AddressFinder {
    private static HazelcastInstance hazelcastInstance = ClientInstance.instance();

    public static void main(String[] args) {
//        predicateSol1();
//        predicateSol2();
//        predicateSol2(new CitySorter(Order.DESC), 5);
//        predicateSol2(new ZipCodeSorter(Order.ASC),5);
//        predicateSol2(5);
        predicateSol3();
        conventionalSol3();
    }

    public static void predicateSol1() {
        System.out.println("< ------------- Sol1 ------------- >");
        IMap<String, Address> addressIMap = hazelcastInstance.getMap("cityAddress");
        Predicate<String, Address> predicate = AddressDao.find("zipCode", 110008, 110191, 110090);
        System.out.println("Starting...");
        long start = System.currentTimeMillis();
        for (Address address : addressIMap.values(predicate)) {
            System.out.println(printAddress(address));
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time taken " + (end - start));
    }

    public static void predicateSol2() {
        System.out.println("< ------------- Sol2 ------------- >");
        IMap<String, Address> addressIMap = hazelcastInstance.getMap("cityAddress");
        Predicate<String, Address> predicate = AddressDao.find("city", "Mumbai", "Kolkata", "Delhi");
        System.out.println("Starting...");
        long start = System.currentTimeMillis();
        for (Address address : addressIMap.values(predicate)) {
            System.out.println(printAddress(address));
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time taken " + (end - start));
    }

    public static void predicateSol2(int pageSize) {
        System.out.println("< ------------- Sol2 ------------- >");
        IMap<String, Address> addressIMap = hazelcastInstance.getMap("cityAddress");
        Predicate<String, Address> predicate = AddressDao.find("city", "Mumbai", "Kolkata", "Delhi");
        System.out.println("Starting...");
        long start = System.currentTimeMillis();
        PagingPredicate pagingPredicate = new PagingPredicate(predicate,pageSize);
        for (Address address : addressIMap.values(pagingPredicate)) {
            System.out.println(printAddress(address));
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time taken " + (end - start));
    }

    public static void predicateSol2(Comparator comparator, int pageSize) {
        System.out.println("< ------------- Sol2 ------------- >");
        IMap<String, Address> addressIMap = hazelcastInstance.getMap("cityAddress");
        Predicate<String, Address> predicate = AddressDao.find("city", "Mumbai", "Kolkata", "Delhi");
        System.out.println("Starting...");
        long start = System.currentTimeMillis();
        PagingPredicate pagingPredicate = new PagingPredicate(predicate, comparator, pageSize);
        for (Address address : addressIMap.values(pagingPredicate)) {
            System.out.println(printAddress(address));
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time taken " + (end - start));
    }

    public static void predicateSol3() {
        System.out.println("< ------------- Sol3 ------------- >");
        System.out.println("< ------------- By Predicate ------------- >");
        IMap<String, Address> addressIMap = hazelcastInstance.getMap("cityAddress");

        Predicate<String, Address> delhiPredicate = AddressDao.find("city", "Delhi");

        Predicate<String, Address> zipCodePredicate = AddressDao.find("zipCode", 110008, 110191, 110090);

        Predicate<String, Address> delhiWithZipCodePredicate = Predicates.and(delhiPredicate, zipCodePredicate);

        System.out.println("Starting...");
        long start = System.currentTimeMillis();
        for (Address address : addressIMap.values(delhiWithZipCodePredicate)) {
            System.out.println(printAddress(address));
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time taken " + (end - start));
    }

    public static void conventionalSol3() {
        System.out.println("< ------------- Sol3 ------------- >");
        System.out.println("< ------------- By Iterator ------------- >");
        IMap<String, Address> addressIMap = hazelcastInstance.getMap("cityAddress");
        System.out.println("Starting...");
        long start = System.currentTimeMillis();
        for (Map.Entry<String, Address> entry : addressIMap.entrySet()) {
            Address address = entry.getValue();
            if (address.getCity().equals("Delhi")) {
                if (address.getZipCode() != 110090 && address.getZipCode() != 110008 && address.getZipCode() != 110191) {
                    continue;
                }
                System.out.println(printAddress(address));
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time taken " + (end - start));
    }

    public static String printAddress(Address address){
        String msg = address.toString();
        Partition partition = hazelcastInstance.getPartitionService().getPartition(address.getCity() + " -> " + address.getZipCode());
        Member member = partition.getOwner();

        msg+="{PID = "+partition.getPartitionId()+", Owner = "+member.getAddress().getHost()+":"+member.getAddress().getPort()+"}";
        return msg;
    }
}
