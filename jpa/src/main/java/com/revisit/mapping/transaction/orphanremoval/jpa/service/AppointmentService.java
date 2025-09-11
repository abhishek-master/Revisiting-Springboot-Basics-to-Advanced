package com.revisit.mapping.transaction.orphanremoval.jpa.service;

import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Appointment;
import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Doctor;
import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Patient;
import com.revisit.mapping.transaction.orphanremoval.jpa.repository.AppointmentRepository;
import com.revisit.mapping.transaction.orphanremoval.jpa.repository.DoctorRepository;
import com.revisit.mapping.transaction.orphanremoval.jpa.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor //Only generated constructor as for fields marked final or @NonNull
@Service
public class AppointmentService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository ;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appointment createAndAssignAppointment (Appointment appointment, BigInteger patientId, BigInteger doctorId){
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);
        return appointment;
    }


    @Transactional
    public void updateAppointmentForDoctor(BigInteger doctorId) {
        Doctor doc = doctorRepository.findById(doctorId).orElseThrow();
        //doctorRepository.findById(BigInteger.valueOf(7)).orElseThrow()
        Set<Appointment> docAppointment = doc.getAppointments().stream()
                .map(appointment -> {
                    appointment.setDoctor(null);
                    return appointment;
                })
                .collect(Collectors.toSet());
    /*
    * The reason your appointments are not getting removed when you run updateAppointmentForDoctor is because you are
    * only setting appointment.setDoctor(null) in the child entities but you are NOT removing those Appointment
    * entities from the Doctor's appointments collection, which is required for orphanRemoval to trigger deletions.
        Key points about orphanRemoval with @OneToMany:
        orphanRemoval=true deletes child entities only if they are removed from the parent's collection (here, removed from Doctor.appointments set).
        Simply nullifying the doctor reference in the Appointment entity is not enough.
        You must remove the Appointment objects from the Doctor's appointments collection for Hibernate/JPA to detect orphans and remove them from the database.
        What is missing in your method?
        Your method streams over the appointments and sets their doctor reference to null, but never removes those appointments from the doctor's appointments set.
    * */

        Set<Appointment> appointmentsToRemove = doc.getAppointments();
        Set<Appointment> appointmentsCopy = new HashSet<>(appointmentsToRemove);
        for(Appointment appointment : appointmentsCopy){
            doc.getAppointments().remove(appointment);
            appointment.setDoctor(null);//Optional but to do bi-directional update, good practice
        }
    }
}
