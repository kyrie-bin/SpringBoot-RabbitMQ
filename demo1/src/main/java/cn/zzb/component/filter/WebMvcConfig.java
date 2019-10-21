package cn.zzb.component.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author Kyrie
 * @Date 2019/7/4
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 注册拦截器 拦截规则
     * 多个拦截器时,以此添加,执行顺序按添加顺序
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getHandlerInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public HandlerInterceptor getHandlerInterceptor() {
        return new CustomHandlerInterceptor();
    }


}
