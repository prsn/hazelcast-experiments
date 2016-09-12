package exp.hazelcast.stream;

import com.hazelcast.core.IMap;

import java.util.stream.Stream;
import static java.util.Map.Entry;

/**
 * A wrapper class for {@link IMap} to allow iteration of {@link Entry} using Java {@link Stream}.
 * Created by prasun on 12/9/16.
 */
public class StreamifyIMap<K, V> {

    private IMap<K, V> iMap;
    private int pageSize;

    private StreamifyIMap() {
        iMap = null;
        pageSize = 500;
    }
    private StreamifyIMap(IMap<K, V> iMap, int pageSize) {
        this.iMap = iMap;
        this.pageSize = pageSize;
    }

    public Stream<Entry<K,V>> stream() {
        StreamPagedSupplier<K,V> streamPagedSupplier = new StreamPagedSupplier<>(iMap, pageSize);
        return Stream.generate(streamPagedSupplier).limit(iMap.size());
    }

    public static class Builder<T, P> {
        StreamifyIMap<T, P> wrapper = null;
        public Builder() {
            wrapper  = new StreamifyIMap<T, P>();
        }
        public Builder withIMap(IMap<T, P> iMap) {
            wrapper.iMap = iMap;
            return this;
        }

        public Builder withPageSize(int pageSize) {
            wrapper.pageSize = pageSize;
            return this;
        }

        public StreamifyIMap build() {
            if ( wrapper.iMap == null )
                throw new IllegalStateException("IMap can not null");
            if ( wrapper.pageSize < 1 )
                throw new IllegalStateException("Page size can not be less than 1 ");
            return wrapper;
        }
    }
}
