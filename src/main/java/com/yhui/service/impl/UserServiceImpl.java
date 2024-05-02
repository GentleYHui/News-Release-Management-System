package com.yhui.service.impl;

import com.yhui.mapper.UserMapper;
import com.yhui.pojo.User;
import com.yhui.service.UserService;
import com.yhui.utils.Md5Util;
import com.yhui.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //根据用户名找人
    @Override
    public User findByUsername(String username) {
        User u =userMapper.findByUsername(username);
        return u;
    }

    //注册用户
    @Override
    public void register(String username, String password) {

        //先对密码进行加密
        String md5String = Md5Util.getMD5String(password);

        //将用户加入数据库
        userMapper.add(username, md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd), id);
    }
}
