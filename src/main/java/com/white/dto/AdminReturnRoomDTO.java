package com.white.dto;

import com.white.domain.Room;
import com.white.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminReturnRoomDTO {
    private Room room;
    private Type type;
}
