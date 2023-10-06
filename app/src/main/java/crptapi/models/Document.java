package crptapi.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Document implements Serializable {
    private String description;
    private String docId;
    private String docStatus;
    private String ownerInn;
    private String participantInn;
    private String producerInn;
    private Date productionDate;
    private String productionType;
    private List<Product> products;
    private Date regDate;
    private String regNumber;
    
    @Override
    public String toString() {
        return "\"description\":{\"participantInn\":" + this.description + "}, \"doc_id\":" + this.docId + ", \"doc_status\":" + this.docStatus + "," +
               "\"doc_type\": \"LP_INTRODUCE_GOODS\" ,  \"importRequest\": true, \"owner_inn\": " + this.ownerInn + ", \"participant_inn\": " + this.participantInn + ", " +
               "\"producer_inn\":" + this.producerInn + ", \"production_date\": " + this.productionDate.toString() + ", \"production_type\": " + this.productionType+ "," +
               "\"products\":" + this.products + ", \"reg_date\": " + this.regDate.toString() + ", \"reg_number\": " + this.regNumber + "}";  
    }
}