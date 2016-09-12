package exp.hazelcast.startup;

import com.google.common.base.Stopwatch;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import exp.hazelcast.bean.Customer;
import exp.hazelcast.factory.CustomerUtil;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Hazelcast node to supply value into IMap
 * Created by prasun on 12/9/16.
 */
public class ServerNode {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        Map<Integer, Customer> customers = hazelcastInstance.getMap("default");
        int totalCustomers = 500000;
        Stopwatch time = Stopwatch.createStarted();
        for( int i = 0 ; i < totalCustomers ; i++ ) {
            customers.put(i, CustomerUtil.createCustomer());
        }
        time.stop();
        System.out.println("Serialization took: " + time.elapsed(TimeUnit.MILLISECONDS) + " milliseconds");

    }
}
