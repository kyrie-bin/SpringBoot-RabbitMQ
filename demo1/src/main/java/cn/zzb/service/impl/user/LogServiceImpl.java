package cn.zzb.service.impl.user;

import cn.zzb.domain.Log;
import cn.zzb.mapper.LogMapper;
import cn.zzb.service.impl.BaseServiceImpl;
import cn.zzb.service.user.ILogService;
import org.springframework.stereotype.Service;

/**
 * @Author: power
 * @Date: 2019/6/24 10:33
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<LogMapper, Log> implements ILogService {


    public LogMapper mapper() {
        return this.getMapper(LogMapper.class);
    }

    @Override
    public boolean insert(Log log) {
        int i = mapper().insert(log);
        if (i == 0) {
            return false;
        }
        return true;
    }
}
