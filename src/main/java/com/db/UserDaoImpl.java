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
        String sql="INSERT INTO user_table(u_name,u_sex,u_id,a_id,create_time,u_url) VALUES(?,?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql, user.getU_name(), user.getU_sex(), user.getU_id(), user.getA_id(), user.getCreate_time(), user.getU_url());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<User> FindUsersByUid(String u_id) {
        String sql="SELECT * FROM user_table WHERE uid=?";
        RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
        List<User> list=jdbcTemplate.query(sql,rowMapper,u_id);
        return list;
    }

    @Override
    public List<User> FindUsersByAid(String a_id) {
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
        try {
            jdbcTemplate.update(sql, id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean UpdateUser(User user) {
        String sql="UPDATE user_table SET u_id = ？WHERE id= ?";
        try {
            jdbcTemplate.update(sql, user.getU_id(), user.getId());
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
