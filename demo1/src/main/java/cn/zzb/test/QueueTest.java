package cn.zzb.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author Kyrie
 * @Date 2019/10/17
 */
public class QueueTest {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin135");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //第二个参数表示是否持久化,第三个参数是判断这个队列是否在连接是否生效,为true表示连接关闭队列删除。
        channel.queueDeclare("zhihao.info", true, false, false, null);
        System.out.println("ok");

        //创建exchange，类型是direct类型
        channel.exchangeDeclare("zhihao.miao","direct");
        //创建exchange，类型是direct类型
        channel.exchangeDeclare("zhihao.miao.info", "direct");
        channel.exchangeDeclare("zhihao.miao.order", "direct");

        //异步没有返回值得方法api
        channel.queueDeclareNoWait("zhihao.info.miao", true, false, false, null);

        //exchange和queue进行绑定(可重复执行,不会重复创建)
        channel.queueBind("zhihao.info", "zhihao.miao.order", "info");

        //异步进行绑定
        channel.queueBindNoWait("zhihao.info", "zhihao.miao.order", "info", null);

        // //exchange与exchange进行绑定(可重复执行,不会重复创建)
        // channel.exchangeBind("zhihao.miao.email", "zhihao.miao.weixin", "debug");

        // //exchange和queue进行解绑(可重复执行)
        // channel.exchangeUnbind("zhihao.info", "zhihao.miao.order", "info");

        // //exchange和exchange进行解绑(可重复执行)
        // channel.exchangeUnbind("zhihao.info.miao", "zhihao.miao.pay", "debug");

        // //删除队列
        // channel.queueDelete("zhihao.info");

        // channel.basicPublish(, , , );

        channel.close();
        connection.close();
    }
}
