package com.CC.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.CC.app.common.exception.BizCodeEnum;
import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.R;
import com.CC.app.entity.AddFriendEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.CC.app.service.AddFriendService;


/**
 * 加好友
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-03-02 21:30:17
 */
@RestController
@RequestMapping("app/addfriend")
public class AddFriendController {
    @Autowired
    private AddFriendService addFriendService;

    @PostMapping("/explore/addFriend")
    public R addFriend(Long userId, Long friendId){
        Boolean succeed = addFriendService.addFriend(userId, friendId);
        if(succeed) return R.ok("成功发送好友请求");
        else return R.error(BizCodeEnum.ADDFRIEND_FAILED_EXCEPTION.getCode(), BizCodeEnum.ADDFRIEND_FAILED_EXCEPTION.getMsg());
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("app:addfriend:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = addFriendService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("app:addfriend:info")
    public R info(@PathVariable("id") Long id){
		AddFriendEntity addFriend = addFriendService.getById(id);
        return R.ok().put("addFriend", addFriend);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("app:addfriend:save")
    public R save(@RequestBody AddFriendEntity addFriend){
		addFriendService.save(addFriend);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("app:addfriend:update")
    public R update(@RequestBody AddFriendEntity addFriend){
		addFriendService.updateById(addFriend);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("app:addfriend:delete")
    public R delete(@RequestBody Long[] ids){
		addFriendService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
