package cn.zzb.component.mq.sender;

import cn.zzb.domain.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息生产者
 *
 * @Author Kyrie
 * @Date 2019/10/21
 */
@Slf4j
@Component
public class ImmediateSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 延迟消费
     *
     * @param booking
     * @param delayTime
     */
    public void send(Booking booking, final long delayTime) {
        log.info("延迟：{}毫秒写入消息队列：{}", delayTime, booking);
        rabbitTemplate.convertAndSend("dead_letter_exchange", "delay_routing_key", booking, message -> {
            message.getMessageProperties().setExpiration(delayTime + "");
            return message;
        });
    }

    /**
     * 重试
     *
     * @param booking
     */
    public void send2(Booking booking) {
        log.info("立即写入消息队列：{}", booking);
        rabbitTemplate.convertAndSend("immediate_exchange2", "immediate_routing_key2", booking, message -> {
            message.getMessageProperties();
            return message;
        });
    }

    /**
     * 发送到延迟队列
     *
     * @param booking     * @param delayTime
     */
    public void delaySend(Booking booking, final long delayTime) {
        log.info("写入重试消息队列：{}" + delayTime + "毫秒后消费", booking);
        rabbitTemplate.convertAndSend("dead_letter_exchange2", "delay_routing_key2", booking, message -> {
            message.getMessageProperties().setExpiration(delayTime + "");
            return message;
        });
    }
}
