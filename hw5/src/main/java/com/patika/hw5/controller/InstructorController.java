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
     * @param permanentInstructorDTO
     */
    @PostMapping("/save-permanent-instructor")
    public ResponseEntity<PermanentInstructor> savePermanentInstructor(@RequestBody PermanentInstructorDTO permanentInstructorDTO){

        Optional<PermanentInstructor> permanentInstructorOptional=instructorService.savePermanentInstructor(permanentInstructorDTO);
        if (permanentInstructorOptional.isPresent())
            return new ResponseEntity<>( permanentInstructorOptional.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    /**
     * @param visitingResearchersDTO
     */
    @PostMapping("/save-visiting-researcher-instructor")
    public ResponseEntity<VisitingResearches> saveVisitingResearches(@RequestBody VisitingResearchersDTO visitingResearchersDTO){

        Optional<VisitingResearches> visitingResearchesOptional=instructorService.saveVisitingResearches(visitingResearchersDTO);
        if (visitingResearchesOptional.isPresent())
            return new ResponseEntity<>( visitingResearchesOptional.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * @param id
     */
    @DeleteMapping("/delete-instructor/{id}")
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
    @PutMapping("/update-permanent-instructors-salary/{id}")
    public ResponseEntity<?> updatePermanentInstructorSalary(@PathVariable Long id,@RequestParam Double changePercent){
        Optional<Instructor> instructorOptional= instructorService.findById(id);
        if (instructorOptional.isPresent()&&instructorOptional.get() instanceof PermanentInstructor) {
            instructorService.updatePermanentInstructorSalary(id,changePercent);
            return new ResponseEntity<>("New Salary for "+id+" is changed.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    @Transactional
    @PutMapping("/update-visiting-researchers-salary/{id}")
    public ResponseEntity<?> updateVisitingResearcherSalary(@PathVariable Long id,@RequestParam Double changePercent){
        Optional<Instructor> instructorOptional= instructorService.findById(id);
        if (instructorOptional.isPresent()&&instructorOptional.get() instanceof VisitingResearches) {
            instructorService.updateVisitingResearchesSalary(id,changePercent);
            return new ResponseEntity<>("New Salary for "+id+" is changed.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
