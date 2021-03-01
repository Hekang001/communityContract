package com.hawki.app.service.impl;

import com.hawki.app.entity.UserEntity;
import com.hawki.app.service.CommentService;
import com.hawki.app.service.LikeService;
import com.hawki.app.service.UserService;
import com.hawki.app.vo.CommentVo;
import com.hawki.app.vo.LikeVo;
import com.hawki.app.vo.PostListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.common.utils.Query;

import com.hawki.app.dao.PostDao;
import com.hawki.app.entity.PostEntity;
import com.hawki.app.service.PostService;


@Service("postService")
public class PostServiceImpl extends ServiceImpl<PostDao, PostEntity> implements PostService {

    @Autowired
    LikeService likeService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PostEntity> page = this.page(
                new Query<PostEntity>().getPage(params),
                new QueryWrapper<PostEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<PostListVo> getPosts() {
        List<PostEntity> postEntities = baseMapper.selectList(new QueryWrapper<PostEntity>());
        List<PostListVo> collect = postEntities.stream().map(post -> {
            PostListVo postListVo = new PostListVo();
            BeanUtils.copyProperties(postListVo, post);
            Long userid = post.getUserid();
            UserEntity userEntity = userService.getById(userid);
            BeanUtils.copyProperties(postListVo, userEntity);
            List<LikeVo> likeVoList = likeService.getLikeListByPostId(post.getId());
            postListVo.setLikes(likeVoList);
            List<CommentVo> commentVos = commentService.getCommentListByPostId(post.getId());
            postListVo.setComments(commentVos);
            return postListVo;
        }).collect(Collectors.toList());
        return collect;
    }

}