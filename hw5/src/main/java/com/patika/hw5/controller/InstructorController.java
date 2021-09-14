package com.patika.hw5.controller;

import com.patika.hw5.dto.InstructorDTO;
import com.patika.hw5.entity.Instructor;
import com.patika.hw5.entity.PermanentInstructor;
import com.patika.hw5.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Instructor> updateSalary(@PathVariable Long id,@RequestParam Number salary){
        Optional<Instructor> instructorOptional=instructorService.findById(id);
        //will fix
        if (instructorOptional.isPresent()) {
            if (instructorOptional.get() instanceof PermanentInstructor)
                instructorOptional.get()=instructorOptional.getClass().cast(PermanentInstructor);
            return new ResponseEntity<>(instructorService.update(instructor), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
