package com.example.sale.config;

import com.example.sale.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.auth.login.LoginException;

/**
 * @ClassName: GlobalExceptionAdvice
 * @Description: 全局异常处理
 * @Author: PANLVZ
 * @Date: 2020-03-06
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionAdvice extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @ExceptionHandler(value = Throwable.class)
    public Result<?> globalException(Throwable e) throws Exception{
        Result<String> result = new Result<>();
        result.setStatus(false);
        if (e instanceof LoginException) {
            result.setStatusCode("401");
        } else {
            logger.error(e.getMessage());
            result.setStatusCode("500");
        }
        result.setStatusMessage(e.getMessage());
        return result;
    }
}
