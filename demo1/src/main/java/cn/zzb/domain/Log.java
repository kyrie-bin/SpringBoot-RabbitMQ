package cn.zzb.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author Kyrie
 * @Date 2019/7/4
 */
@Data
@TableName("log")
public class Log {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("operateor")
    private String operateor;

    @TableField("operateType")
    private String operatetype;

    @TableField("operateDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operatedate;

    @TableField("operatereSult")
    private String operateresult;

    @TableField("ip")
    private String ip;
}
