package song.core.config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by Song on 2016/2/24.
 */
@Configuration
public class ServletConfig {

    /*

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
*/

    /*@Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(80);
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound"));
        factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/servererror"));
        return factory;
    }*/

}
