package crptapi.models;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    private final String certificateDocument;
    private final Date certificateDocumentDate;
    private final String certificateDocumentNumber;
    private final String ownerInn;
    private final String producerInn;
    private final Date productionDate;
    private final String tnvedCode;
    private final String uitCode;
    private final String uituCode;
    
    public Product(String certificateDocument, Date certificateDocumentDate, String certificateDocumentNumber,
                   String ownerInn, String producerInn, Date productionDate, String tnvedCode,
                   String uitCode, String uituCode) {
        this.certificateDocument = certificateDocument;
        this.certificateDocumentDate = certificateDocumentDate;
        this.certificateDocumentNumber = certificateDocumentNumber;
        this.ownerInn = ownerInn;
        this.producerInn = producerInn;
        this.productionDate = productionDate;
        this.tnvedCode = tnvedCode;
        this.uitCode = uitCode;
        this.uituCode = uituCode;
    }
    
    @Override
    public String toString() {
        return "{ \"certificate_document\":" + this.certificateDocument + ", " +
               "\"certificate_document_date\":" + this.certificateDocumentDate.toString() + "," +
               "\"certificate_document_number\":" + this.certificateDocumentNumber + ", \"owner_inn\":"+ this.ownerInn + "," +
               "\"producer_inn\":" + this.producerInn + ", \"production_date\":" + this.productionDate.toString() + ", \"tnved_code\":" + this.tnvedCode + ", " +
               "\"uit_code\":" + this.uitCode + ", \"uitu_code\":" + this.uituCode + "}";
    }
}