package crptapi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class Product {
    @JsonProperty("certificate_document")
    private final String certificateDocument;
    @JsonProperty("certificate_document_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final Date certificateDocumentDate;
    @JsonProperty("certificate_document_number")
    private final String certificateDocumentNumber;
    @JsonProperty("owner_inn")
    private final String ownerInn;
    @JsonProperty("producer_inn")
    private final String producerInn;
    @JsonProperty("production_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final Date productionDate;
    @JsonProperty("tnved_code")
    private final String tnvedCode;
    @JsonProperty("uit_code")
    private final String uitCode;
    @JsonProperty("uitu_code")
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
}