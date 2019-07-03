package com.meucontrole.api.session;

import com.meucontrole.api.entities.ApplicationUser;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ApplicationUserSession {

    private ApplicationUser applicationUser;

    public ApplicationUser getAuthorized() {
        return applicationUser;
    }

    public void authorize(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}
