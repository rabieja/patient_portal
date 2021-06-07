package com.example.application.views.search;


import com.example.application.api.database.Doctor;
import com.example.application.api.database.ThreadController;
import com.example.application.api.database.services.DoctorService;
import com.example.application.api.database.services.VisitService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route("search_a_doctor")
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Find a doctor")
public class SearchView extends VerticalLayout {

    private final ThreadController threadController;
    private DoctorService doctorService;
    private VisitService visitService;
    private Grid<Doctor> grid = new Grid<>(Doctor.class);
    private TextField filterNameText = new TextField();
    private TextField filterLastNameText = new TextField();
    private TextField filterSpecText = new TextField();
    private TextField filterClinicText = new TextField();

    private SearchForm form;


    public SearchView(DoctorService doctorService,
                      VisitService visitService) {
        this.doctorService = doctorService;
        this.visitService = visitService;
        this.threadController = new ThreadController(visitService);
        addClassName("search_view");
        setSizeFull();
        configureFilter();
        configureGrid();

        form = new SearchForm(visitService.findAllVisit());

        form.addListener(SearchForm.ReservedEvent.class, e-> {
            try {
                reservedVisit();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        form.addListener(SearchForm.CloseEvent.class, e -> closeEditor());
        Div content = new Div(form);
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(filterNameText, filterLastNameText, filterSpecText, filterClinicText);
        add(hl, grid, content);
        updateList();
        closeEditor();
    }

    private  void reservedVisit() throws InterruptedException {
        threadController.run(form.visit.getValue());
        updateList();
        closeEditor();
    }

    private void configureFilter() {
        filterNameText.setPlaceholder("Filter by name...");
        filterNameText.setClearButtonVisible(true);
        filterNameText.setValueChangeMode(ValueChangeMode.LAZY);
        filterNameText.addValueChangeListener(e -> updateList());

        filterLastNameText.setPlaceholder("Filter by last name...");
        filterLastNameText.setClearButtonVisible(true);
        filterLastNameText.setValueChangeMode(ValueChangeMode.LAZY);
        filterLastNameText.addValueChangeListener(e -> updateList());

        filterSpecText.setPlaceholder("Filter by Specialisation...");
        filterSpecText.setClearButtonVisible(true);
        filterSpecText.setValueChangeMode(ValueChangeMode.LAZY);
        filterSpecText.addValueChangeListener(e -> updateList());

        filterClinicText.setPlaceholder("Filter by Clinic name...");
        filterClinicText.setClearButtonVisible(true);
        filterClinicText.setValueChangeMode(ValueChangeMode.LAZY);
        filterClinicText.addValueChangeListener(e -> updateList());

    }

    private void configureGrid() {
         grid.addClassName("doctor-grid");
         grid.setSizeFull();
         grid.setColumns("name", "lastName", "specialisation", "clinicName");

        grid.asSingleSelect().addValueChangeListener(event ->
                editDoctor(event.getValue()));
    }


    public void editDoctor(Doctor doctor) {
        if (doctor == null) {
            closeEditor();
        } else {
            form.setDoctor(doctor);
            form.visit.setItems(visitService.findAllVisitForDoctor(doctor.getUsername()));
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setDoctor(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList(){
        grid.setItems(doctorService.findAll(filterNameText.getValue(),
                filterLastNameText.getValue(),
                filterSpecText.getValue(),
                filterClinicText.getValue()));

    }

}
