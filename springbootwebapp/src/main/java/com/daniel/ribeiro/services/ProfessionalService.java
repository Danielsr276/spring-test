package com.daniel.ribeiro.services;


import com.daniel.ribeiro.domain.Professional;

public interface ProfessionalService {
    Iterable<Professional> listAllProfessionals();

    Professional getProfessionalById(Integer id);

    Professional saveProfessional(Professional professional);
}
