package com.spring.dao;

import com.spring.model.User;
import com.spring.utils.RolesRowMapper;
import com.spring.utils.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDao {
    private static final String SELECT_USER = "SELECT * FROM users WHERE username=?";
    private static final String SELECT_ROLES = "SELECT * FROM authorities WHERE username=?";
    @Autowired
    private  JdbcTemplate template;
    public  User findUserByUserName(String name){
        User user=template.queryForObject(SELECT_USER,new Object[]{name},new UserRowMapper());
        if (user==null){
            throw new RuntimeException("user not found");
        }
        List<String> list=template.query(SELECT_ROLES,new Object[]{name},new RolesRowMapper());
        String[] roles= list.toArray(new String[list.size()]);
        user.setRoles(roles);
        return user;
    }
}
