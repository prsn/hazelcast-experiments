Stream paged supplier, which work as a provider to stream data in page manner.

Problem: Using Hazelcast, you can read all the using one the hazelcasr distributed collection implementation, but non of them support java 8 stream. More over, if you are about to read huge data from hazelcast cluster, then there is likelyhood that you may run out of memory.

Solution: To provide a stream api for hazelcast IMap, we can easily wrap IMap object with in a stream supplier class and provide data when needed. Quite simple implementation, but to overcome the memory related problem, we need to fetch data from hazelcast in a pged maner. Hazelcast support PagingPredicate to fetch data page wise.

I am going to use hazelcase PagingPredicate to solve memory related problem.
