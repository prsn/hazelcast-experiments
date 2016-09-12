package exp.hazelcast.stream;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.hazelcast.core.IMap;
import com.hazelcast.query.PagingPredicate;

import java.util.Set;
import java.util.function.Supplier;

import static java.util.Map.Entry;

/**
 * Class to work as a stream supplier. It will fetch data from {@link IMap hazlecast} in paged manner
 * and will store those paged data locally, when stream will ask for next item through {@link StreamPagedSupplier#get()}
 * method {@link StreamPagedSupplier} class will return next item from local storage. When all the items for a particular
 * page will be served it will fetch next page data from {@link IMap hazlecast} using {@link PagingPredicate}.
 *
 * Created by prasun on 12/9/16.
 */
public class StreamPagedSupplier <K, V> implements Supplier<Entry<K, V>> {

    private final IMap dataStore;
    private PagingPredicate predicate;
    /**
     * Stores element reference locally to support stream paging
     */
    private Set<Entry<K,V>> cachedElement;
    /**
     * Counts total numbers of records processed for current page, it is used for data retrival from {@link #cachedElement}
     */
    private volatile int elementProcessed;
    /**
     * Items per page
     */
    private int pageSize;

    private StreamPagedSupplier() {
        this.dataStore = null;
    }

    /**
     *
     * @param dataStore The {@link IMap hazelcast} object to retrieve items
     * @param pageSize Indicates number of records in a page
     */
    public StreamPagedSupplier(final IMap<K, V> dataStore, int pageSize) {
        Preconditions.checkNotNull(dataStore);
        Preconditions.checkArgument(pageSize > 0);
        this.dataStore = dataStore;
        this.predicate = new PagingPredicate(pageSize);
        this.cachedElement = dataStore.entrySet(predicate);
        this.elementProcessed = 0;
        this.pageSize = pageSize;
    }

    /**
     * Returns next element for current page using locally cached reference. It total items returned is equals to
     * {@link #pageSize} it will fetch next paged records.
     * @return
     */
    private Entry<K, V> nextElement() {
        if ( elementProcessed == pageSize) {
            //fetch next paged result
            predicate.nextPage();
            cachedElement = dataStore.entrySet(predicate);
            elementProcessed = 0;
        }
        Entry<K, V> element = Iterables.get(cachedElement, elementProcessed);
        elementProcessed++;
        return element;
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public Entry<K, V> get() {
        return nextElement();
    }
}