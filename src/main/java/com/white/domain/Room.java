package com.white.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName room
 */
@TableName(value ="room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room implements Serializable {
    private Integer id;

    private String number;

    private Integer type;

    private Integer state;

    private Integer maxPeople;

    private String introduce;

    private static final long serialVersionUID = 1L;
}