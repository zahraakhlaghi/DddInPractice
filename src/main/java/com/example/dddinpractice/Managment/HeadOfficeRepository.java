package com.example.dddinpractice.Managment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadOfficeRepository extends JpaRepository<HeadOffice, Long> {
}
