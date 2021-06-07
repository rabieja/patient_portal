package com.example.application.api.database.services;

import com.example.application.api.database.*;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class VisitService {
    private final VisitRepo visitRepo;

    public VisitService(VisitRepo visitRepo) {
        this.visitRepo = visitRepo;
    }

    public List<Visit> findAll(){
        if((VaadinSession.getCurrent().getAttribute(AppUser.class).getRole()).equals(Role.DOCTOR)) {
            return visitRepo.search(VaadinSession.getCurrent().getAttribute(AppUser.class).getUsername());
        }else{
            return visitRepo.searchPatientVisit(VaadinSession.getCurrent().getAttribute(AppUser.class).getUsername());
        }
    }

    public void addVisit(String date) {
        visitRepo.save(new Visit(date, VaadinSession.getCurrent().getAttribute(AppUser.class).getUsername(), "", false));
    }

    public void deleteXXXVisit(){
        visitRepo.xxx("xxx").forEach(visit -> deleteVisit(visit));
    }

    public void deleteVisit(Visit visit) {
        visitRepo.delete(visit);
    }

    public List<Visit> findAllVisit() {
        return visitRepo.findAll();
    }

    public List<Visit> findAllVisitForDoctor(String username) {
        return visitRepo.searchFree(username, "free");
    }

    public void reserved(Visit visit) {
        visit.setReserved(true);
        visit.setPatient(VaadinSession.getCurrent().getAttribute(AppUser.class).getUsername());
        visitRepo.save(visit);
    }

    public void deleteVisitPatient(Visit visit) {
        visit.setPatient("");
        visit.setReserved(false);
        visitRepo.save(visit);
    }
}
