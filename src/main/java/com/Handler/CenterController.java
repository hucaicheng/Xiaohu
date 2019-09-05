package com.Handler;

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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

@Controller
public class CenterController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AgentService agentService;


    /**
     * 用户注册
     * @param user
     * @param code
     * @param session
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public DataResult userRegister(User user,String code,HttpSession session,
                               HttpServletRequest request,HttpServletResponse response) throws IOException {
        DataResult dataResult =new DataResult();
        //str.toLOwerCase() 把字符串全部转为小写
        if(code.toLowerCase().equals(session.getAttribute("code").toString().toLowerCase())) {
            user.setCreate_time(new Date());
            user.setU_hash(user.getU_name().hashCode()+"");
            //System.out.println(user);
            dataResult = userService.UserRigister(user);
                response.sendRedirect("success.html?h="+user.getU_hash());
        }else{
            dataResult.setStatus(401);
            dataResult.setMsg("验证码错误");
        }
        return dataResult;
    }

    /**
     * 返回 hash 值
     * @param hash
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userInfo",method = RequestMethod.POST)
    public DataResult useriNFO(String hash){
        DataResult dataResult =new DataResult();
        dataResult=userService.GetUserByUhash(hash);
        return  dataResult;
    }

    /**
     * 设置图片验证码
     *
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping("/getImg")
    public void GetImg(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Image img=new Image();
        request.getSession().setAttribute("code",img.getVcode());
        OutputStream out = response.getOutputStream();
        ImageIO.write(img.getImage(),"png",out);
        request.getSession().setAttribute("code",img.getVcode());

    }



    @RequestMapping("/handler/sign_up")
    public String signUp(){



        return null;
    }

    /**
     * 登录验证
     * @param name
     * @param pass
     * @param phone
     * @param code
     * @param type
     * @return 数据
     */
    @ResponseBody
    @RequestMapping(value = "/Login")
    public String AdminLogin(@RequestParam(required = false) String name,String pass,@RequestParam(required = false) String phone,String code,Map<String,Object>map,@RequestParam(required = false) String type,HttpServletResponse response){
        DataResult dataResult=new DataResult();
        Cookie cookie = new Cookie(type.hashCode()+"",  name==null?phone.hashCode()+"" : name.hashCode()+"");//创建新的Cookie对象
        cookie.setPath("/");//设置作用域：本域名下ContextPath都可以访问该cookie。
        response.addCookie(cookie);//将cookie 添加到response 的Cookie 数组中返回给客户端
        if("admin".equals(type)){
            dataResult=adminService.AdminLogin(name,pass);
        }else if("agent".equals(type)){
            dataResult=agentService.Login(phone,pass);
        }
        map.put("cookie",cookie);
        if(dataResult.getStatus()==200)
        return "redirect:info.html?type="+type;
         return "redirect:login.html";
    }




}
