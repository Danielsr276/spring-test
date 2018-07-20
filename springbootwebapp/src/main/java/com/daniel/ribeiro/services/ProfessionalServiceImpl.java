package com.daniel.ribeiro.services;

import com.daniel.ribeiro.domain.Professional;
import com.daniel.ribeiro.repositories.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessionalServiceImpl implements ProfessionalService {
    private ProfessionalRepository professionalRepository;

    @Autowired
    public void setProfessionalRepository(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Iterable<Professional> listAllProfessionals() {
        return professionalRepository.findAll();
    }

    @Override
    public Professional getProfessionalById(Integer id) {
        return professionalRepository.findById(id).orElse(null);
    }

    @Override
    public Professional saveProfessional(Professional professional) {
        return professionalRepository.save(professional);
    }
}
