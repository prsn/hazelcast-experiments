package exp.hazelcast.startup;

import com.google.common.base.Stopwatch;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import exp.hazelcast.bean.Customer;
import exp.hazelcast.stream.StreamifyIMap;

import java.util.concurrent.TimeUnit;

/**
 * Hazelcast client to read IMap value from a stream
 */
class StartApp {
    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient(config);
        System.out.println("Reading customers...");
        Stopwatch time = Stopwatch.createStarted();
        IMap<Integer, Customer> customers = client.getMap("default");
        StreamifyIMap<Integer, Customer> streamifyIMap = new StreamifyIMap.Builder<Integer, Customer>()
                .withIMap(customers)
                .withPageSize(10000)
                .build();
        System.out.println("Total customers to read: " + customers.size());
        streamifyIMap.stream()
                .filter(f -> f.getValue().getCustomerId() % 1000 == 0)
                .forEach(e -> {
            //e.getValue();
                    System.out.println(e.getValue());
        });
        time.stop();
        System.out.println("Time taken to read objects: " + time.elapsed(TimeUnit.MILLISECONDS));

    }
}