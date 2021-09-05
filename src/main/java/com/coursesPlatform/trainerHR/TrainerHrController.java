package com.coursesPlatform.trainerHR;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
@CrossOrigin(origins = "http://localhost:4200/")
class TrainerHrController {
    private final TrainerHrService trainerService;

    TrainerHrController(TrainerHrService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/all/internal")
    ResponseEntity<List<TrainerHrDTO>> getAllTrainers() {
        List<TrainerHrDTO> allTrainers = trainerService.findAllTrainers();
        return new ResponseEntity<>(allTrainers, HttpStatus.OK);
    }

    @GetMapping("/all/external")
    ResponseEntity<List<TrainerHrExternalDTO>> getAllTrainersExternal() {
        List<TrainerHrExternalDTO> allTrainers = trainerService.showAllTrainersExternal();
        return new ResponseEntity<>(allTrainers, HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<TrainerHrDTO> addNewTrainer(@RequestBody TrainerHrDTO trainerDTO) {
        TrainerHrDTO add = trainerService.add(trainerDTO);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @PutMapping("/{name}/{lastName}")
    ResponseEntity<TrainerHrDTO> updateTrainer(@PathVariable("name") String firstname,
                                               @PathVariable("lastName") String lastname,
                                               @RequestBody TrainerHrDTO trainerDTO
    ) {
        TrainerHrDTO update = trainerService.update(firstname, lastname, trainerDTO);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}/{lastname}")
    ResponseEntity<?> deleteTrainer(@PathVariable("name") String trainerName, @PathVariable("lastName") String trainerLastName) {
        trainerService.deleteByNameAndLastName(trainerName, trainerLastName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}