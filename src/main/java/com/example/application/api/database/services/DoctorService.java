package com.example.application.api.database.services;

import com.example.application.api.database.*;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepo doctorRepo;
    private UserRepo userRepo;

    public DoctorService(DoctorRepo doctorRepo, UserRepo userRepo) {
        this.doctorRepo = doctorRepo;
        this.userRepo = userRepo;
    }

    public Doctor getUserByUsername(String username) {
        return this.doctorRepo.getByUsername(username);
    }

    public List<Doctor> findAll(String nameFilter, String lastNameFilter, String specFilter, String clinicFilter) {
        if ((nameFilter == null || nameFilter.isEmpty()) &&
                (lastNameFilter == null || lastNameFilter.isEmpty()) &&
                (specFilter == null || specFilter.isEmpty()) &&
                (clinicFilter == null || clinicFilter.isEmpty())) {
            return doctorRepo.findAll();
        } else {
            return doctorRepo.search(nameFilter, lastNameFilter, specFilter, clinicFilter);
        }
    }

    public void addAdditionalInfo(String name, String lastName, String specialisation, String clinicName) {
        AppUser user = VaadinSession.getCurrent().getAttribute(AppUser.class);
        Doctor doctor = doctorRepo.getByUsername(user.getUsername());
        if (doctor != null) {
            if (name == "") {
                name = doctor.getName();
            }
            if (lastName == "") {
                lastName = doctor.getLastName();
            }
            if (specialisation == "") {
                specialisation = doctor.getSpecialisation();
            }
            if (clinicName == "") {
                clinicName = doctor.getClinicName();
            }

            doctorRepo.delete(doctorRepo.getByUsername(user.getUsername()));
        }
        doctorRepo.save(new Doctor(user.getUsername(), name, lastName, specialisation, clinicName));
    }

    @PostConstruct
    public void generateData() {
        doctorRepo.save(new Doctor("name1", "Michał", "Kowalski", "pediatra", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name2", "Dominik", "Nowak", "pediatra", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name3", "Dominik", "Kowalski", "pediatra", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name4", "Michał", "Nowak", "pediatra", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name5", "Paweł", "Woźny", "pediatra", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name6", "Agnieszka", "Frankiewicz", "pediatra", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name7", "Matylda", "Drozd", "internista", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name8", "Dominika", "Drozd", "internista", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name9", "Krzysztof", "Władysław", "internista", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name10", "Monika", "Przybyszewska", "internista", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name11", "Małgorzata", "Pawłowicz", "internista", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name12", "Beata", "Majdan", "internista", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name13", "Zbigniew", "Wieniawa", "ginekolog", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name14", "Tadeusz", "Rymarowicz", "ginekolog", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name15", "Rafał", "Deprati", "ginekolog", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name16", "Paulina", "Antoniak", "ginekolog", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name17", "Dominika", "Iwaszkiewicz", "ginekolog", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name18", "Władysław", "Korzeniowski", "ginekolog", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name19", "Helena", "Korzeniowska", "ginekolog", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name20", "Sonia", "Przybyła", "ginekolog", "Szpital Powiatowy"));
        doctorRepo.save(new Doctor("name21", "Jakub", "Kowalski", "kardiolog", "Szpital Powiatowy"));
    }
}