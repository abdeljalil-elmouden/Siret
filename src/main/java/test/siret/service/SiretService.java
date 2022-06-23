package test.siret.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;

import test.siret.domain.SiretData;

@Service
public class SiretService {

    public void transferData(String jsonData) throws IOException {
        ObjectMapper root = new ObjectMapper();
        JsonNode jsonNode = root.readTree(jsonData);

        //data company
        JsonNode company = jsonNode.path("etablissement");
        Long id = company.path("id").asLong();
        String nic = company.path("nic").asText();
        LocalDate creationDate = LocalDate.parse(company.path("date_creation").asText());
        String fullAdresse = company.path("geo_adresse").asText();

        // data unite_legale
        JsonNode unitLegale = company.path("unite_legale");
        String tvaNumber = unitLegale.path("numero_tva_intra").asText();

        SiretData siretData = new SiretData();
        siretData.setId(id);
        siretData.setNic(nic);
        siretData.setCreationDate(creationDate);
        siretData.setTvaNumber(tvaNumber);
        siretData.setFullAddress(fullAdresse);

        String header = "id,nic,fullAdresse,creationDate,fullname,tvaNumber"; // for methode 1
        String[] openCsvHeader = {"id", "nic", "fullAdresse", "creationDate", "fullname", "tvaNumber"};
        String[] data = {id.toString(), nic, fullAdresse, creationDate.toString(), "", tvaNumber};
        writeToCsv(siretData, header);
        //writeWithOpenCsv(openCsvHeader, data, id);

    }

    public void writeToCsv(SiretData data, String header) throws IOException {
        PrintWriter writer = new PrintWriter("C:\\Users\\siret_" + data.getId() + ".csv");
        writer.println(header);
        writer.println(data.toString());

        writer.close();
    }

    public void writeWithOpenCsv(String[] header, String[] data, Long idCompany) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter("C:\\Users\\openCsv_siret_" + idCompany + ".csv"));
        List<String[]> dataCsv = new ArrayList<>();
        dataCsv.add(header);
        dataCsv.add(data);
        csvWriter.writeAll(dataCsv);
    }
}
