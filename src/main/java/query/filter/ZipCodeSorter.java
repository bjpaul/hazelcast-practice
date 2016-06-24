package query.filter;

import query.data.Address;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by bijoy on 24/6/16.
 */
public class ZipCodeSorter implements Comparator<Map.Entry>, Serializable {
    final static long serialVersionUID = 1l;

    private Order order;
    public ZipCodeSorter(Order order){
        this.order = order;
    }
    @Override
    public int compare(Map.Entry o1, Map.Entry o2) {
        Address address1 = (Address) o1.getValue();
        Address address2 = (Address) o2.getValue();
        return address1.getZipCode().compareTo(address2.getZipCode()) * order.getOrder();
    }
}
