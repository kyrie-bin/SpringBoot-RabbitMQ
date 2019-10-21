package cn.zzb.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author Kyrie
 * @Date 2019/7/4
 */
@Data
public class User {
    @NotBlank(message = "请输入姓名")
    private String name;
}
