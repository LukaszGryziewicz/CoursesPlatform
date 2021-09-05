package com.coursesPlatform.trainer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
@CrossOrigin(origins = "http://localhost:4200/")
class TrainerController {
    private final TrainerService trainerService;

    TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/all/internal")
    ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        List<TrainerDTO> allTrainers = trainerService.findAllTrainers();
        return new ResponseEntity<>(allTrainers, HttpStatus.OK);
    }

    @GetMapping("/all/external")
    ResponseEntity<List<TrainerExternalDTO>> getAllTrainersExternal() {
        List<TrainerExternalDTO> allTrainers = trainerService.showAllTrainersExternal();
        return new ResponseEntity<>(allTrainers, HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<TrainerDTO> addNewTrainer(@RequestBody TrainerDTO trainerDTO) {
        TrainerDTO add = trainerService.add(trainerDTO);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @PutMapping("/{name}/{lastName}")
    ResponseEntity<TrainerDTO> updateTrainer(@PathVariable("name") String firstname,
                                             @PathVariable("lastName") String lastname,
                                             @RequestBody TrainerDTO trainerDTO
    ) {
        TrainerDTO update = trainerService.update(firstname, lastname, trainerDTO);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}/{lastname}")
    ResponseEntity<?> deleteTrainer(@PathVariable("name") String trainerName, @PathVariable("lastName") String trainerLastName) {
        trainerService.deleteByNameAndLastName(trainerName, trainerLastName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}