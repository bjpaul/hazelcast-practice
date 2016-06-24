package query.filter;

/**
 * Created by bijoy on 24/6/16.
 */
public enum Order {
    ASC("ASC",1),
    DESC("DESC",-1);

    String value;
    int order;

    Order(String value, int order){
        this.value = value;
        this.order = order;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
