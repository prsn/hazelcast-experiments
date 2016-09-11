package exp.hazelcast.app;

import com.google.common.base.Stopwatch;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import javax.sound.midi.SysexMessage;
import java.util.concurrent.TimeUnit;


/**
 * Created by prasun on 10/9/16.
 */
public class SampleAppClient {

    public static void main(String[] args) {
        SerializerConfig sc = new SerializerConfig()
                .setImplementation(new CustomerStreamSerializer())
                .setTypeClass(Customer.class);
        ClientConfig config = new ClientConfig();
        //config.getSerializationConfig().addSerializerConfig(sc);
        config.getSerializationConfig().addDataSerializableFactory(CustomerDataSerializableFactory.FACTORY_ID, new CustomerDataSerializableFactory());
        HazelcastInstance client = HazelcastClient.newHazelcastClient(config);
        System.out.println("Reading customers...");
        Stopwatch time = Stopwatch.createStarted();
        IMap<Integer, Customer> customers = client.getMap("default");
        System.out.println("Total customers to read: " + customers.size());
        for( int i =0 ; i < customers.size(); i++) {
            customers.get(i);
        }
        time.stop();
        System.out.println("Time taken to read objects: " + time.elapsed(TimeUnit.MILLISECONDS));

    }
}
