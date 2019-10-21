package cn.zzb.component.mq.immediateReceiver;

import cn.zzb.component.mq.sender.ImmediateSender;
import cn.zzb.domain.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 *
 * @Author Kyrie
 * @Date 2019/10/21
 */
@Component
@EnableRabbit
@Configuration
@Slf4j
public class ImmediateReceiver {

    @Autowired
    private ImmediateSender immediateSender;

    @RabbitListener(queues = "immediate_queue")
    @RabbitHandler
    public void get1(Booking booking) {
        System.out.println("收到延时信息啦" + booking);
    }

    @RabbitListener(queues = "immediate_queue2")
    @RabbitHandler
    public void get2(Booking booking) {
        if (booking.getCount() <= 3) {
            //失败,重试
            log.info("消费信息失败: " + booking + "加入到延迟队列");
            booking.setCount(booking.getCount() + 1);
            immediateSender.delaySend(booking, 3000);
        } else {
            log.info("消费信息成功: " + booking);
        }
    }
}
