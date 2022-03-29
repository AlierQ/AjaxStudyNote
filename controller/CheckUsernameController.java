package ajaxtest.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@RestController
public class CheckUsernameController {
    @RequestMapping("checkUsername")
    public String checkUsername(String username, HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("请求方式" + request.getMethod()); // 查看请求方式

        // 除了传递参数还可以通过HttpServletRequest的getParament获得参数
        String username2=request.getParameter("username");
//        username2=new String(username2.getBytes("iso-8859-1"),"utf-8");//进行编码再进行解码

        System.out.println("用户名是:" + username2);
        if("tom".equals(username2)||"jack".equals(username2))
        {
            return "flase";
        }
        return "true";
    }
}
