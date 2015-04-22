package org.mom.config;

import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MOMWebInitializer implements WebApplicationInitializer {
    
    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        System.out.println("Entering onStartup");
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.register(WebSocketConfig.class);

        sc.addListener(new ContextLoaderListener(context));
        sc.setInitParameter("defaultHtmlEscape", "true");

        ServletRegistration.Dynamic dispatcher = sc.addServlet("dispatcherServlet", new DispatcherServlet(context));

        dispatcher.setLoadOnStartup(1);
        dispatcher.setAsyncSupported(true);
        dispatcher.addMapping("/");
        
        System.out.println("Exiting onStartup");
    }
}
