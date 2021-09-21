package com.patika.hw5.service;

import com.patika.hw5.dto.InstructorDTO;
import com.patika.hw5.dto.PermanentInstructorDTO;
import com.patika.hw5.dto.VisitingResearchersDTO;
import com.patika.hw5.entity.Course;
import com.patika.hw5.entity.Instructor;
import com.patika.hw5.entity.PermanentInstructor;
import com.patika.hw5.entity.VisitingResearches;
import com.patika.hw5.exceptions.InstructorIsAlreadyExistException;
import com.patika.hw5.mappers.InstructorMapper;
import com.patika.hw5.repository.InstructorRepository;
import com.patika.hw5.repository.PermanentInstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class InstructorService implements BaseService<Instructor> {
   private final InstructorRepository instructorRepository;
   private final PermanentInstructorRepository permanentInstructorRepository;

   private final InstructorMapper instructorMapper;

    @Override
    public List<Instructor> findAll() {
        return (List<Instructor>) instructorRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    public Optional<Instructor> findById(Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        if (instructor.isPresent()) {
            return instructorRepository.findById(id);
        }
        throw new RuntimeException("Not found");
    }

    /**
     * @param instructorDTO
     * @return Optional<Instructor>
     */
    public Optional<Instructor> save(InstructorDTO instructorDTO) {
        boolean isExists=  instructorRepository.existsById(instructorDTO.getId());

        if (isExists){
            throw new InstructorIsAlreadyExistException("Instructor is already Exists");
        }
        Instructor instructor=instructorMapper.maprFromInstructoDTOrtoInstructor(instructorDTO);
        return Optional.of(instructorRepository.save(instructor));
    }

    /**
     * @param permanentInstructorDTO
     * @return Optional<PermanentInstructor>
     */
    public Optional<PermanentInstructor> savePermanentInstructor(PermanentInstructorDTO permanentInstructorDTO) {
        boolean isExists=  instructorRepository.existsById(permanentInstructorDTO.getId());

        if (isExists){
            throw new InstructorIsAlreadyExistException("Permanent Instructor is already Exists");
        }
        PermanentInstructor permanentInstructor=instructorMapper.mapFromPermanentInstructorDTOToPermanentInstructor(permanentInstructorDTO);
        return Optional.of(instructorRepository.save(permanentInstructor));
    }
    /**
     * @param visitingResearchersDTO
     * @return  Optional<VisitingResearches>
     */
    public Optional<VisitingResearches> saveVisitingResearches(VisitingResearchersDTO visitingResearchersDTO) {
        boolean isExists=  instructorRepository.existsById(visitingResearchersDTO.getId());

        if (isExists){
            throw new InstructorIsAlreadyExistException("Visiting Researches is already Exists");
        }
        VisitingResearches visitingResearches=instructorMapper.mapFromVisitingResearcherDTOToVisitingResearcher(visitingResearchersDTO);
        return Optional.of(instructorRepository.save(visitingResearches));
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        instructorRepository.deleteById(id);
    }

    /**
     * @param instructor
     * @return
     */
    @Override
    public Instructor update(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    /**
     * @param id
     * @return
     */
    public List<Course> findCoursesOfInstructor(Long id) {
        return  instructorRepository.findById(id).get().getCourseList();
    }
    public void updatePermanentInstructorSalary(long id,Double percentage){
        Optional<Instructor> instructorOptional = permanentInstructorRepository.findById(id);
            PermanentInstructor permanentInstructor=(PermanentInstructor)instructorOptional.get();
            double preSalary= permanentInstructor.getFixedSalary();
            double afterSalary= CalculateSalary(preSalary,percentage);

            permanentInstructor.setFixedSalary(afterSalary);

    }
    public void updateVisitingResearchesSalary(long id,Double percentage){
        Optional<Instructor> instructorOptional = permanentInstructorRepository.findById(id);
        if (instructorOptional.get() instanceof VisitingResearches){
            VisitingResearches visitingResearches=(VisitingResearches)instructorOptional.get();
            double preSalary= visitingResearches.getHourlySalary();
            double afterSalary= CalculateSalary(preSalary,percentage);
            visitingResearches.setHourlySalary(afterSalary);

        }

    }
    public double CalculateSalary(double salary,Double changePercent){
        return salary+(salary*(double)changePercent/100);
    }
}
