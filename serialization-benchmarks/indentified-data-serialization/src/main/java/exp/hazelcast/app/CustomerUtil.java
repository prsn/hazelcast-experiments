package exp.hazelcast.app;

import java.util.Random;

/**
 * Created by prasun on 11/9/16.
 */
public class CustomerUtil {

    public static Customer createCustomer() {
        Customer cust =  new Customer();
        cust.setAge((int)(new Random().nextDouble()*80) + 1);
        cust.setCompany("Self");
        cust.setCustomerId((long)(new Random().nextDouble()* (Long.MAX_VALUE - 10000)) + 10000);
        cust.setDesignation("Undecided");
        cust.setMarried((int)(new Random().nextDouble() * 1) == 0? true : false);
        cust.setName("Undeclared");
        cust.setSalary(new Random().nextDouble());
        return cust;
    }
}
