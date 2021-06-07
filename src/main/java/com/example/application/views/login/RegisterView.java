package com.example.application.views.login;

import com.example.application.api.database.services.AuthService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("register")
public class RegisterView extends Composite{

    private final AuthService authService;

    public RegisterView(AuthService authService){
        this.authService = authService;
    }

    @Override
    protected Component initContent(){
        TextField username = new TextField("Username");
        PasswordField password1 = new PasswordField("Password");
        PasswordField password2 = new PasswordField("Confirm password");
        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.setLabel("Chose one option");
        radioGroup.setItems("PATIENT", "DOCTOR");
        radioGroup.setValue("PATIENT");

        VerticalLayout layout =  new VerticalLayout();

        layout.addClassName("register-view");
        layout.setSizeFull();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        Button button = new Button("Send", event -> register(
                username.getValue(),
                password1.getValue(),
                password2.getValue(),
                radioGroup.getValue()));

        layout.add(new H2("Register"),
                radioGroup,
                username,
                password1,
                password2,
                button,
        new RouterLink("Log in", LoginView.class));
        return layout;
    }
    private void register(String username, String password1, String password2, String role){
        if(username.trim().isEmpty()){
            Notification.show("Enter a username");
        }else if(password1.isEmpty()){
            Notification.show("Enter a password");
        }else if(!password1.equals(password2)){
            Notification.show("Passwords don't match");
        }else {
            authService.register(username, password1, role);
            Notification.show("Registration succeeded");
        }
    }
}
