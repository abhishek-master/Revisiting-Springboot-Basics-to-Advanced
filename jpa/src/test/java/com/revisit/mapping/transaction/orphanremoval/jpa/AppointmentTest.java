package com.revisit.mapping.transaction.orphanremoval.jpa;

import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Appointment;
import com.revisit.mapping.transaction.orphanremoval.jpa.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testCreateAndAssignAppointment (){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 10, 11,1,1,1))
                .reason("Stomachache")
                .status("SEVERE")
                .build();
        Appointment createdAppointment = appointmentService.createAndAssignAppointment(appointment ,BigInteger.valueOf(4), BigInteger.valueOf(4));
        System.out.println(createdAppointment);

    }

    @Test
    public void testAppointmentUpdate () {
        appointmentService.updateAppointmentForDoctor(BigInteger.valueOf(7));
    }
}
