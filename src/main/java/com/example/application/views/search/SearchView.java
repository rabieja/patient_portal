package com.example.application.views.search;


import com.example.application.api.database.User;
import com.example.application.views.login.AuthService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "search_a_doctor", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Find a doctor")
public class SearchView extends HorizontalLayout {

    private AuthService authService;
    private Grid<User> grid = new Grid<>(User.class);

    public SearchView(AuthService authService) {
        this.authService = authService;
        addClassName("search_view");
        setSizeFull();
        configureGrid();

        add(grid);
        updateList();
    }

    private void configureGrid() {
         grid.addClassName("user-grid");
         grid.setSizeFull();
         grid.setColumns("name", "password");
    }

    private void updateList(){
        grid.setItems(authService.findAll());
    }

}
