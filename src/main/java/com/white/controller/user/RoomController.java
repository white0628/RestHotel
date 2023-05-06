package com.white.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.white.controller.util.CommonResult;
import com.white.controller.util.StatusCode;
import com.white.controller.util.WebUtil;
import com.white.domain.Room;
import com.white.domain.Order;
import com.white.domain.Type;
import com.white.domain.User;
import com.white.dto.BookDTO;
import com.white.dto.DateSectionDTO;
import com.white.dto.ReturnRoomDTO;
import com.white.dto.TypeDTO;
import com.white.service.RoomService;
import com.white.service.OrderService;
import com.white.service.TypeService;
import com.white.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/roomlist")
    public CommonResult<List<ReturnRoomDTO>> roomlist(@RequestBody DateSectionDTO dateSectionDTO){

        CommonResult<List<ReturnRoomDTO>> commonResult = new CommonResult<>();

        List<ReturnRoomDTO> list = roomService.listRooms(dateSectionDTO);

        commonResult.setData(list);
        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());

        return commonResult;
    }

    @PostMapping(value = "/roomDetail")
    public CommonResult<ReturnRoomDTO> roomDetail(@RequestParam("roomId")Integer roomId){
        CommonResult<ReturnRoomDTO> commonResult = new CommonResult<>();
        ReturnRoomDTO returnRoomDTO = roomService.roomDetail(roomId);

        commonResult.setData(returnRoomDTO);
        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());

        return commonResult;
    }

    @PostMapping("/bookRoom")
    public CommonResult<String> bookRoom(@RequestBody BookDTO bookDTO){
        CommonResult<String> commonResult = new CommonResult<>();

        User user = (User) WebUtil.getSession().getAttribute("loginUser");
        Room room = roomService.getById(bookDTO.getRoomId());
        Type type = typeService.getById(room.getType());
        Order order = new Order();
        BeanUtils.copyProperties(bookDTO, order);
        order.setUserId(user.getId());

        user.setState(1);
        userService.updateById(user);
        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        commonResult.setData("预订成功");
        return commonResult;
    }

    @PostMapping("/listRoomsByTypeId")
    public CommonResult<List<ReturnRoomDTO>> listRoomsByTypeId(@RequestBody TypeDTO typeDTO){
        CommonResult<List<ReturnRoomDTO>> commonResult = new CommonResult<>();
        QueryWrapper queryWrapper = new QueryWrapper();

        DateSectionDTO dateSectionDTO = new DateSectionDTO();
        BeanUtils.copyProperties(typeDTO, dateSectionDTO);
        List<ReturnRoomDTO> roomList = roomService.listRooms(dateSectionDTO);

        List<ReturnRoomDTO> returnRoomList = new ArrayList<>();
        if(0!=roomList.size()){
            for (ReturnRoomDTO room : roomList){
                if (typeDTO.getTypeId().equals(room.getType().getId())){
                    returnRoomList.add(room);
                }
            }
        }
        commonResult.setData(returnRoomList);
        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());

        return commonResult;
    }

}
