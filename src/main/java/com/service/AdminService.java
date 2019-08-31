package com.service;

import com.db.AdminDao;
import com.db.AgentDao;
import com.db.UserDao;
import com.model.Admin;
import com.model.Agent;
import com.model.DataResult;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:智霸霸
 * @Date 2019/8/29
 */
@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private AgentDao agentDao;
    @Autowired
    private UserDao userDao;

    //管理员登录验证
    public DataResult AdminLogin(String a_name, String a_password){
        DataResult dataResult=new DataResult();
        Admin admin=adminDao.FindAdmin(a_name,a_password);
        if(admin!=null){
            dataResult.setMsg("登录成功");
            dataResult.setStatus(200);
            dataResult.setData(admin);
        }else {
            dataResult.setStatus(500);
            dataResult.setMsg("登录失败");
        }
        return dataResult;
    }
    //管理员删除邀请人
    public DataResult DeleteAgent(int a_id){
        DataResult dataResult=new DataResult();
        if(agentDao.DeleteAgent(a_id)){
            dataResult.setMsg("删除成功");
            dataResult.setStatus(200);
        }else {
            dataResult.setStatus(500);
            dataResult.setMsg("删除失败");
        }
        return dataResult;
    }
    //管理员查看所有邀请人
    public DataResult GetAllAgent(){
        DataResult dataResult=new DataResult();
        List<Agent> list=agentDao.GetAll();
        if(list!=null){
            dataResult.setMsg("查找成功");
            dataResult.setStatus(200);
            dataResult.setData(list);
        }else {
            dataResult.setStatus(500);
            dataResult.setMsg("查找失败");
        }
        return dataResult;
    }
    //管理员查看所有注册人信息
    public DataResult GetAllUser(){
        DataResult dataResult=new DataResult();
        List<User> list=userDao.GetAllUser();
        if(list!=null){
            dataResult.setMsg("success");
            dataResult.setStatus(200);
            dataResult.setData(list);
        }else {
            dataResult.setMsg("查找失败");
            dataResult.setStatus(500);
        }
        return dataResult;
    }
}
