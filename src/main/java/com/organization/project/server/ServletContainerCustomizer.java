package com.organization.project.server;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.organization.project.util.PropertiesUtil;

@PropertySource("classpath:config.properties")
@Component
public class ServletContainerCustomizer extends PropertiesUtil implements EmbeddedServletContainerCustomizer {
	
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(Integer.valueOf(getPropertiesValue("organization.port")));
	}
}
