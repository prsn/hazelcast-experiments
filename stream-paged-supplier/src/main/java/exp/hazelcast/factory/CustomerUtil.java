package exp.hazelcast.factory;

import exp.hazelcast.bean.Customer;

import java.util.Random;

/**
 * Utility class to generate {@link Customer customer} based on random values
 * Created by prasun on 11/9/16.
 */
public class CustomerUtil {

    private static long count = 1;

    public static Customer createCustomer() {
        Customer cust =  new Customer();
        cust.setAge((int)(new Random().nextDouble()*80) + 1);
        cust.setCompany("Self");
        cust.setCustomerId(count);
        cust.setDesignation("Undecided");
        cust.setMarried((int)(new Random().nextDouble() * 1) == 0? true : false);
        cust.setName(String.format("Name [%d]", count++));
        cust.setSalary(new Random().nextDouble());
        return cust;
    }
}
