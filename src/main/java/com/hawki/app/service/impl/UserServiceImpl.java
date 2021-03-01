package com.hawki.app.service.impl;

import com.hawki.app.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.common.utils.Query;

import com.hawki.app.dao.UserDao;
import com.hawki.app.entity.UserEntity;
import com.hawki.app.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public UserVo login(Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        if(username != null && password != null){
            UserEntity user = baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("username", username));
            if(user != null){
                String pwd = user.getPassword();
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                boolean matches = passwordEncoder.matches(password, pwd);
                if(matches){
                    UserVo userVo = new UserVo();
                    userVo.setUserId(user.getId());
                    userVo.setIdentifyName(user.getRealName());
                    userVo.setIdentifyNumber(user.getIdentity());
                    return userVo;
                }
            }
        }

        return null;
    }

}