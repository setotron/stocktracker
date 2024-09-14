package com.ifp.invt_tfg.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("C:/TFG-INVT/prod/")
    private String rutaImagenes;

    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/productos/**").addResourceLocations("file:" + rutaImagenes);

    }

}
