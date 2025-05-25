package com.mycompany.project;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Calculator {
    private static double getPerUnitRate() throws IOException {
        try (Scanner scanner = new Scanner(new File("\\\\DESKTOP-3DH51Q4\\Users\\jawwa\\OneDrive\\Desktop\\DA\\perUnitRate.txt"))) {
            return scanner.nextDouble();
        }
    }
    
    public static double calculateElectricityCharges(int units) {
        double perUnitRate;
        try {
            perUnitRate = getPerUnitRate();
        } catch (IOException ex) {
            ex.printStackTrace();
            return -1.0;
        }
        return units * perUnitRate;
    }
}
