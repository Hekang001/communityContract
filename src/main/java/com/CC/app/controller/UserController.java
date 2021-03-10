package com.CC.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.CC.app.common.exception.BizCodeEnum;
import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.R;
import com.CC.app.entity.UserEntity;
import com.CC.app.vo.UserInfoVo;
import com.CC.app.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.CC.app.service.UserService;


/**
 * 用户表
 */
@RestController
@RequestMapping("app/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public R login(@RequestParam Map<String, String> params){
        UserVo user = userService.login(params);

        if(user != null){
            return R.ok("登录成功").setData(user);
        }else {
            return R.error(BizCodeEnum.LOGINACCT_PASSWORD_INVAILD_EXCEPTION.getCode(), BizCodeEnum.LOGINACCT_PASSWORD_INVAILD_EXCEPTION.getMsg());
        }
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("app:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 用户基本信息
     */
    @RequestMapping("/careerInfo")
    public R careerInfo(){
//        JobInfoVo jobInfoVo = userService.getCareerInfo();
        return R.ok();
    }

    /**
     * 信息
     */
    @RequestMapping("/explore/userInfo")
    //@RequiresPermissions("app:user:info")
    public R info(Long id, Long myId){
		UserInfoVo user = userService.getUserInfoById(id, myId);

        return R.ok().put("data", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("app:user:save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("app:user:update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("app:user:delete")
    public R delete(@RequestBody Long[] ids){
		userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
