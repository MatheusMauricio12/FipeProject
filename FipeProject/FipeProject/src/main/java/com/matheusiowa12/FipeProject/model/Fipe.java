package com.matheusiowa12.FipeProject.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Fipe(@JsonAlias("Valor") String value, @JsonAlias("Marca")
String brand, @JsonAlias("Modelo") String model, @JsonAlias("AnoModelo") int modelYear, @JsonAlias("Combustivel")
String fuel, @JsonAlias("CodigoFipe") String fipeCode, @JsonAlias("MesReferencia") String referenceMonth, @JsonAlias("SiglaCombustivel") String fuelAcronym) {

    @Override
    public String toString() {
        return "Value: " + this.value + "\n" +
                "Brand: " + this.brand + "\n" +
                "Model: " + this.model + "\n" +
                "Model's Year: " + this.modelYear + "\n" +
                "Fuel's type: " + this.fuel + "\n" +
                "FIPE's code: " + this.fipeCode + "\n" +
                "Reference's month: " + this.referenceMonth + "\n" +
                "Fuel's acronym: " + this.fuelAcronym + "\n";
    }
}
