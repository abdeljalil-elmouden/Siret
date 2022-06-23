package test.siret.domain;

import java.time.LocalDate;

import lombok.Data;

/**
 * 
 * 
 * @author : EL MOUDEN Abdeljalil
 * @email : elmoudene123@gmail.com
 * @version : 1.0
 *
 */
@Data
public class SiretData {

    private Long id;
    private String nic;
    private String fullAddress;
    private LocalDate creationDate;
    private String fullName;
    private String tvaNumber;


    @Override
    public String toString() {
        StringBuilder dataBuilder = new StringBuilder();
        appendFieldValue(dataBuilder, String.valueOf(id));
        appendFieldValue(dataBuilder, nic);
        appendFieldValue(dataBuilder, fullAddress);
        appendFieldValue(dataBuilder, creationDate.toString());
        appendFieldValue(dataBuilder, fullName);
        appendFieldValue(dataBuilder, tvaNumber);


        return dataBuilder.toString();
    }

    private void appendFieldValue(StringBuilder dataBuilder, String fieldValue) {
        if (fieldValue != null) {
            dataBuilder.append(fieldValue).append(",");
        } else {
            dataBuilder.append("").append(",");
        }
    }
}
