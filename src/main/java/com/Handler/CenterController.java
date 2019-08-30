package com.Handler;

import com.db.UserDao;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
public class CenterController {


    @ResponseBody
    @RequestMapping("/userRegister")
    public String userRegister(){

        return null;
    }

    @RequestMapping("/handler/sign_up")
    public String signUp(){

        return null;
    }

}
