package cn.zzb.service.user;

import cn.zzb.domain.Log;
import cn.zzb.service.BaseService;

/**
 * @Author Kyrie
 * @Date 2019/7/4
 */
public interface ILogService extends BaseService<Log> {
    boolean insert(Log log);
}
