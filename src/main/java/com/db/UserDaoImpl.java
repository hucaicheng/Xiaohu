package com.db;

import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author:智霸霸
 * @Date 2019/8/27
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public boolean InsertUser(User user) {
        String sql="INSERT INTO user_table(u_name,u_sex,u_id,a_id,create_time,u_url,phone) VALUES(?,?,?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql, user.getU_name(), user.getU_sex(), user.getU_id(), user.getA_id(), user.getCreate_time(), user.getU_url(),user.getPhone());
           return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> FindUsersByUid(int u_id) {
        String sql="SELECT * FROM user_table WHERE uid=?";
        RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
        List<User> list=jdbcTemplate.query(sql,rowMapper,u_id);
        return list;
    }

    @Override
    public List<User> FindUsersByAid(int a_id) {
        String sql="SELECT * FROM user_table WHERE a_id=?";
        RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
        List<User> list=jdbcTemplate.query(sql,rowMapper,a_id);
        return list;
    }

    @Override
    public List<User> GetAllUser() {
        String sql="SELECT * FROM uesr_table";
        RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
        List<User> list=jdbcTemplate.query(sql,rowMapper);
        return list;
    }

    @Override
    public boolean DeleteUserById(Integer id) {
        String sql="DELETE FROM user_table WHERE id = ?";
           int i= jdbcTemplate.update(sql, id);
           if(i==1)
            return true;
           return  false;
    }

    @Override
    public boolean UpdateUser(User user) {
        String sql="UPDATE user_table SET u_id = ？WHERE id= ?";

           int i= jdbcTemplate.update(sql, user.getU_id(), user.getId());
           if(i==1)
                return true;
           return false;
    }

    public User FindUserByPhone(String  phone){
        String sql="SELECT * FROM user_table WHERE phone= ?";
        try{
            RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
            User user=jdbcTemplate.queryForObject(sql,rowMapper,phone);
            return user;
        }catch (Exception e){
            return null;
        }
    }
    public int FindUser(User user){

        return 0;
    }
}
