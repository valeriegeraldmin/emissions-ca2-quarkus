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

    public List<Emission> parseXML(String filePath) {

        List<Emission> emissions = new ArrayList<>();  // new list every time

        try {
            File xmlFile = new File(filePath);
            System.out.println("Parsing XML from: " + xmlFile.getAbsolutePath());

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList rows = doc.getElementsByTagName("Row");

            for (int i = 0; i < rows.getLength(); i++) {

                Node row = rows.item(i);
                if (row.getNodeType() == Node.ELEMENT_NODE) {

                    Element elem = (Element) row;

                    // CATEGORY tag handling (two different XML tag names)
                    String category = "";
                    NodeList cat1 = elem.getElementsByTagName("Category__1_3"); // two underscores
                    NodeList cat2 = elem.getElementsByTagName("Category_1_3");  // one underscore

                    if (cat1.getLength() > 0)
                        category = cat1.item(0).getTextContent();
                    else if (cat2.getLength() > 0)
                        category = cat2.item(0).getTextContent();
                    else
                        category = "UNKNOWN";

                    int year = Integer.parseInt(
                            elem.getElementsByTagName("Year").item(0).getTextContent()
                    );

                    String scenario = elem.getElementsByTagName("Scenario").item(0).getTextContent();

                    // Value with empty-string protection
                    String valueText = elem.getElementsByTagName("Value").item(0).getTextContent().trim();
                    double predictedValue = valueText.isEmpty() ? 0.0 : Double.parseDouble(valueText);

                    // Build Emission object
                    Emission e = new Emission();
                    e.categoryCode = category;
                    e.categoryDescription = category;
                    e.year = year;
                    e.scenario = scenario;
                    e.predictedValue = predictedValue;
                    e.actualValue = 0;

                    emissions.add(e);
                    System.out.println("Parsed row #" + (i + 1));
                }
            }

            System.out.println("TOTAL PARSED: " + emissions.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emissions;
    }
}



