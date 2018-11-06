# aggregation-system

### Binary Production
To build the binary production navigate to root folder and execute: `mvn clean package`

### Application modules
The application consists of the following modules:

#### thrift-server
Navigate to directory thrift-server and execute mvn spring-boot:run

#### kafka-consumer
Navigate to directory kafka-consumer and execute mvn spring-boot:run

#### thrift-client
Navigate to directory thrift-client and execute mvn spring-boot:run

#### thrift-generator
The thrift-generator module generates the ThriftMessage class which is used by the other modules. To achieve this the module is injected
through maven dependency to the other modules.

### Running kafka
Download kafka distribution from kafka website.
Execute following commands:
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic events

### Cassandra (keyspace and table creation)
After downloading and installing Cassandra run the following in CQL shell:

CREATE KEYSPACE IF NOT EXISTS EventsSpace
WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }

- 'class' : 'SimpleStrategy' (used for a single datacenter and one rack, most appropriate in development environment)
- 'replication_factor' : 1 (replication factor of one means that there is only one copy of each row in the Cassandra cluster)

CREATE TABLE EventsSpace.message (
  id uuid,
  m text,
  v int,
  date timestamp,
  PRIMARY KEY (id, date)
) with CLUSTERING ORDER BY (date DESC);

The table has a compound primary key consisting of id and date columns.
Column date is the clustering key and is used to sort results by date descending.

### Thrift types selection for message (logging event)

For m (the logging message), string type is selected as a natutal choice.

For time i64 is selected as the most appropriate type for timestamp.