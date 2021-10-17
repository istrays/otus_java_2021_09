package homework;


import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {

    private final NavigableMap<Customer, String> storage = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return storage.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return storage.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        storage.put(customer, data);
    }
}
