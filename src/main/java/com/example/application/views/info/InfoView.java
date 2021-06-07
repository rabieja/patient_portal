package com.example.application.views.info;

import com.example.application.api.database.AppUser;
import com.example.application.api.database.Doctor;
import com.example.application.api.database.services.DoctorService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;


@Route("add_info")
@PageTitle("Add information")
@Theme(themeFolder = "myapp")
public class InfoView extends VerticalLayout{

    private DoctorService doctorService;

    public InfoView(DoctorService doctorService){
        this.doctorService = doctorService;


        addClassName("add_info");
        addClassName("about-view");
        TextField name = new TextField("Name");
        TextField lastName = new TextField("Last name");
        TextField spec = new TextField("Specialisation");
        TextField clinicName = new TextField("Name of the clinic");

        AppUser user = VaadinSession.getCurrent().getAttribute(AppUser.class);
        if (doctorService.getUserByUsername(user.getUsername()) != null){
            Doctor doctor = doctorService.getUserByUsername(user.getUsername());
            name.setPlaceholder(doctor.getName());
            lastName.setPlaceholder(doctor.getLastName());
            spec.setPlaceholder(doctor.getSpecialisation());
            clinicName.setPlaceholder(doctor.getClinicName());
        }
        VerticalLayout layout =  new VerticalLayout();

        layout.setSizeFull();
  //       setAlignItems(FlexComponent.Alignment.CENTER);

        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        Button button = new Button("Send", event -> addInfo(
                name.getValue(),
                lastName.getValue(),
                spec.getValue(),
                clinicName.getValue()));

        layout.add(new H2("Add info about you"),
                name,
                lastName,
                spec,
                clinicName,
                button);
        add(layout);
    }

    private void addInfo(String name, String lastName, String spec, String clinicName) {
        doctorService.addAdditionalInfo(name, lastName, spec, clinicName);
    }
}
