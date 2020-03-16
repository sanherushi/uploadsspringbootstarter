package com.majingji.autoconfig;

import com.majingji.properties.UploadsProperties;
import com.majingji.service.UploadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UploadsProperties.class)
@ConditionalOnClass(UploadsService.class)
public class UploadsServiceAutoConfig {
    @Autowired
    private UploadsProperties uploadsProperties;
    @Bean
    public UploadsService getService(){
        return new UploadsService(uploadsProperties);
    }


}
