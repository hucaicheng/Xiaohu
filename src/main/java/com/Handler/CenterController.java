package com.Handler;

import com.model.Admin;
import com.model.DataResult;
import com.model.User;
import com.service.AdminService;
import com.service.AgentService;
import com.service.UserService;
import com.util.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

@SessionAttributes(value = "dataResult")
@Controller
public class CenterController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AgentService agentService;

    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public String userRegister(User user,String code,Map<String,Object> map,HttpSession session){
        DataResult dataResult =new DataResult();
        //str.toLOwerCase() 把字符串全部转为小写
        if(code.toLowerCase().equals(session.getAttribute("code").toString().toLowerCase())) {
            user.setCreate_time(new Date());
            //System.out.println(user);
            dataResult = userService.UserRigister(user);
        }else{
            dataResult.setStatus(401);
            dataResult.setMsg("验证码错误");
        }
        map.put("dataResult", dataResult);
        return "redirect:success.html";
    }

    @RequestMapping("/getImg")
    public void GetImg(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Image img=new Image();
        request.getSession().setAttribute("code",img.getVcode());
        OutputStream out = response.getOutputStream();
        ImageIO.write(img.getImage(),"png",out);
        request.getSession().setAttribute("code",img.getVcode());

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

    @ResponseBody
    @RequestMapping("/AdminLogin")
    public String AdminLogin(@RequestParam(required = false) String name,String pass,@RequestParam(required = false) String phone,String code,Map<String,Object> map,String type){
        DataResult dataResult=new DataResult();
        if("admin".equals(type)){
            dataResult=adminService.AdminLogin(name,pass);
            map.put("dataResult",dataResult);
        }else if("agent".equals(type)){
            dataResult=agentService.Login(phone,pass);
            map.put("detaResult",dataResult);
        }
        return "redirect:success.html";
    }


}
