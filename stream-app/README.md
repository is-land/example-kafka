# stream app

## How to Start

```bash
./gradlew clean build
java -cp stream-app/build/libs/stream-app.jar island.stuff.streams.WordCountExample $BOOTSTRAP_SERVERS $INPUT_TOPIC_NAME $OUTPUT_TOPIC_NAME
```

Usage

```bash
$BOOTSTRAP_SERVERS = Kafka server to connect to. e.q. localhost:9092
$INPUT_TOPIC_NAME  = Source topic.
$OUTPUT_TOPIC_NAME = Target topic.
```

Create the input and output topics used by this example.
```bash
$KAFKA_HOME/bin/kafka-topics.sh --bootstrap-server localhost:9092 \
                                --create --topic fromTopic \
                                --partitions 1 --replication-factor 1

$KAFKA_HOME/bin/kafka-topics.sh --bootstrap-server localhost:9092 \
                                --create --topic toTopic \
                                --partitions 1 --replication-factor 1
```

Start the console producer:

```bash
$KAFKA_HOME/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic fromTopic
```

You can then enter input data by writing some line of text and followed by ENTER:
```bash
 qwe<ENTER>
 qwe<ENTER>
 qwe<ENTER>
 asd<ENTER>
 asd<ENTER>
 asd<ENTER>
```

Start the console producer to inspect the resulting data in the output topic:

```bash
 $KAFKA_HOME/bin/kafka-console-consumer.sh --topic toTopic --from-beginning \
                                           --bootstrap-server localhost:9092 \
                                           --property print.key=true \
                                           --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
```

You should see output data similar to below.

```bash
qwe	1
qwe	2
qwe	3
asd	1
asd	2
asd	3
```

Start Stream App

```bash
java -cp .:./stream-app/build/libs/*:./stream-app/build/libs/stream-app.jar \
         island.stuff.streams.WordCountExample 'localhost:9092' fromTopic toTopic
```