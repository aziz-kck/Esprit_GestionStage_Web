package com.example.pidev.userdto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configMapper {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}