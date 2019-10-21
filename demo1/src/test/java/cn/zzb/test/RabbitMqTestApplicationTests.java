package cn.zzb.test;

import cn.zzb.component.mq.sender.ImmediateSender;
import cn.zzb.domain.Booking;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Author Kyrie
 * @Date 2019/10/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTestApplicationTests {

    @Autowired
    private ImmediateSender immediateSender;

    @Test
    public void test() {
        Booking booking = new Booking();
        booking.setBookingContent("haha");
        booking.setBookingName("预定房子");
        booking.setBookingTime(new Date());
        booking.setOperatorName("hello");
        immediateSender.send(booking, 1000);
    }
}
