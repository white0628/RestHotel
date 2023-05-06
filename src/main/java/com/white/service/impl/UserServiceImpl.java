package com.white.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.domain.User;
import com.white.service.UserService;
import com.white.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 14551
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-04-20 10:56:27
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




