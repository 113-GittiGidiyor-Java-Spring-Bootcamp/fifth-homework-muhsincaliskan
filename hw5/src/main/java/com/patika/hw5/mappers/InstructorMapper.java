package com.patika.hw5.mappers;


import com.patika.hw5.dto.InstructorDTO;
import com.patika.hw5.dto.PermanentInstructorDTO;
import com.patika.hw5.dto.VisitingResearchersDTO;
import com.patika.hw5.entity.Instructor;
import com.patika.hw5.entity.PermanentInstructor;
import com.patika.hw5.entity.VisitingResearches;
import com.patika.hw5.service.InstructorService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    @Autowired
    InstructorService instructorService=null;
    Instructor maprFromInstructoDTOrtoInstructor(InstructorDTO instructorDTO);
    InstructorDTO maprFromInstructortoInstructorDTO(Instructor instructor);
    VisitingResearchersDTO  mapFromVisitingResearcherToVisitingResearcherDTO(VisitingResearches visitingResearches);
    VisitingResearches mapFromVisitingResearcherDTOToVisitingResearcher(VisitingResearchersDTO VisitingResearcherDTO);
    PermanentInstructorDTO mapFromPermanentInstructorToPermanentInstructorDTO(PermanentInstructor permanentInstructor);
    PermanentInstructor  mapFromPermanentInstructorDTOToPermanentInstructor(PermanentInstructorDTO permanentInstructorDTO);

}
