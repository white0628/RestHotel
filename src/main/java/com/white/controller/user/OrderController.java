package com.white.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.white.controller.util.CommonResult;
import com.white.controller.util.StatusCode;
import com.white.controller.util.WebUtil;
import com.white.domain.Order;
import com.white.domain.User;
import com.white.dto.ReturnOrderDTO;
import com.white.dto.ReturnRoomDTO;
import com.white.dto.ReturnUserDTO;
import com.white.service.RoomService;
import com.white.service.OrderService;
import com.white.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;

    @GetMapping("/historyOrder")
    public CommonResult<List<ReturnOrderDTO>> historyOrder(){
        CommonResult<List<ReturnOrderDTO>> commonResult = new CommonResult<>();
        QueryWrapper queryWrapper = new QueryWrapper();

        User user = (User) WebUtil.getSession().getAttribute("loginUser");

        queryWrapper.eq("user_id",user.getId());
        List<Order> list = orderService.list(queryWrapper);

        List<ReturnOrderDTO> orderDTOList = new ArrayList<>();
        if(0!=list.size()){
            for (Order order : list){
                ReturnOrderDTO orderDTO = new ReturnOrderDTO();
                ReturnRoomDTO roomDTO = roomService.roomDetail(order.getRoomId());

                orderDTO.setOrder(order);
                orderDTO.setRoom(roomDTO);

                orderDTOList.add(orderDTO);
            }
        }
        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        commonResult.setData(orderDTOList);
        return commonResult;
    }

    @PostMapping("/detailOrder")
    public CommonResult<ReturnOrderDTO> detailOrder(@RequestParam("orderId") Integer orderId){
        CommonResult<ReturnOrderDTO> commonResult = new CommonResult<>();
        ReturnOrderDTO returnOrder = new ReturnOrderDTO();
        ReturnUserDTO userDTO = new ReturnUserDTO();

        Order order = orderService.getById(orderId);
        User user = userService.getById(order.getUserId());
        BeanUtils.copyProperties(user,userDTO);
        ReturnRoomDTO returnRoomDTO = roomService.roomDetail(order.getRoomId());

        returnOrder.setOrder(order);
        returnOrder.setUser(userDTO);
        returnOrder.setRoom(returnRoomDTO);

        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        commonResult.setData(returnOrder);

        return commonResult;
    }
}
