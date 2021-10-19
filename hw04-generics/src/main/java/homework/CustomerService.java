package homework;


import java.util.*;

public class CustomerService {

    private final NavigableMap<Customer, String> storage = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    private Map.Entry<Customer, String> copyEntry(Map.Entry<Customer, String> original) {
        return Optional
                .ofNullable(original)
                .map(entry -> new AbstractMap.SimpleEntry<>(new Customer(entry.getKey()), entry.getValue()))
                .orElse(null);
    }

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> originalEntry = storage.firstEntry();
        return copyEntry(originalEntry);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> originalEntry = storage.higherEntry(customer);
        return copyEntry(originalEntry);
    }

    public void add(Customer customer, String data) {
        storage.put(customer, data);
    }
}
