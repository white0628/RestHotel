package com.white.mapper;


import com.white.domain.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
* @author 14551
* @description 针对表【room】的数据库操作Mapper
* @createDate 2023-04-20 10:56:27
* @Entity com.white.domain.Room
*/
public interface RoomMapper extends BaseMapper<Room> {

    @Update("update room set state = 1 where id = #{roomid}")
    Boolean bookRoom(@Param("roomId") Integer roomId);

    @Update("update room set state = 0 where id = #{roomId}")
    Boolean finishRoom(@Param("roomId") Integer roomId);



}




