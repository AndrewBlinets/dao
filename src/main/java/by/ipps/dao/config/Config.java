package by.ipps.dao.config;

import by.ipps.dao.utils.seriazable.PageSerializer;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

@Configuration
public class Config {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public Module jacksonHibernate4Module() {
    Hibernate5Module module = new Hibernate5Module();
    module.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
    return module;
  }

  @Bean
  public Module jacksonPageWithJsonViewModule() {
    SimpleModule module = new SimpleModule("jackson-page-with-jsonview", Version.unknownVersion());
    module.addSerializer(Page.class, new PageSerializer());
    return module;
  }
}
