package exp.hazelcast.app;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

/**
 * Created by prasun on 11/9/16.
 */
public class CustomerDataSerializableFactory implements DataSerializableFactory {
    public static final int FACTORY_ID = 1;

    public static final int CUSTOMER_TYPE = 1;

    public IdentifiedDataSerializable create(int typeId) {
        if ( typeId == CUSTOMER_TYPE) {
            return new Customer();
        } else {
            return null;
        }
    }
}
