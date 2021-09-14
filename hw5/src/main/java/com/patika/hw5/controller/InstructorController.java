package com.patika.hw5.controller;

import com.patika.hw5.dto.InstructorDTO;
import com.patika.hw5.dto.PermanentInstructorDTO;
import com.patika.hw5.dto.VisitingResearchersDTO;
import com.patika.hw5.entity.Instructor;
import com.patika.hw5.entity.PermanentInstructor;
import com.patika.hw5.entity.VisitingResearches;
import com.patika.hw5.mappers.InstructorMapper;
import com.patika.hw5.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class InstructorController {
    private InstructorService instructorService;

    /**
     * @param instructorService
     */
    @Autowired
    public InstructorController(InstructorService instructorService){this.instructorService=instructorService;}
    @GetMapping("/instructors/{id}")
    public ResponseEntity<Instructor> findInstructor(@PathVariable Long instructorId){
        Optional<Instructor> instructor=instructorService.findById(instructorId);
        return new ResponseEntity<>(instructor.get(),HttpStatus.OK);
    }

    /**
     * @return
     */
    @GetMapping("/all-instructors")
    public ResponseEntity<List<Instructor>> findAll(){

        return new ResponseEntity<>( instructorService.findAll(), HttpStatus.OK);
    }

    /**
     * @param instructor
     */
    @PostMapping("/save-instructor")
    public ResponseEntity<Instructor> save(@RequestBody InstructorDTO instructor){

        Optional<Instructor> instructorOptional=instructorService.save(instructor);
        if (instructorOptional.isPresent())
            return new ResponseEntity<>( instructorOptional.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * @param id
     */
    @DeleteMapping("/delete-instructor")
    public ResponseEntity<Instructor> delete(@PathVariable Long id){
        Optional<Instructor> instructorOptional=instructorService.findById(id);
        if (instructorOptional.isPresent()) {
            instructorService.deleteById(id);
            return new ResponseEntity<>(instructorOptional.get(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    @PutMapping("/update-instructors")
    public ResponseEntity<Instructor> update(@RequestBody Instructor instructor){
        Optional<Instructor> instructorOptional=instructorService.findById(instructor.getId());
        if (instructorOptional.isPresent())
            return new ResponseEntity<>(instructorService.update(instructor),HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    @Transactional
    @PutMapping("/update-permanent-instructors-salary")
    public ResponseEntity<PermanentInstructor> updatePermanentInstructorSalary(@PathVariable Long id,@RequestParam Number changePercent){
        Optional<Instructor> instructorOptional= instructorService.findById(id);
        if (instructorOptional.isPresent()) {
            if (instructorOptional.get().getClass()== PermanentInstructor.class) {
                //need fix
                PermanentInstructorDTO permanentInstructorDTO = (PermanentInstructorDTO) instructorOptional.get();
                 permanentInstructorDTO.setFixedSalary( CalculateSalary(permanentInstructorDTO.getFixedSalary(),changePercent));
            }

            return new ResponseEntity<>(instructorService.update(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    @Transactional
    @PutMapping("/update-visiting-researchers-salary")
    public ResponseEntity<Instructor> updateVisitingResearcherSalary(@PathVariable Long id,@RequestParam Number changePercent){
        Optional<Instructor> instructorOptional= instructorService.findById(id);
        if (instructorOptional.isPresent()) {
            if (instructorOptional.get().getClass()== VisitingResearches.class) {
                //need fix
                VisitingResearchersDTO visitingResearchersDTO = ((VisitingResearches) instructorOptional.get());
                visitingResearchersDTO.setHourlySalary( CalculateSalary(visitingResearchersDTO.getHourlySalary(),changePercent));
            }
            return new ResponseEntity<>(instructorService.update(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    public double CalculateSalary(double salary,Number changePercent){
        return salary+(salary*(double)changePercent/100);
    }
}
