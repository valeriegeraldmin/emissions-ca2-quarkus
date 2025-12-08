package ie.valerie.ca2.service;

import ie.valerie.ca2.entity.Emission;
import ie.valerie.ca2.parser.XmlParserService;
import ie.valerie.ca2.repository.EmissionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EmissionImportService {

    @Inject
    EmissionRepository repository;

    @Transactional
    public void importFromXML(String filePath) {

        XmlParserService parser = new XmlParserService();
        parser.parseXML(filePath);

        for (Emission e : parser.emissions) {
            repository.persist(e);
        }
    }
}

