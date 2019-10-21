package cn.zzb.component.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Kyrie
 * @Date 2019/7/4
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    /**
     * 拦截Exception类的异常
     *
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Map<String, Object> exceptionHandler(Exception e) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("respCode", "9999");
        result.put("respMsg", e.getMessage());
        //正常开发中,可创建一个统一响应实体,如:CommonResp
        return result;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("参数检验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        HashMap<String, Object> result = new HashMap<>();
        result.put("respCode", "01002");
        result.put("respMsg", fieldError.getDefaultMessage());
        return result;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    public Map<String, Object> bindException(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("参数检验异常:{}({}) ", fieldError.getDefaultMessage(), fieldError.getField());
        HashMap<String, Object> result = new HashMap<>();
        result.put("respCode", "01002");
        result.put("respMsg", fieldError.getDefaultMessage());
        return result;
    }


}
