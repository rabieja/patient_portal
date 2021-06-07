package com.example.application.views.search;

import com.example.application.api.database.Doctor;
import com.example.application.api.database.Visit;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import javax.print.Doc;
import java.util.List;

public class SearchForm extends FormLayout {
    private Doctor doctor;

    TextField name = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField specialisation = new TextField("Specialisation");
    TextField clinicName = new TextField("Clinic name");
    ComboBox<Visit> visit = new ComboBox<>("Visits");

    Binder<Doctor> binder = new BeanValidationBinder<>(Doctor.class);
    Button reserved = new Button("Reserve visit");
    Button close = new Button("Cancel");

    public SearchForm(List <Visit> visits) {
        addClassName("contact-form");
        binder.bindInstanceFields(this);

        name.setEnabled(false);
        lastName.setEnabled(false);
        specialisation.setEnabled(false);
        clinicName.setEnabled(false);

        visit.setItems(visits);
        visit.setItemLabelGenerator(Visit::getDate);
        add(name, lastName, specialisation, clinicName, visit, createButtonsLayout());
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        binder.readBean(doctor);
    }
    private HorizontalLayout createButtonsLayout() {
        reserved.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        reserved.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        reserved.addClickListener(event -> fireEvent(new ReservedEvent(this, doctor)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
        return new HorizontalLayout(reserved, close);
    }
    public static abstract class SearchEvent extends ComponentEvent<SearchForm> {
        private Doctor doctor;

        protected SearchEvent(SearchForm source, Doctor doctor) {
            super(source, false);
            this.doctor = doctor;
        }

        public Doctor getDoctor() {
            return doctor;
        }
    }

    public static class ReservedEvent extends SearchEvent {
        ReservedEvent(SearchForm source, Doctor doctor) {
            super(source, doctor);
        }
    }

    public static class CloseEvent extends SearchEvent {
        CloseEvent(SearchForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
