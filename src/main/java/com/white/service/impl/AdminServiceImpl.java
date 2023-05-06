package com.white.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.domain.Admin;
import com.white.service.AdminService;
import com.white.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author 14551
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2023-04-20 10:56:27
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




