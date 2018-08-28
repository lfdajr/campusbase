package br.com.campusbase;

import com.svlada.security.model.UserContext;
import org.springframework.security.core.Authentication;

public interface IUserAuthentication {
    public Authentication getAuthentication();
    public UserContext getPrincipal();
}
