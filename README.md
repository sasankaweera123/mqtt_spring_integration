# mqtt_spring_integration

Simple restApi about MQTT protocol using [Eclipse Mosquitto](https://mosquitto.org/) (open source MQTT broker).

## Publisher 

Defult port:  `127.0.0.1:1883`

Using CMD: `mosquitto -t myTopic -m "sasa"`

File Location : `C:\Program Files\mosquitto`

### or

POST Rquest: `localhost:8080/send`

```json
{
    "topic":"myTopic",
    "message":{
        "data":"sasa"
    }
}
```

## Dependency 

- [spring-integration-mqtt](https://docs.spring.io/spring-integration/reference/mqtt.html)
- [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson)
