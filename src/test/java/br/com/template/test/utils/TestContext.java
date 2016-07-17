package br.com.template.test.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.Template.config.PersistenceConfig;

@Configuration
@Import({ PersistenceConfig.class})
@ComponentScan(basePackages = { "br.com.template.test" })
public class TestContext {

}
