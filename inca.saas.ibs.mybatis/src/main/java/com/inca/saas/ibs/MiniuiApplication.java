package com.inca.saas.ibs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

@SpringBootApplication
public class MiniuiApplication implements EmbeddedServletContainerCustomizer{
	
	public static void main(String[] args) {
        SpringApplication.run(MiniuiApplication.class, args);
    }
    
    @Override  
    public void customize(ConfigurableEmbeddedServletContainer container) {  
        container.setPort(8082);  
    }
}
