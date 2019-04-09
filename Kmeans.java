/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Danar
 */
public class Kmeans {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String Path = "E:\\TIK UB\\Semester 5\\Pengenalan Pola\\datakmeans.txt.txt";
        Scanner input = new Scanner(System.in);
        ArrayList<Data> data = new ArrayList<Data>();
        data = DataBuilder.build(Path);
        //input variable
        Scanner s = new Scanner(System.in);
        System.out.print("K : ");
        int k = s.nextInt();
        System.out.print("Threshold : ");
        double threshold = s.nextDouble();
        double function = 0;
        //set random klaster
        for (Data temp : data) {
            temp.setRandomKlaster(1, k);
        }
        System.out.printf("%4S  %10S  %10S  %10S  %10S  %10S\n", "No", "Latitude", "Longitude", "Brightness", "Confidance", "Klaster");
        for (Data x : data) {
            System.out.printf("%4d  %10f  %10f  %10f  %10f  %10d\n", x.getNo(), x.getLatitude(), x.getLongitude(), x.getBrightness(), x.getConfidance(), x.getKlaster());
        }
        System.out.println("==================================================================");
        KmeansAlgoritma.KMeans(data, k, threshold, function);
        // display data klaster
        System.out.printf("%4S  %10S  %10S  %10S  %10S  %10S\n", "No", "Latitude", "Longitude", "Brightness", "Confidance", "Klaster");
        for (Data x : data) {
            System.out.printf("%4d  %10f  %10f  %10f  %10f  %10d\n", x.getNo(), x.getLatitude(), x.getLongitude(), x.getBrightness(), x.getConfidance(), x.getKlaster());
        }
        
    }
}
