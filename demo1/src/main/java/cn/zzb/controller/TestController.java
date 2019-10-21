package cn.zzb.controller;

import cn.zzb.component.core.LogAnno;
import cn.zzb.component.mq.sender.ImmediateSender;
import cn.zzb.domain.Booking;
import cn.zzb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author Kyrie
 * @Date 2019/7/4
 */

/**
 * @author lastwhisper
 * @desc
 * @email gaojun56@163.com
 */
@RestController
public class TestController {

    @Autowired
    private ImmediateSender immediateSender;

    /**
     * @return
     * @desc 这里为了方便直接在controller上进行aop日志记录，也可以放在service上。
     * or lastwhisper
     * @Param
     */
    @LogAnno(operateType = "添加用户")
    @PostMapping("/user/add")
    public void add() {
        System.out.println("向数据库中添加用户!!");
        System.out.println(1 / 0);
    }

    @PostMapping("/exception")
    public User exception(@RequestBody @Validated User user) {
        return user;
    }

    @PostMapping("/test1")
    public void test1() {
        Booking booking = new Booking();
        booking.setBookingContent("haha-1");
        booking.setBookingName("预定房子-1");
        booking.setBookingTime(new Date());
        booking.setOperatorName("hello-1");
        immediateSender.send(booking, 10000);
    }

    @PostMapping("/test2")
    public void test2() {
        Booking booking = new Booking();
        booking.setBookingContent("haha-2");
        booking.setBookingName("预定房子-2");
        booking.setBookingTime(new Date());
        booking.setOperatorName("hello-2");
        immediateSender.send(booking, 1000);
    }

    @PostMapping("/test3")
    public void test3() {
        Booking booking = new Booking();
        booking.setBookingContent("haha-3");
        booking.setBookingName("预定房子-3");
        booking.setBookingTime(new Date());
        booking.setOperatorName("hello-3");
        booking.setCount(0);
        immediateSender.send2(booking);
    }
}
