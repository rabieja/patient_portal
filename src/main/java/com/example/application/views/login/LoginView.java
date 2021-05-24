package com.example.application.views.login;


import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

@Route("/login")
@PageTitle("Login")
public class LoginView  extends VerticalLayout implements BeforeEnterObserver {
    private LoginForm login = new LoginForm();

    public LoginView(){
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");

        add(new H1("Log in"), login,
        new RouterLink("Register", RegisterView.class));
    }

    private boolean authenticate(AbstractLogin.LoginEvent e) {
        return true;
    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation().getQueryParameters()
                .getParameters().containsKey("error")) {
            login.setError(true);
        }
    }

}
