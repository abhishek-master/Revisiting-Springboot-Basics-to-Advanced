package com.abhishek.auditorAwareAndCaching;

import com.abhishek.auditorAwareAndCaching.client.impl.DoctorClientImpl;
import com.abhishek.auditorAwareAndCaching.dto.DoctorDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestRestClient {
    @MockBean
    private DoctorClientImpl doctorClientService;

    @Test
    void getAllDoctors() {
        when(doctorClientService.getAllDoctors()).thenReturn(List.of());
        List<DoctorDto> doctorDtoList = doctorClientService.getAllDoctors();
        assertThat(doctorDtoList).isEmpty();
    }
}
