package cn.zzb.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Kyrie
 * @Date 2019/10/21
 */
@Data
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bookingName;

    private Date bookingTime;

    private String bookingContent;

    private String operatorName;

    private Integer count;

}
