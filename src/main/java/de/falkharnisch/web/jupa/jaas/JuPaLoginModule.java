package de.falkharnisch.web.jupa.jaas;

import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.UserService;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JuPaLoginModule implements LoginModule {

    @Inject
    private UserService userService;

    private CallbackHandler handler;
    private Subject subject;
    private UserPrincipal userPrincipal;
    private RolePrincipal rolePrincipal;
    private String login;
    private List<String> userGroups;

    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        handler = callbackHandler;
        this.subject = subject;
    }

    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);

        try {
            handler.handle(callbacks);
            String name = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback) callbacks[1])
                    .getPassword());

            if (name != null) {
                try {
                    User user = userService.getUserByUsername(name);
                    if (password.equals(user.getPassword())) {
                        login = name;
                        userGroups = new ArrayList<String>();
                        userGroups.add("useradmin");
                        userGroups.add("loginUser");
                        return true;
                    }
                } catch (NoResultException e) {
                    throw new LoginException("Authentication failed");
                }
            }

            // If credentials are NOT OK we throw a LoginException
            throw new LoginException("Authentication failed");

        } catch (IOException e) {
            throw new LoginException(e.getMessage());
        } catch (UnsupportedCallbackException e) {
            throw new LoginException(e.getMessage());
        }
    }

    public boolean commit() {
        userPrincipal = new UserPrincipal(login);
        subject.getPrincipals().add(userPrincipal);

        if (userGroups != null && userGroups.size() > 0) {
            for (String groupName : userGroups) {
                rolePrincipal = new RolePrincipal(groupName);
                subject.getPrincipals().add(rolePrincipal);
            }
        }

        return true;
    }

    public boolean abort() {
        return false;
    }

    public boolean logout() {
        subject.getPrincipals().remove(userPrincipal);
        subject.getPrincipals().remove(rolePrincipal);
        return true;
    }
}
