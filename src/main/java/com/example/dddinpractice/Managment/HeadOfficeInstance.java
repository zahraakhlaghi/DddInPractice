package com.example.dddinpractice.Managment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HeadOfficeInstance implements CommandLineRunner {

    private HeadOfficeRepository headOfficeRepository;
    public static HeadOffice Instance;

    @Autowired
    public HeadOfficeInstance(HeadOfficeRepository headOfficeRepository) {
        this.headOfficeRepository = headOfficeRepository;
    }


    public static HeadOffice getInstance() {
        return Instance;
    }

    @Override
    public void run(String... args) throws Exception {
        HeadOffice headOffice = new HeadOffice();
        Instance = headOfficeRepository.save(headOffice);
    }
}
