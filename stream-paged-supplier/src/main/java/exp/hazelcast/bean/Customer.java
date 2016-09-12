package exp.hazelcast.bean;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;

import java.io.IOException;
import java.io.Serializable;

/**
 * Bean class to use as value in IMap
 * Created by prasun on 11/9/16.
 */
public class Customer implements Serializable
{
    private String name;
    private int age;
    private String designation;
    private double salary;
    private long customerId;
    private String company;
    private boolean married;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    @Override
    public String toString(){
        return String.format("%s with age of %d is wroking in %s as %s having customer id %d",this.getName(),this.getAge(), this.getCompany(), this.getDesignation(), this.getCustomerId());
    }

    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(this.getCompany());
        out.writeUTF(this.getDesignation());
        out.writeUTF(this.getName());
        out.writeInt(this.getAge());
        out.writeLong(this.getCustomerId());
        out.writeDouble(this.getSalary());
    }

    public void readData(ObjectDataInput in) throws IOException {
        Customer cust = this;
        cust.setCompany(in.readUTF());
        cust.setDesignation(in.readUTF());
        cust.setName(in.readUTF());
        cust.setAge(in.readInt());
        cust.setCustomerId(in.readLong());
        cust.setSalary(in.readDouble());
    }

}
