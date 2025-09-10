package com.matheusiowa12.FipeProject.main;

import com.matheusiowa12.FipeProject.model.Data;
import com.matheusiowa12.FipeProject.model.Fipe;
import com.matheusiowa12.FipeProject.model.Models;
import com.matheusiowa12.FipeProject.service.ConsumeAPI;
import com.matheusiowa12.FipeProject.service.ConvertData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private Scanner sc = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1";
    private ConsumeAPI consume = new ConsumeAPI();
    private ConvertData converter = new ConvertData();

    public void showMenu(){
        var menu = """
                *** OPTIONS ***
                Car
                Motorcyle
                Truck
             
                Type in the option that you want to select: """;

        System.out.println(menu);
        String address;
        var option = sc.nextLine();


        if(option.toLowerCase().contains("car")){
            address = URL_BASE + "/carros/marcas";
        } else if (option.toLowerCase().contains("mot")) {
            address = URL_BASE + "/moto/marcas";
        } else if (option.toLowerCase().contains("cami")){
            address = URL_BASE + "/caminhoes/marcas";
        } else {
            System.out.println("Invalid input. Please try again!");
            return;
        }

        System.out.println("\nBrands for the selected type of vehicle:");
        var json = consume.obtainData(address);
        var brands = converter.obtainList(json, Data.class);
        brands.stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.println("\nType the code of a car's brand in which you want to search for its models: ");
        var brandCode = sc.nextLine();
        address += "/" + brandCode + "/modelos";

        json = consume.obtainData(address);
        var modelsList = converter.obtainData(json, Models.class);

        System.out.println("\nModels for the selected brand:");
        modelsList.models().stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);


        System.out.println("\nType the code of a car's model in which you want to search for its years: ");
        var carModelCode = sc.nextLine();
        address += "/" + carModelCode + "/anos";

        System.out.println("\nYears for the selected car's model:");
        json = consume.obtainData(address);
        var yearsList = converter.obtainList(json, Data.class);
        yearsList.stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.println("\nType the car's year to see its FIPE's table: ");
        var selectedCarYear = sc.nextLine();

        var carYearCode = yearsList.stream()
                .filter(o -> o.code().startsWith(selectedCarYear))
                .toList()
                .getFirst().code();

        address += "/" + carYearCode;

        json = consume.obtainData(address);
        var carFipeResult = converter.obtainData(json, Fipe.class);
        System.out.println("\nFIPE's table of the model: " + carFipeResult.model() + "\n");
        System.out.println(carFipeResult.toString());
    }
}
