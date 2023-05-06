package com.white.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.domain.Room;
import com.white.domain.Order;
import com.white.domain.Type;
import com.white.dto.AdminReturnRoomDTO;
import com.white.dto.DateSectionDTO;
import com.white.dto.ReturnRoomDTO;
import com.white.mapper.TOrderMapper;
import com.white.mapper.TypeMapper;
import com.white.service.RoomService;
import com.white.mapper.RoomMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
* @author 14551
* @description 针对表【room】的数据库操作Service实现
* @createDate 2023-04-20 10:56:27
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService{
    
    @Resource
    private TOrderMapper tOrderMapper;

    @Resource
    private TypeMapper typeMapper;
    
    @Override
    public Boolean bookRoom(Integer roomId) {
        return this.getBaseMapper().bookRoom(roomId);
    }

    @Override
    public Boolean finishRoom(Integer roomId) {
        return this.getBaseMapper().finishRoom(roomId);
    }

    @Override
    public List<ReturnRoomDTO> listRooms(DateSectionDTO dateSectionDTO) {
        QueryWrapper roomQueryWrapper = new QueryWrapper();

        List<Room> roomList = this.baseMapper.selectList(roomQueryWrapper);
        Map<Integer, Room> roomMap = roomList.stream().collect(Collectors.toMap(Room::getId,a->a,(k1, k2)->k1));

        /*
        * lt:less than 少于
        * gt:greater than 大于
        * */
        List<Order> orders = tOrderMapper.selectList(new QueryWrapper<Order>()
                .eq("flag", 1)
                .lt("in_time", dateSectionDTO.getInTime())
                .lt("in_time", dateSectionDTO.getLeaveTime())
                .gt("leave_time", dateSectionDTO.getInTime())
                .lt("leave_time", dateSectionDTO.getLeaveTime())
                .or()
                .eq("flag",1)
                .gt("in_time", dateSectionDTO.getInTime())
                .lt("in_time", dateSectionDTO.getLeaveTime())
                .gt("leave_time", dateSectionDTO.getInTime())
                .gt("leave_time", dateSectionDTO.getLeaveTime())
                .or()
                .eq("flag",1)
                .gt("in_time", dateSectionDTO.getInTime())
                .lt("in_time", dateSectionDTO.getLeaveTime())
                .gt("leave_time", dateSectionDTO.getInTime())
                .lt("leave_time", dateSectionDTO.getLeaveTime())
                .or()
                .eq("flag",1)
                .lt("in_time", dateSectionDTO.getInTime())
                .lt("in_time", dateSectionDTO.getLeaveTime())
                .gt("leave_time", dateSectionDTO.getInTime())
                .gt("leave_time", dateSectionDTO.getLeaveTime()));

        for(Order order : orders){
            roomMap.remove(order.getRoomId());
        }
        List<Room> rooms = new ArrayList<>(roomMap.values());

        List<ReturnRoomDTO> returnRoomDTOList = new ArrayList<>();

        if (rooms.size()!=0){
            for (Room room : rooms){
                ReturnRoomDTO returnRoomDTO = split(room);
                BeanUtils.copyProperties(room,returnRoomDTO);
                Type type = typeMapper.selectById(room.getId());
                returnRoomDTO.setType(type);
                returnRoomDTOList.add(returnRoomDTO);
            }
        }
        return returnRoomDTOList;
    }

    @Override
    public ReturnRoomDTO roomDetail(Integer roomId) {
        Room room = this.getBaseMapper().selectById(roomId);

        ReturnRoomDTO returnRoomDTO = split(room);
        Type type = typeMapper.selectById(room.getType());
        returnRoomDTO.setType(type);
        return returnRoomDTO;
    }

    @Override
    public AdminReturnRoomDTO adminRoomDetail(Integer roomId) {
        Room room = this.getBaseMapper().selectById(roomId);

        AdminReturnRoomDTO roomDTO = new AdminReturnRoomDTO();
        roomDTO.setRoom(room);

        Type type = typeMapper.selectById(room.getType());
        roomDTO.setType(type);
        return roomDTO;
    }

    private ReturnRoomDTO split(Room room) {

        ReturnRoomDTO returnRoomDTO = new ReturnRoomDTO();
        BeanUtils.copyProperties(room,returnRoomDTO);

        Map<String,String> introduces = new HashMap<>();
        String[] items = room.getIntroduce().split(",");
        for(String str : items){
            String[] strs = str.split(":");
            introduces.put(strs[0],strs[1]);
        }
        returnRoomDTO.setIntroduces(introduces);

        return returnRoomDTO;

    }
}




