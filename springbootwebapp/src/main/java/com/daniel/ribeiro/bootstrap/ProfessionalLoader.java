package com.daniel.ribeiro.bootstrap;

import com.daniel.ribeiro.domain.Professional;
import com.daniel.ribeiro.repositories.ProfessionalRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProfessionalLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ProfessionalRepository professionalRepository;

    private Logger log = LogManager.getLogger(ProfessionalLoader.class);

    @Autowired
    public void setProfessionalRepository(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (int i = 0; i < 5; i++) {
            Professional professional = new Professional();
            professional.setProfessionalId(i);
            professional.setName("Professional " + i);
            professional.setIdentification("00" + i);
            professional.setCreatedAt(new Date());
            professional.setUpdatedAt(new Date());

            professionalRepository.save(professional);

            log.info("Saved New Professional - id: " + professional.getId());
        }
    }
}
