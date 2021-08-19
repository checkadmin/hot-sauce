package com.hot.sauce.common.generator;

import com.hot.sauce.common.generator.util.GeneratorConfig;

/**
 * @author ： liuYang
 * @date ： 2021/5/6 11:21
 * @description ： 代码生成
 */
public class CodeGenerator {

    public static void main(String[] args) {
        String str[] = new String[]{"tb_goods"};
        GeneratorConfig.getAutoGenerator("goods-service","liuYang",
                "goods",
                "tb_", str).execute();
    }
}
