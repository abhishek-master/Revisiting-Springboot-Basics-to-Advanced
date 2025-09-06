package com.revisiting.jpa.hibernate.spring_data_jpa.repositories;

import com.revisiting.jpa.hibernate.spring_data_jpa.dto.CDepartmentDTO;
import com.revisiting.jpa.hibernate.spring_data_jpa.dto.IDepartmentDto;
import com.revisiting.jpa.hibernate.spring_data_jpa.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    //Just check how returning InterfaceType in the Repository methods creates a proxy object/projection
    //And our controller serializes only these fields. This query indeed get the full entity but since return type is
    //IterfaceDTO and it has just 3 getters method, the result is reduced to just those.
    /*
    * In detail :
    * The custom @Query executes and returns the full entity (or partial columns if the query is selective).
Spring Data JPA tries to map the query result to the interface by matching the entity's properties to the interface's getter methods.
Instead of returning the full entity object, Spring returns a proxy that only exposes the properties defined by the interface getters.
When the controller serializes the response, only these exposed interface methods (fields) are serialized to JSON.
    * */



    //Interface Projection
    @Query("select d from DepartmentEntity d where d.id = :id")
    IDepartmentDto getIdInterfaceCustom(Integer id);

    //Class Projection or Constructor Projection
    @Query("Select new com.revisiting.jpa.hibernate.spring_data_jpa.dto.CDepartmentDTO(d.id, d.name, d.hod) from DepartmentEntity d where d.id = :id")
    CDepartmentDTO getIdInterfaceConcrete(Integer id);
}
