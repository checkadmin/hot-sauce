package com.hot.sauce.mall.gataway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ： coder.Yang
 * @date ： 2021/8/26 16:56
 * @description ：白名单配置文件
 */
@Data
@Component
@ConfigurationProperties(prefix = "white")
public class WhiteUrlList {

    private List<String> urlList;
}
