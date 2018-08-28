package br.com.campusbase;

import com.svlada.security.model.UserContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthentication implements IUserAuthentication{

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public UserContext getPrincipal() {
        return (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    
    
}
