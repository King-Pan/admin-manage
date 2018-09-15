package club.javalearn.admin.controller;

import club.javalearn.admin.common.ServerResponse;
import club.javalearn.admin.exception.UnauthorizedException;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author king-pan
 * Date: 2018/9/14
 * Time: 下午5:15
 * Description: No Description
 */
@RestControllerAdvice
public class ExceptionController {

    private final ServerResponse serverResponse;

    @Autowired
    public ExceptionController(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    /**
     * 捕捉shiro的异常
     *
     * @param e
     * @return
     */
    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public ServerResponse handle401(ShiroException e) {
        return ServerResponse.createByErrorCodeMessage(401, "您无权访问");
    }

    // 捕捉shiro的异常
    @ExceptionHandler(ServletException.class)
    public ServerResponse handle401(HttpServletRequest request, ServletException e) {
        return ServerResponse.createByErrorCodeMessage(getStatus(request).value(), e.getMessage());
    }


    @ExceptionHandler(AuthenticationException.class)
    public ServerResponse handle401(AuthenticationException e) {
        return ServerResponse.createByErrorCodeMessage(401, e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ServerResponse handle401(UnauthorizedException e) {
        return ServerResponse.createByErrorCodeMessage(401, e.getMessage());
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public ServerResponse globalException(HttpServletRequest request, Throwable ex) {
        return ServerResponse.createByErrorCodeMessage(getStatus(request).value(), ex.getMessage());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
