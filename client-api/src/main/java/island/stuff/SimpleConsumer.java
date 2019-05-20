package island.stuff;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

public class SimpleConsumer {

    final static String TOPIC_NAME = "topic3";

    public static void main(String[] args) {
        new SimpleConsumer().run();
    }

    private Properties props() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9091");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "SimpleConsumer4");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return props;
    }

    private void run() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props());
        Collection<String> topics = Collections.singletonList(TOPIC_NAME);
        consumer.subscribe(topics);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record: records) {
                String message = String.format("Message: [key]%s, [value]%s, [offset]%s", record.key(), record.value(), record.offset());
                System.out.println(message);
            }
            //consumer.commitSync();
        }
    }

}
