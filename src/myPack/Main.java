package myPack;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;


public class Main{
    
    public static void main(String[] args) throws IOException{

        Integer h = 0;
        System.out.println(h);
    
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the file to load: ");
        String fileName = scnr.nextLine();
        Matrix test = new Matrix();
        test =  Matrix.loadFromFile(fileName);
        
        test.display();

        System.out.print("The Frobenius Norm is ");
        System.out.print(test.getNorm());
        System.out.println();

        System.out.print("Enter the row:");
        int row = scnr.nextInt();
        System.out.println("");
        System.out.print("Enter the col:");
        int column = scnr.nextInt();
        System.out.println("");
        System.out.println("Enter the value:");
        double val = scnr.nextDouble();

        test.setEntry(row, column, val);
        

        Matrix Transpos = new Matrix();
        Transpos = test.transpose();

        Transpos.saveToFile("A1.txt");

        Transpos = Transpos.transpose();
        
        System.out.println("This is the trenspose");
        Transpos.display();

        scnr.close();

    }



}