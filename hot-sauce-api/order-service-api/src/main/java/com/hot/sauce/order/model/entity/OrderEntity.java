package com.hot.sauce.order.model.entity;

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
 * @author liuYang
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_order")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    private Long goodsId;

    private Long createTime;

    private Long userId;

    private Long orderNo;


}
