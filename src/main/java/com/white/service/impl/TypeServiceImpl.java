package com.white.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.domain.Type;
import com.white.service.TypeService;
import com.white.mapper.TypeMapper;
import org.springframework.stereotype.Service;

/**
* @author 14551
* @description 针对表【type】的数据库操作Service实现
* @createDate 2023-04-20 10:56:27
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




