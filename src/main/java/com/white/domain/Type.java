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
 * @TableName type
 */
@TableName(value ="type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Serializable {
    private Integer id;

    private String typeName;

    private Double price;

    private String introduce;

    private String feature;

    private String coverImage;

    private String detailUrl;

    private static final long serialVersionUID = 1L;
}