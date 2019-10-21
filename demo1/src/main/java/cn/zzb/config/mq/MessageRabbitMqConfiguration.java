package cn.zzb.config.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author Kyrie
 * @Date 2019/10/18
 */
@Configuration
public class MessageRabbitMqConfiguration {

    /**
     * 创建一个立即消费队列
     *
     * @return
     */
    @Bean
    public Queue immediateQueue() {
        //第一个参数是创建queue的名字,第二个参数是是否支持持久化
        return new Queue("immediate_queue", true);
    }

    /**
     * 创建一个延时队列
     *
     * @return
     */
    @Bean
    public Queue delayQueue() {
        Map<String, Object> params = new HashMap<>();
        //x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称
        params.put("x-dead-letter-exchange", "immediate_exchange");
        //x-dead-letter-routing-key 声明了这些死信在转发时携带的routing-key名称
        params.put("x-dead-letter-routing-key", "immediate_routing_key");
        return new Queue("delay_queue", true, false, false, params);
    }

    /**
     * 路由立即消费
     *
     * @return
     */
    @Bean
    public DirectExchange immediateExchange() {
        //一共有三种构造方法,可以只传exchange的名字, 第二种,可以传exchange名字,是否支持持久化,是否可以自动删除
        //第三种在第二种参数上可以增加Map,Map中可以存放自定义exchange中的参数
        return new DirectExchange("immediate_exchange", true, false);
    }

    /**
     * 路由死信
     *
     * @return
     */
    @Bean
    public DirectExchange deadLetterExchange() {
        //一共有三种构造方法,可以只传exchange的名字, 第二种,可以传exchange名字,是否支持持久化,是否可以自动删除
        //第三种在第二种参数上可以增加Mao,Map中可以存放自定义exchange中的参数
        return new DirectExchange("dead_letter_exchange", true, false);
    }

    /**
     * 把立即消费的队列和立即消费的exchange绑定在一起
     *
     * @return
     */
    @Bean
    public Binding immediateBinding() {
        return BindingBuilder.bind(immediateQueue()).to(immediateExchange()).with("immediate_routing_key");
    }

    /**
     * 把立即消费的队列和立即消费的exchange绑定在一起
     *
     * @return
     */
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(deadLetterExchange()).with("delay_routing_key");
    }

    //========================================================以下是延时重试==============================================================//

    /**
     * 创建一个立即消费队列
     *
     * @return
     */
    @Bean
    public Queue immediateQueue2() {
        return new Queue("immediate_queue2", true, false, false);
    }

    /**
     * 创建一个延时队列
     *
     * @return
     */
    @Bean
    public Queue delayQueue2() {
        Map<String, Object> params = new HashMap<>();
        //x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称
        params.put("x-dead-letter-exchange", "immediate_exchange2");
        //x-dead-letter-routing-key 声明了这些死信在转发时携带的routing-key名称
        params.put("x-dead-letter-routing-key", "immediate_routing_key2");
        //多长时间发送
        params.put("x-message-ttl", 10000);
        //第一个参数是创建queue的名字,第二个参数是是否支持持久化
        return new Queue("delay_queue2", true, false, false, params);
    }

    /**
     * 路由立即消费
     *
     * @return
     */
    @Bean
    public DirectExchange immediateExchange2() {
        //一共有三种构造方法,可以只传exchange的名字, 第二种,可以传exchange名字,是否支持持久化,是否可以自动删除
        //第三种在第二种参数上可以增加Map,Map中可以存放自定义exchange中的参数
        return new DirectExchange("immediate_exchange2", true, false);
    }

    /**
     * 路由死信
     *
     * @return
     */
    @Bean
    public DirectExchange deadLetterExchange2() {
        //一共有三种构造方法,可以只传exchange的名字, 第二种,可以传exchange名字,是否支持持久化,是否可以自动删除
        //第三种在第二种参数上可以增加Mao,Map中可以存放自定义exchange中的参数
        return new DirectExchange("dead_letter_exchange2", true, false);
    }

    /**
     * 把立即消费的队列和立即消费的exchange绑定在一起
     *
     * @return
     */
    @Bean
    public Binding immediateBinding2() {
        return BindingBuilder.bind(immediateQueue2()).to(immediateExchange2()).with("immediate_routing_key2");
    }

    /**
     * 把延时消费的队列和延时消费的exchange绑定在一起
     *
     * @return
     */
    @Bean
    public Binding delayBinding2() {
        return BindingBuilder.bind(delayQueue2()).to(deadLetterExchange2()).with("delay_routing_key2");
    }
}
