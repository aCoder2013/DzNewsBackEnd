package song.core.exception;

import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Song on 2015/11/10.
 */
public class PersonalMappingExceptionResolver extends SimpleMappingExceptionResolver {


    public PersonalMappingExceptionResolver() {
        setWarnLogCategory(PersonalMappingExceptionResolver.class.getName());
    }

    @Override
    protected String buildLogMessage(Exception ex, HttpServletRequest request) {
        return "MVC Exception :"+ex.getLocalizedMessage();
    }
}
