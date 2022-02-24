package com.example.dddinpractice.SnackMachines;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnackMachineRepository extends JpaRepository<SnackMachine, Long> {

}
