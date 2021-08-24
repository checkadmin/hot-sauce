package com.hot.sauce.goods.model.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author coder.Yang
 * @since 2021-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_goods")
public class GoodsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    private String name;

    private BigDecimal price;

    private Integer num;

    private Long createTime;


}
