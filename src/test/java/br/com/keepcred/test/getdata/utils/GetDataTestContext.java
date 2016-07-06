package br.com.keepcred.test.getdata.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.keepcred.config.PersistenceConfig;

@Configuration
@Import({ PersistenceConfig.class})
@ComponentScan(basePackages = { "br.com.sicoobapp.test.getdata" })
public class GetDataTestContext {

}
