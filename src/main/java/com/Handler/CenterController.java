package com.Handler;

import com.model.DataResult;
import com.model.User;
import com.service.UserService;
import com.util.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

@SessionAttributes(value = "dataResult")
@Controller
public class CenterController {

    @Autowired
    private UserService userService;

    private Image img;

    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public String userRegister(User user,String test,Map<String,Object> map){
        DataResult dataResult =new DataResult();
        if(test.equals(img.getVcode())) {
            user.setCreate_time(new Date());
            System.out.println(user);
            dataResult = userService.UserRigister(user);
        }else{
            dataResult.setStatus(401);
            dataResult.setMsg("验证码错误");
        }
        map.put("dataResult", dataResult);
        return "redirect:success.html";
    }

    @RequestMapping("/getImg")
    public void GetImg(HttpServletResponse response,Map<String,Object> map) throws IOException {
        img=new Image();
        OutputStream out = response.getOutputStream();
        ImageIO.write(img.getImage(),"jpeg",out);
    }

    @ResponseBody
    @RequestMapping(value = "/user")
    public DataResult userRegister(Map<String ,Object> map){
        return (DataResult) map.get("dataResult");
    }


    @RequestMapping("/handler/sign_up")
    public String signUp(){

        return null;
    }

}
