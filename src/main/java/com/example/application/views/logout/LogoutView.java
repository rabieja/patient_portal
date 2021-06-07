package com.example.application.views.logout;

import com.example.application.api.database.AppUser;
import com.example.application.api.database.services.AuthService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import javax.servlet.http.HttpSession;

@Route("logout")
public class LogoutView extends Composite<VerticalLayout> {
    public LogoutView(){
        UI.getCurrent().getPage().setLocation("login");
        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();
    }
}
