package com.white.service;

import com.white.domain.Room;
import com.baomidou.mybatisplus.extension.service.IService;
import com.white.dto.AdminReturnRoomDTO;
import com.white.dto.DateSectionDTO;
import com.white.dto.ReturnRoomDTO;

import java.util.List;

/**
* @author 14551
* @description 针对表【room】的数据库操作Service
* @createDate 2023-04-20 10:56:27
*/
public interface RoomService extends IService<Room> {

    Boolean bookRoom(Integer roomId);

    Boolean finishRoom(Integer roomId);

    List<ReturnRoomDTO> listRooms(DateSectionDTO dateSectionDTO);

    ReturnRoomDTO roomDetail(Integer roomId);

    AdminReturnRoomDTO adminRoomDetail(Integer roomId);



}
