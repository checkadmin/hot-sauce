package com.hot.sauce.goods.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuYang
 * @since 2021-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_goods")
public class GoodsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer goodsId;

    private String name;

    private BigDecimal price;

    private Integer num;

    private Long createTime;


}
