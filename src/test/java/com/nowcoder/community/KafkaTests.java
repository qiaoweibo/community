package com.nowcoder.community;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class KafkaTests {

    @Autowired
    private KafkaProducer kafkaProducer;

    /**
     * 一般都对生产者和消费者的代码进行各自的封装
     *
     * @author QiaoWeiBo
     * @date 2022/6/16 11:14 AM
     */
    @Test
    public void testKafka() {

        kafkaProducer.sendMessage("test", "你好");
        kafkaProducer.sendMessage("test", "在吗");

        //发完消息之后，等一会再结束
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

/**
 * 生产者发送消息是主动,主动去调用send方法
 *
 * @author QiaoWeiBo
 * @date 2022/6/16 4:50 PM
 */
@Component
class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String topic, String data) {
        kafkaTemplate.send(topic, data);
    }

}

/**
 * 消费者处理消息是被动的去调用相关方法的，队列中有消息才会调用(但是可能会有延迟)
 *
 * @author QiaoWeiBo
 * @date 2022/6/16 4:50 PM
 */
@Component
class KafkaConsumer {

    @KafkaListener(topics = {"test"})
    public void handleMessage(ConsumerRecord record) {
        System.out.println(record.value());
    }

}
