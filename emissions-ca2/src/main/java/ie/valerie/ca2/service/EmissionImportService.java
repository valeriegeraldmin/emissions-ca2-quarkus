package ie.valerie.ca2.service;

import ie.valerie.ca2.entity.Emission;
import ie.valerie.ca2.parser.XmlParserService;
import ie.valerie.ca2.repository.EmissionRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EmissionImportService {

    @Inject
    EmissionRepository repository;

    @Inject
    XmlParserService parser;

    @Transactional
    public void importFromXML(String filePath) {

        // Parse XML and receive fresh list
        List<Emission> list = parser.parseXML(filePath);

        System.out.println("Emissions loaded from parser = " + list.size());

        // Save all emissions
        for (Emission e : list) {
            repository.persist(e);
        }
    }
}

