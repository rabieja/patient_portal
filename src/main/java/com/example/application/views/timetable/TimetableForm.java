package com.example.application.views.timetable;

import com.example.application.api.database.Visit;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class TimetableForm extends FormLayout {

    private Visit visit;
    TextField date = new TextField("Date");
    TextField status = new TextField("Status");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    Binder<Visit> binder = new BeanValidationBinder<>(Visit.class);

    public TimetableForm() {
        addClassName("visit-form");
        binder.bindInstanceFields(this);
        date.setEnabled(false);
        status.setEnabled(false);

        HorizontalLayout hl = new HorizontalLayout(date, status);
        add(hl,
                createButtonsLayout());
    }

    private Component createButtonsLayout() {
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        close.addClickShortcut(Key.ESCAPE);
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
        return new HorizontalLayout(delete, close);
    }

    public void setVisit(Visit visit) {
        binder.setBean(visit);
        this.visit = visit;
    }

    public static abstract class TimetableEvent extends ComponentEvent<TimetableForm> {
        private Visit visit;

        protected TimetableEvent(TimetableForm source, Visit visit) {
            super(source, false);
            this.visit = visit;
        }

        public Visit getVisit() {
            return visit;
        }
    }



    public static class DeleteEvent extends TimetableEvent {
        DeleteEvent(TimetableForm source, Visit visit) {
            super(source, visit);
        }

    }

    public static class CloseEvent extends TimetableEvent {
        CloseEvent(TimetableForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }


}

