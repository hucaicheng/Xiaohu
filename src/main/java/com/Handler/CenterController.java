package com.Handler;

import com.db.UserDao;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class CenterController {
    @Autowired
    private UserDao userDao;

    @ResponseBody
    @RequestMapping("/test")
    public String Test(){
        return "zzz";
    }
}
