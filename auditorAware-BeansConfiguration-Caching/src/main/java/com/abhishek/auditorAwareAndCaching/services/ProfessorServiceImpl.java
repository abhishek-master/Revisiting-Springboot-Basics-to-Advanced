package com.abhishek.auditorAwareAndCaching.services;

import com.abhishek.auditorAwareAndCaching.dto.GetAllProfessorsResponseDto;
import com.abhishek.auditorAwareAndCaching.dto.ProfessorDto;
import com.abhishek.auditorAwareAndCaching.dto.ProfessorResponseDto;
import com.abhishek.auditorAwareAndCaching.entities.Professor;
import com.abhishek.auditorAwareAndCaching.entities.Student;
import com.abhishek.auditorAwareAndCaching.entities.Subject;
import com.abhishek.auditorAwareAndCaching.repository.AdmissionRecordRepository;
import com.abhishek.auditorAwareAndCaching.repository.ProfessorRepository;
import com.abhishek.auditorAwareAndCaching.repository.StudentRepository;
import com.abhishek.auditorAwareAndCaching.repository.SubjectRepository;
import org.modelmapper.ModelMapper ;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProfessorServiceImpl implements ProfessorService{

    private final ProfessorRepository professorRepository ;
    private final SubjectRepository subjectRepository ;
    private final StudentRepository studentRepository ;
    private final AdmissionRecordRepository admissionRecordRepository ;


    final ModelMapper modelMapper ;
    ProfessorServiceImpl (ProfessorRepository professorRepository, ModelMapper modelMapper, AdmissionRecordRepository admissionRecordRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.professorRepository = professorRepository ;
        this.studentRepository = studentRepository ;
        this.subjectRepository = subjectRepository ;
        this.admissionRecordRepository = admissionRecordRepository ;
        this.modelMapper = modelMapper ;
    }

    @Override
    public List<GetAllProfessorsResponseDto> getAllProfessors() {
        List<Professor> allProfessors =  professorRepository.findAll() ;
        return allProfessors.stream().
                                    map((professor -> {
                                        String profTitle = professor.getTitle();
                                        List<String> listOfSubjects = new ArrayList<>( );
                                        professor.getSubjects().stream().
                                                peek(subject -> listOfSubjects.add(subject.getTitle())).
                                                toList();
                                        return new GetAllProfessorsResponseDto(profTitle, listOfSubjects);
                                    })).
                                    collect(Collectors.toList());
    }

    @Override
    public ProfessorDto getProfessorById(Integer professor_id) {
        return modelMapper.map(professorRepository.findById(professor_id).orElseThrow(), ProfessorDto.class);
    }

    @Override
    @Transactional
    public ProfessorDto createProfessor(ProfessorDto professorData) {
        return modelMapper.map(professorRepository.save(modelMapper.map(professorData, Professor.class)), ProfessorDto.class);
    }

    @Override
    @Transactional
    public ProfessorResponseDto updateProfessor (Integer id, Map<String, List<Integer>> data){
        Professor prof = professorRepository.findById(id).orElseThrow() ;
        boolean hasSubjects = data.containsKey("subjects") ;
        boolean hasStudents = data.containsKey("students") ;

        //I know we can have more modular code but let's do it all in here :)
        if(hasStudents){
            List<Integer> studentIds = data.get("students");
            List<Student> students = new ArrayList<>();
            for(int student_id : studentIds) {
                Student student = studentRepository.findById(student_id).orElseThrow();
                students.add(student);
            }
            prof.setStudents(students);
        }
        if(hasSubjects){
            List<Integer> subjectIds = data.get("subjects");
            List<Subject> subjects = new ArrayList<>();
            for(int subject_id : subjectIds) {
                Subject subject = subjectRepository.findById(subject_id).orElseThrow();
                subject.setProfessor(prof);
                subjects.add(subject);
            }
            prof.setSubjects(subjects);
        }
        professorRepository.save(prof);
        //Here
        return modelMapper.map(prof, ProfessorResponseDto.class);
    }
}
