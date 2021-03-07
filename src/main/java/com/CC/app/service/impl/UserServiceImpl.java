package com.CC.app.service.impl;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.Query;
import com.CC.app.dao.UserDao;
import com.CC.app.entity.UserEntity;
import com.CC.app.vo.ContractInfoVo;
import com.CC.app.vo.UserInfoVo;
import com.CC.app.vo.UserVo;
import com.CC.app.service.ContractService;
import com.CC.app.service.FriendService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.CC.app.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    FriendService friendService;

    @Autowired
    ContractService contractService;
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

    @Override
    public UserInfoVo getUserInfoById(Long id, Long myId) {

        UserEntity userEntity = baseMapper.selectById(id);
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userEntity, userInfoVo);
        userInfoVo.setGender(userEntity.getSex()==1?"男":"女");
        int riskLevel = 2;
        if(userEntity.getStars() > 3) riskLevel = 0;
        else if(userEntity.getStars() > 1) riskLevel = 1;
        userInfoVo.setRiskLevel(riskLevel);
        Boolean isFriend = friendService.judgeFriendship(id, myId);
        userInfoVo.setIsFriend(isFriend);
        if(!isFriend){
            userInfoVo.setNickName("");
            userInfoVo.setIdentity("");
        }
        ContractInfoVo contractInfoVo = contractService.getByDebtId(id);
        BeanUtils.copyProperties(contractInfoVo, userInfoVo);
        return userInfoVo;
    }

}