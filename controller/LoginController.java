package ajaxtest.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class LoginController {
    @RequestMapping("login")
    public String login(String username,String userpwd){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(username.equals("wanghong123") && userpwd.equals("lalala123")){
            return "true";
        }
        return "false";
    }
}
