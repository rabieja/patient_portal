package com.example.application.views.about;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;

@Route("about")
@PageTitle("About")
public class AboutView extends VerticalLayout {

    public AboutView() {
        addClassName("about-view");
        setSizeFull();
        setAlignItems(FlexComponent.Alignment.CENTER);

        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(new H1("Welcome to patient portal"));
    }

}
