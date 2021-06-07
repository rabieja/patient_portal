package com.example.application.api.database.services;



import com.example.application.api.database.*;


import com.example.application.views.about.AboutView;
import com.example.application.views.info.InfoView;
import com.example.application.views.main.MainView;

import com.example.application.views.search.SearchView;
import com.example.application.views.timetable.TimetablePatientView;
import com.example.application.views.timetable.TimetableView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    public record AuthorizedRoute(String route, String name, Class<? extends Component> view){
    }

    public class AuthException extends Exception{

    }

    private final UserRepo userRepo;


    public AuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public List<AppUser> findAll(){
        return userRepo.findAll();
    }
    public void register(String username, String password, String role){
        if (role == "PATIENT"){
            userRepo.save(new AppUser(username, password, Role.PATIENT));
        }else {
            userRepo.save(new AppUser(username, password, Role.DOCTOR));
        }
    }

    public void authenticate(String username, String password, String role) throws Exception {
        AppUser user = userRepo.getByUsername(username);

        if(user != null && (user.getRole().toString()).equals(role) && user.checkPassword(password)){
            VaadinSession.getCurrent().setAttribute(AppUser.class, user);
            createRoutes(user.getRole());
        }else{
            throw new Exception();
        }
    }
    private void createRoutes(Role role) {
        getAuthorizedRoutes(role).stream()
                .forEach(route ->
                        RouteConfiguration.forSessionScope().setRoute(
                               route.route, route.view, MainView.class));
    }
    public List<AuthorizedRoute> getAuthorizedRoutes(Role role){
        ArrayList<AuthorizedRoute> routes = new ArrayList<>();
        if(role.equals(Role.PATIENT)){
            routes.add(new AuthorizedRoute("search_a_doctor", "Find a doctor", SearchView.class));
            routes.add(new AuthorizedRoute("patient_timetable", "Timetable", TimetablePatientView.class));
            routes.add(new AuthorizedRoute("about", "About", AboutView.class));
        }else if(role.equals(Role.DOCTOR)){
            routes.add(new AuthorizedRoute("timetable", "Timetable", TimetableView.class));
            routes.add(new AuthorizedRoute("add_info", "Add info", InfoView.class));
            routes.add(new AuthorizedRoute("about", "About", AboutView.class));
        }
        return routes;
    }


    @PostConstruct
    public void generateData(){
        AppUser user1 = userRepo.save(new AppUser("name1", "password",  Role.DOCTOR));
        AppUser user2 = userRepo.save(new AppUser("name2", "password",  Role.DOCTOR));
        AppUser user3 = userRepo.save(new AppUser("name3", "password",  Role.DOCTOR));
        AppUser user4 = userRepo.save(new AppUser("name4", "password",  Role.DOCTOR));
        AppUser user5 = userRepo.save(new AppUser("name5", "password",  Role.DOCTOR));
        AppUser user6 = userRepo.save(new AppUser("name6", "password",  Role.DOCTOR));
        AppUser user7 = userRepo.save(new AppUser("name7", "password",  Role.DOCTOR));
        AppUser user8 = userRepo.save(new AppUser("name8", "password",  Role.DOCTOR));
        AppUser user9 = userRepo.save(new AppUser("name9", "password",  Role.DOCTOR));
        AppUser user10 = userRepo.save(new AppUser("name10", "password",  Role.DOCTOR));
        AppUser user11 = userRepo.save(new AppUser("name11", "password",  Role.DOCTOR));
        AppUser user12 = userRepo.save(new AppUser("name12", "password",  Role.DOCTOR));
        AppUser user13 = userRepo.save(new AppUser("name13", "password",  Role.DOCTOR));
        AppUser user14 = userRepo.save(new AppUser("name14", "password",  Role.DOCTOR));
        AppUser user15 = userRepo.save(new AppUser("name15", "password",  Role.DOCTOR));
        AppUser user16 = userRepo.save(new AppUser("name16", "password",  Role.DOCTOR));
        AppUser user17 = userRepo.save(new AppUser("name17", "password",  Role.DOCTOR));
        AppUser user18 = userRepo.save(new AppUser("name18", "password",  Role.DOCTOR));
        AppUser user19 = userRepo.save(new AppUser("name19", "password",  Role.DOCTOR));
        AppUser user20 = userRepo.save(new AppUser("name20", "password",  Role.DOCTOR));
        AppUser user21 = userRepo.save(new AppUser("name21", "password",  Role.DOCTOR));
    }

}
