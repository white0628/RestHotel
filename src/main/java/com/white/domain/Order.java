package com.white.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName t_order
 */
@TableName(value ="t_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer roomId;

    private Date inTime;

    private Date leaveTime;

    private Date createTime;

    private Double realPrice;

    private Integer realPeople;

    private Integer fapiao;

    private Integer flag;

    private static final long serialVersionUID = 1L;
}