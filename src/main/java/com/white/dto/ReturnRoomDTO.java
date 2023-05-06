package com.white.dto;

import com.white.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnRoomDTO {
    private Integer id;
    private String number;
    private Integer maxPeople;
    private Map<String,String> introduces;
    private Type type;
}
