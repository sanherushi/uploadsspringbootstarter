package com.majingji.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
//读取配置文件@Component组件
@ConfigurationProperties(prefix = "com.majingji")
public class UploadsProperties {
    //这里随意配置一个属性
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
