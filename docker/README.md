kafka-docker
============

Dockerfile for [Apache Kafka](http://kafka.apache.org/)

The image is available directly from [Docker Hub](https://hub.docker.com/r/wurstmeister/kafka/)

To see more information on github. [wurstmeister/kafka-docker](https://github.com/wurstmeister/kafka-docker)

Tutorial: [http://wurstmeister.github.io/kafka-docker/](http://wurstmeister.github.io/kafka-docker/)

## How to start 
1.	**(Necessary)** Install [Docker](https://www.docker.io/gettingstarted/#h_installation)
2.	(Optional) Update `docker-compose.yml` with your docker host IP (`KAFKA_ADVERTISED_HOST_NAME`\)
3.	(Optional) If you want to customise any Kafka parameters, simply add them as environment variables in `docker-compose.yml`. For example:
	-	to increase the `message.max.bytes` parameter add `KAFKA_MESSAGE_MAX_BYTES: 2000000` to the `environment` section.
	-	to turn off automatic topic creation set `KAFKA_AUTO_CREATE_TOPICS_ENABLE: 'false'`
54	Start the cluster

## Usage

Start a cluster:

- ```docker-compose up -d ```

Add more brokers:

- ```docker-compose scale kafka=3```

Destroy a cluster:

- ```docker-compose stop```

## Broker IDs

Default Broker IDs: 1001 with single node, and it would be 1002, 1003, ....1xxx when you scale the kafka.

You can also configure the broker id in different ways.

1. explicitly, using ```KAFKA_BROKER_ID```
2. via a command, using ```BROKER_ID_COMMAND```, e.g. ```BROKER_ID_COMMAND: "hostname | awk -F'-' '{print $$2}'"```

If you don't specify a broker id in your docker-compose file, it will automatically be generated (see [https://issues.apache.org/jira/browse/KAFKA-1070](https://issues.apache.org/jira/browse/KAFKA-1070). This allows scaling up and down. In this case it is recommended to use the ```--no-recreate``` option of docker-compose to ensure that containers are not re-created and thus keep their names and ids.