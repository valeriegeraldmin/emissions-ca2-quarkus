package ie.valerie.ca2.parser;

import ie.valerie.ca2.entity.Emission;
import jakarta.enterprise.context.ApplicationScoped;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class XmlParserService {

    public List<Emission> emissions = new ArrayList<>();

    public void parseXML(String filePath) {

        try {
            File xmlFile = new File(filePath);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            NodeList rows = doc.getElementsByTagName("Row");

            for (int i = 0; i < rows.getLength(); i++) {

                Element elem = (Element) rows.item(i);

                String description = elem.getElementsByTagName("Category_1_3").item(0).getTextContent();
                int year = Integer.parseInt(elem.getElementsByTagName("Year").item(0).getTextContent());
                String scenario = elem.getElementsByTagName("Scenario").item(0).getTextContent();
                double value = Double.parseDouble(elem.getElementsByTagName("Value").item(0).getTextContent());

                Emission e = new Emission();
                e.categoryDescription = description;
                e.categoryCode = "CAT-" + (i + 1);
                e.year = year;
                e.scenario = scenario;
                e.predictedValue = value;
                e.actualValue = 0;

                emissions.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

