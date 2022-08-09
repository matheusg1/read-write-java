package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Product;

public class App {
    public static void main(String[] args) throws Exception {

        List<Product> productsList = new ArrayList<>();

        String strPath = "C:\\temp\\products.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(strPath))){

            String pathLine = br.readLine();

            while(pathLine != null){
                String[] lines = pathLine.split("\n");

                for (String line : lines) {
                    //System.out.println(line);
                    String[] line2 = line.split(",");
                    Product p1 = new Product(line2[0], Double.parseDouble(line2[1]), Integer.parseInt(line2[2]));
                    productsList.add(p1);
                }
                pathLine = br.readLine();
            }

            //Create "out" directory
            File path = new File(strPath);

            new File(path.getParent() + "\\out").mkdir();

            //Write new file
            String outputPath = "C:\\temp\\out\\summary.csv";

            try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))){
                
                for (Product product : productsList) {
                    bw.write(product.getName() + "," + product.getPrice() * product.getQuantity());
                    bw.newLine();
                }
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}