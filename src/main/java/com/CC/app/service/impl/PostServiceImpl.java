package com.CC.app.service.impl;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.Query;
import com.CC.app.dao.PostDao;
import com.CC.app.entity.UserEntity;
import com.CC.app.vo.CommentVo;
import com.CC.app.vo.LikeVo;
import com.CC.app.vo.PostListVo;
import com.CC.app.service.CommentService;
import com.CC.app.service.LikeService;
import com.CC.app.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.CC.app.entity.PostEntity;
import com.CC.app.service.PostService;


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
        return postEntities.stream().map(post -> {
            PostListVo postListVo = new PostListVo();
            BeanUtils.copyProperties(post,postListVo);
            if(post.getForwardId() == -1)postListVo.setType(0);
            else postListVo.setType(1);
            Long userid = post.getUserid();
            UserEntity userEntity = userService.getById(userid);
            BeanUtils.copyProperties(userEntity, postListVo, "id");
            Long postId = post.getId();
            List<LikeVo> likeVoList = likeService.getLikeListByPostId(postId);
            postListVo.setLikes(likeVoList);
            List<CommentVo> commentVos = commentService.getCommentListByPostId(post.getId());
            postListVo.setComments(commentVos);
            return postListVo;
        }).collect(Collectors.toList());
    }

    @Override
    public PostListVo getDetailById(Long postId) {
        PostEntity post = baseMapper.selectById(postId);
        PostListVo postListVo = new PostListVo();
        BeanUtils.copyProperties(post, postListVo);
        if(post.getForwardId() == -1)postListVo.setType(0);
        else postListVo.setType(1);
        Long userid = post.getUserid();
        UserEntity userEntity = userService.getById(userid);
        BeanUtils.copyProperties(userEntity, postListVo, "id");
        List<LikeVo> likeVoList = likeService.getLikeListByPostId(postId);
        postListVo.setLikes(likeVoList);
        List<CommentVo> commentVos = commentService.getCommentListByPostId(post.getId());
        postListVo.setComments(commentVos);
        return postListVo;
    }

    @Override
    public void forward(Long postId, Long userId) {
        PostEntity postEntity = baseMapper.selectById(postId);
        postEntity.setId(null);
        postEntity.setUserid(userId);
        postEntity.setUpdatetime(new Date());
        postEntity.setForwardId(postId);
        baseMapper.insert(postEntity);
    }

    @Override
    public void posting(Map<String, String> params) {
        PostEntity postEntity = new PostEntity();
        postEntity.setUserid(Long.parseLong(params.get("userId")));
        postEntity.setContent(params.get("content"));
        postEntity.setForwardId(-1L);
        postEntity.setForward(Integer.parseInt(params.get("canForword")));
        postEntity.setPush(Integer.parseInt(params.get("pushPeople")));
        postEntity.setAmount(new BigDecimal(params.get("amount")));
        postEntity.setPermission(Integer.parseInt(params.get("permission")));
        postEntity.setInterest(new BigDecimal(params.get("interest")));
        postEntity.setDuetime(Integer.parseInt(params.get("duetime")));
        postEntity.setStatus(0);
        postEntity.setUpdatetime(new Date());
        postEntity.setCreateTime(new Date());
        baseMapper.insert(postEntity);
    }

}