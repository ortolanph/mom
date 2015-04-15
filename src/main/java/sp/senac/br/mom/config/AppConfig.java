package sp.senac.br.mom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author ortolanph
 */
@Configuration
//@PropertySource("mom.properties")
@ComponentScan(basePackages = {"sp.senac.br.mom.controller", "sp.senac.br.mom.controller"})
//@EnableJpaRepositories("sp.senac.br.mom.repositories")
public class AppConfig extends WebMvcConfigurationSupport {

    @Autowired
    private Environment env;

    /**
     * Registra um controlador para a camada de vis&atilde;o.
     *
     * @param registry o registro administrado pelo spring
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    /**
     * Registra uma manipulador para a camada de vis&atilde;o.
     *
     * @param registry o registro administrado pelo spring
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("resources/");
    }

    /**
     * Mapeia todas as p&aacute;ginas JSP.
     *
     * @return o resolver da camada vis&atilde;o
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");

        return resolver;
    }
}
