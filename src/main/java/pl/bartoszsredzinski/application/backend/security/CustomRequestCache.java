package pl.bartoszsredzinski.application.backend.security;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 15.06.2022
 */
public class CustomRequestCache extends HttpSessionRequestCache{
    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse response){
        if(!SecurityUtils.isFrameworkInternalRequest(request)){
            super.saveRequest(request, response);
        }
    }
}
