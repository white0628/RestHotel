package com.white.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.domain.Comment;
import com.white.service.CommentService;
import com.white.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 14551
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2023-04-20 10:56:27
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




