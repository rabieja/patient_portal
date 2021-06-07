package com.example.application.api.database;

import com.example.application.api.database.services.VisitService;
import com.vaadin.flow.component.notification.Notification;

import java.util.*;

public class ThreadController extends Thread{

    public VisitService visitService;

    public ThreadController(VisitService visitService) {
        this.visitService = visitService;
    }

    public void run(Visit visit) throws InterruptedException {
        visitService.reserved(visit);
    }
}
