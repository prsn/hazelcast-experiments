# Stream Page Supplier for Hazelcast IMap

![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)

Stream Page Supplier wraps a Hazelcast IMap in a stream (java 8) and allow user t to access data in using stream API. Hazelcast IMap inherits from java.util.concurrent.ConcurrentMap but does not offer stream API to work over collection. This is a small endavour to offer stream based processing of Hazelcast IMap. Follwoings are the objectives that this supplier solved

  - Java 8 stream based processing for Hazelcast IMap
  - Fetch data in page mannger using PagingPredicates of Hazelcast
  
### Problem statement:
If you are using Hazelcast has in-memory data store, then you probably know that you can read data from Hazelcast using its own IMap interface. This interface offer API to read/write data from/to hazelcast, but this interface does not support java stream API. For reading purpose Hazelcast does support predicates, using which we can read data in page manner. Access data in page manner helps us to get ride of OutOfMemoryError, which is very important for data centric applications.

### Solution: 
The solution is to wrap the IMap access within a object and return a Stream so that application code easily access IMap using Stream API. Now while supplying data for IMap stream, we can use Hazelcast's PagingPredicate to fetch data in page manner, hence with careful processing we can avoid OutOfMemoryError.

### Todos

 - Yet to be decided

License
----

MIT


**Free Software, Hell Yeah!**
