package com.example.application.views.login;


import com.example.application.api.database.services.AuthService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

@Route("/login")
@PageTitle("Login")
public class LoginView  extends VerticalLayout  {

    public LoginView(AuthService authService){
        addClassName("login-view");

        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");
        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.setItems("PATIENT", "DOCTOR");
        radioGroup.setValue("PATIENT");

        VerticalLayout vl = new VerticalLayout();
        vl.setSizeFull();
        vl.setAlignItems(Alignment.CENTER);

        vl.setJustifyContentMode(JustifyContentMode.CENTER);

        vl.add(new H1("Log in"),
                radioGroup,
            username,
            password,
        new Button("Log in", event ->{
            try {
                authService.authenticate(username.getValue(), password.getValue(), radioGroup.getValue());
                UI.getCurrent().navigate("about");
            } catch (Exception e) {
                Notification.show("Wrong credentials.");
            }
        }),
        new RouterLink("Register", RegisterView.class));

        add(vl);
    }


}


