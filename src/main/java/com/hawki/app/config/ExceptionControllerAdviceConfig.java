package com.hawki.app.config;

import com.hawki.app.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.hawki.app.common.exception.BizCodeEnum;
import java.util.HashMap;
import java.util.Map;

/**
 * 集中处理所有异常
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.hawki.app.controller")
public class ExceptionControllerAdviceConfig {

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e){
        log.error("数据校验出现问题{}，异常类型：{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();

        Map<String,String> errorMap = new HashMap<>();
        //发生错误的字段和错误消息放入errorMap进行返回
        bindingResult.getFieldErrors().forEach((fieldError -> {
            errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        }));
        return R.error(BizCodeEnum.VAILD_EXCEPTION.getCode(),BizCodeEnum.VAILD_EXCEPTION.getMsg()).put("data",errorMap);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){

        log.error("错误：",throwable);
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(),BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }

}
