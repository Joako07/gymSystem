package com.springboot.gym.gymsystem;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValuesConfig {

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
