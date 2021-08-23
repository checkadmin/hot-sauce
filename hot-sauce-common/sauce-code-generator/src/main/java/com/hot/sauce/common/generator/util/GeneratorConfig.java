package com.hot.sauce.common.generator.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author ： liuYang
 * @date ： 2021/8/19 10:53
 * @description ： 代码生成配置类
 */
public class GeneratorConfig {
    /**
     * 数据库-连接
     */
    private final static String JDBC_URL = "localhost:3306/";
    /**
     * 数据库-用户名
     */
    private final static String JDBC_UESR = "root";
    /**
     * 数据库-密码
     */
    private final static String JDBC_PWD = "root";

    /**
     * 包路径
     */
    private final static String modulePackage = "com.hot.sauce";


    private static GlobalConfig getGlobalConfig(String serviceName,String author){
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "\\hot-sauce-service\\"+serviceName+"\\src\\main\\java");
        gc.setAuthor(author);
        gc.setOpen(false);
        //第二次生成会把第一次生成的覆盖掉
        gc.setFileOverride(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        gc.setEntityName("%sEntity");
        return gc;
    }

    private static DataSourceConfig getDataSourceConfig(String sourceName){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://"+JDBC_URL+sourceName+"?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(JDBC_UESR);
        dsc.setPassword(JDBC_PWD);
        return dsc;
    }

    private static PackageConfig getPackageConfig(String moduleName){
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(modulePackage);
        return pc;
    }

    private static StrategyConfig getStrategyConfig(String tablePrefix, String... include){
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //去除表前缀
        strategy.setTablePrefix(tablePrefix);
        //使用lombok
        strategy.setEntityLombokModel(true);
        // 逆向工程使用的表   如果要生成多个,这里可以传入String[]
        strategy.setInclude(include);
      return strategy;
    }

    /**
     * 代码生成类
     * @param sourceName 数据库名称
     * @param serviceName 服务名称
     * @param author 作者
     * @param moduleName 服务简称，例如：goods-service -> goods
     * @param tablePrefix 去除表前缀
     * @param include 表集合
     * @return AutoGenerator
     */
    public static AutoGenerator getAutoGenerator(String sourceName,String serviceName,String author,
                                          String moduleName,
                                          String tablePrefix, String... include){
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(getGlobalConfig(serviceName,author));
        mpg.setDataSource(getDataSourceConfig(sourceName));
        mpg.setPackageInfo(getPackageConfig(moduleName));
        mpg.setStrategy(getStrategyConfig(tablePrefix,include));
        return mpg;
    }


}
