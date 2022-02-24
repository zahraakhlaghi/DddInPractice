package com.example.dddinpractice.Atms.Rest;

import com.example.dddinpractice.Atms.Atm;
import com.example.dddinpractice.Atms.AtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("api/atm")
public class AtmController {

    private AtmRepository atmRepository;

    @Autowired
    public AtmController(AtmRepository atmRepository) {
        this.atmRepository = atmRepository;
    }

    @PostMapping("takeMoney/{id}")
    public ResponseEntity<?> TakeMoney(@PathVariable Long id, Double amount) {

        Optional<Atm> optional = atmRepository.findById(id);
        if (optional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Atm not found"
            );
        Atm atm = optional.get();
        String error = atm.CanTakeMoney(amount);
        if (error != null)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, error
            );
        Double amountWithCommission = atm.CalculateAmountWithCommission(amount);
        return ResponseEntity.status(HttpStatus.OK).body("You have taken: " + amountWithCommission);
    }
}
