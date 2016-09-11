package exp.hazelcast.app;

import com.google.common.base.Stopwatch;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */

/**
 * Serialization benchmarks, reference
 * http://blog.hazelcast.com/comparing-serialization-methods/
 */
public class StartupNode2
{
    public static void main( String[] args ) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        Map<Integer, Customer> customers = hazelcastInstance.getMap("default");
        int totalCustomers = 1000000;
        Stopwatch time = Stopwatch.createStarted();
        for( int i = 500000 ; i < totalCustomers ; i++ ) {
            customers.put(i, CustomerUtil.createCustomer());
        }
        time.stop();
        System.out.println("Serialization took: " + time.elapsed(TimeUnit.MILLISECONDS) + " milliseconds");

    }
}
