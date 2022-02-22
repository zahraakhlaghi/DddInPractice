package com.example.dddinpractice.Rest;

import com.example.dddinpractice.Money;
import com.example.dddinpractice.SnackMachine;
import com.example.dddinpractice.SnackMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("api")
public class SnackMachineController {

    private SnackMachineRepository snackMachineRepository;

    @Autowired
    public SnackMachineController(SnackMachineRepository snackMachineRepository) {
        this.snackMachineRepository = snackMachineRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> CreateSnackMachine(SnackMachine snackMachine) {
        SnackMachine res = snackMachineRepository.save(snackMachine);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @PatchMapping("/insert/{id}")
    public ResponseEntity<?> InsertMoney(@PathVariable Long id, @RequestBody Money coinOrNote) {
        Optional<SnackMachine> optionalSnackMachine = snackMachineRepository.findById(id);
        if (optionalSnackMachine.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "SnackMachine not found"
            );
        SnackMachine snackMachine = optionalSnackMachine.get();
        snackMachine.InsertMoney(coinOrNote);
        return ResponseEntity.status(HttpStatus.OK).body("You have inserted: " + coinOrNote);
    }

    @GetMapping("return/{id}")
    public ResponseEntity<?> ReturnMonet(@PathVariable Long id) {
        Optional<SnackMachine> optionalSnackMachine = snackMachineRepository.findById(id);
        if (optionalSnackMachine.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "SnackMachine not found"
            );
        SnackMachine snackMachine = optionalSnackMachine.get();
        snackMachine.ReturnMoney();
        return ResponseEntity.status(HttpStatus.OK).body("Money was returned.");
    }

    @PatchMapping("buySnack/{id}")
    public ResponseEntity<?> BuySnack(@PathVariable Long id) {
        Optional<SnackMachine> optionalSnackMachine = snackMachineRepository.findById(id);
        if (optionalSnackMachine.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "SnackMachine not found"
            );
        SnackMachine snackMachine = optionalSnackMachine.get();
        snackMachine.BuySnack(1);
        return ResponseEntity.status(HttpStatus.OK).body("You have bought a snack.");
    }


}