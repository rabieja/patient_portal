package com.example.application.views.timetable;

import com.example.application.api.database.Visit;
import com.example.application.api.database.services.VisitService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

import java.time.Duration;

@Route("patient_timetable")
@PageTitle("Timetable")
@Theme(themeFolder = "myapp")
@CssImport("./themes/myapp/views/visit-view.css")
public class TimetablePatientView extends VerticalLayout {
    private VisitService visitService;
    private Grid<Visit> grid = new Grid<>(Visit.class);
    private TimetableForm form;

    public TimetablePatientView(VisitService visitService) {
        this.visitService = visitService;
        addVisit("xxx");
        addClassName("visit-view");
        setSizeFull();
        configureGrid();

        form = new TimetableForm();
        form.addListener(TimetableForm.DeleteEvent.class, this::deleteVisit);
        form.addListener(TimetableForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form);

        add(grid, content);

        visitService.deleteXXXVisit();
        updateList();
        closeEditor();
    }

    private void deleteVisit(TimetableForm.DeleteEvent evt) {
        visitService.deleteVisitPatient(evt.getVisit());
        updateList();
        closeEditor();
    }
    private void addVisit(String date) {
        visitService.addVisit(date);
        updateList();
    }
    public void updateList() {
        grid.setItems(visitService.findAll());
    }

    private void configureGrid() {
        grid.addClassName("visit-grid");
        grid.setSizeFull();
        grid.setColumns("date", "status");

        grid.asSingleSelect().addValueChangeListener(event ->
                editVisit(event.getValue()));

    }
    public void editVisit(Visit visit) {
        if (visit == null) {
            closeEditor();
        } else {
            form.setVisit(visit);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setVisit(null);
        form.setVisible(false);
        removeClassName("editing");
    }
}
