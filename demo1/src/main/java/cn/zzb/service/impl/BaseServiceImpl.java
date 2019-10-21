package cn.zzb.service.impl;


import cn.zzb.service.BaseService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础服务实现类
 *
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    /**
     * 根据Mapper类型获取Mapper
     *
     * @param m
     * @return
     */
    @SuppressWarnings("unchecked")
    public <M extends BaseMapper> M getMapper(Class<M> m) {
        return (M) mapper;
    }

    @SuppressWarnings("unchecked")
    public <M extends BaseMapper> M getMapper() {
        return (M) mapper;
    }

    @Autowired
    protected BaseMapper<T> mapper;
}
