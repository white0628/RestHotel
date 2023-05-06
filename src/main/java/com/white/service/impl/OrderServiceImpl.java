package com.white.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.domain.Order;
import com.white.service.OrderService;
import com.white.mapper.TOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author 14551
* @description 针对表【t_order】的数据库操作Service实现
* @createDate 2023-04-20 10:56:27
*/
@Service
public class OrderServiceImpl extends ServiceImpl<TOrderMapper, Order>
    implements OrderService {

}




