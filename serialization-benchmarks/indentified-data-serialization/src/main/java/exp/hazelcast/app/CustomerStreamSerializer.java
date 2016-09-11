package exp.hazelcast.app;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.IOException;

/**
 * Created by prasun on 11/9/16.
 */
public class CustomerStreamSerializer implements StreamSerializer<Customer> {
    public void write(ObjectDataOutput out, Customer customer) throws IOException {
        out.writeUTF(customer.getCompany());
        out.writeUTF(customer.getDesignation());
        out.writeUTF(customer.getName());
        out.writeInt(customer.getAge());
        out.writeLong(customer.getCustomerId());
        out.writeDouble(customer.getSalary());
    }

    public Customer read(ObjectDataInput in) throws IOException {
        Customer cust = new Customer();
        cust.setCompany(in.readUTF());
        cust.setDesignation(in.readUTF());
        cust.setName(in.readUTF());
        cust.setAge(in.readInt());
        cust.setCustomerId(in.readLong());
        cust.setSalary(in.readDouble());
        return cust;
    }

    public int getTypeId() {
        return 1729;
    }

    public void destroy() {

    }
}
