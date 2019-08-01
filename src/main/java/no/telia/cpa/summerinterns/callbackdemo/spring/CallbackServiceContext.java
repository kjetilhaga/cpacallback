package no.telia.cpa.summerinterns.callbackdemo.spring;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class CallbackServiceContext {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        ServletRegistrationBean<MessageDispatcherServlet> servletRegistrationBean =
                new ServletRegistrationBean<>(servlet, "/api/ws/*");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

    @Bean
    public Jaxb2Marshaller jaxb2MarshallerCallback() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the 'package' in gradle file
        marshaller.setContextPath("no.telia.cpa.summerinterns.ws.smscallback");
        return marshaller;
    }

    @Bean(name = "smscallback")
    public Wsdl11Definition smsCallbackWsdlDefinition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("ws/smscallback/smscallback.wsdl"));
        return wsdl11Definition;
    }

    @Bean(name = "smscallbackSchema")
    public XsdSchema smsCallbackSchema() {
        return new SimpleXsdSchema(new ClassPathResource("ws/smscallback/smscallback.xsd"));
    }


}
