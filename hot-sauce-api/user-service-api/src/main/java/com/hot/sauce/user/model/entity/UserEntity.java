package com.hot.sauce.user.model.entity;

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
@TableName("tb_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String name;

    private String password;

    private Long createTime;

    private Boolean lockFlag;

    private BigDecimal account;


}
