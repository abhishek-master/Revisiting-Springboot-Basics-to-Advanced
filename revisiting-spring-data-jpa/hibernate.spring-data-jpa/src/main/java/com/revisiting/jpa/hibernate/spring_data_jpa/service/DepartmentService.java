package com.revisiting.jpa.hibernate.spring_data_jpa.service;

import com.revisiting.jpa.hibernate.spring_data_jpa.dto.CDepartmentDTO;
import com.revisiting.jpa.hibernate.spring_data_jpa.dto.DepartmentDto;
import com.revisiting.jpa.hibernate.spring_data_jpa.dto.DepartmentResponseDto;
import com.revisiting.jpa.hibernate.spring_data_jpa.dto.IDepartmentDto;
import com.revisiting.jpa.hibernate.spring_data_jpa.entities.DepartmentEntity;
import com.revisiting.jpa.hibernate.spring_data_jpa.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final String str = "MODIFIED" ;
    private final DepartmentRepository departmentRepository ;

    DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository ;
    }

    public DepartmentResponseDto saveDepartment(DepartmentDto deptData) {
        DepartmentEntity deducedEntity = new DepartmentEntity(null ,deptData.getName(), deptData.getNumberOfClasses(), deptData.getNumberOfStudents(), deptData.getNumberOfProfessors(), deptData.getHod(), LocalDateTime.now(), LocalDateTime.now());
        DepartmentEntity res = departmentRepository.save(deducedEntity);
        return new DepartmentResponseDto(res.getId(), res.getName());

    }

    @Transactional
    public List<DepartmentResponseDto> saveAllDepartments(List<DepartmentDto> deptDataList) {
        List<DepartmentResponseDto> result = new ArrayList<>();
        for(DepartmentDto department : deptDataList){
            DepartmentEntity deducedEntity = new DepartmentEntity(null ,department.getName(), department.getNumberOfClasses(), department.getNumberOfStudents(), department.getNumberOfProfessors(), department.getHod(), LocalDateTime.now(), LocalDateTime.now());
            DepartmentEntity res = departmentRepository.save(deducedEntity);
            result.add(new DepartmentResponseDto(res.getId(), res.getName()));
        }
        return result ;

    }

    public IDepartmentDto getById(Integer id) {
        IDepartmentDto response = departmentRepository.getIdInterfaceCustom(id);
        return response;

    }

    public CDepartmentDTO getByIdConcrete(Integer id) {
        CDepartmentDTO result = departmentRepository.getIdInterfaceConcrete(id);
        //Now since we have result as a concrete class we can modify data here.
        String curName = result.getName() + str ;
        result.setName(curName);
        return  result ;
    }
}
