# Kafka installation: Case 1

Simplest installation for testing.
 
* One zookeeper & One broker
* Zero configuration, totally default.
* Zookeeper default client port: 2181
* Broker default listener port: 9092


## Download & extract

```bash
[kafka-lab]$ wget https://archive.apache.org/dist/kafka/2.2.0/kafka_2.12-2.2.0.tgz
[kafka-lab]$ tar zxvf kafka_2.12-2.2.0.tgz
[kafka-lab]$ cd kafka_2.12-2.2.0
[kafka_2.12-2.2.0]$ ls -l
```

## Startup server

```bash
[kafka_2.12-2.2.0]$ bin/zookeeper-server-start.sh config/zookeeper.properties
[kafka_2.12-2.2.0]$ bin/kafka-server-start.sh config/server.properties
```

