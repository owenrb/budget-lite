package io.owenrbee.budget.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
/**
 * Internationalization bean
 * 
 * @author owenrb
 *
 */
public class Internationalization {
	
   @Bean
   /**
    * Use custom name messages in a properties file
    * @return
    */
   public LocalValidatorFactoryBean validtor() {
      LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
      bean.setValidationMessageSource(messageSource());
      return bean;
   }
   
   @Bean
   public MessageSource messageSource() {
      ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
      messageSource.setBasename("classpath:/i18n/validation");
      messageSource.setDefaultEncoding("UTF-8");
      return messageSource;
   }
}
