package myPack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import static java.lang.Math.sqrt;

public class Matrix {

    public double[][] matrix;
    public int rows = 0;
    public int columns = 0;

    public Matrix(){
        double[][] matrix = null;
        this.matrix = matrix;
    }

    public static Matrix loadFromFile(String filename) throws IOException{
        
        try{
            FileInputStream readIn = new FileInputStream(filename);
        } catch(FileNotFoundException exception){
            System.out.println("Unable to open file");
        }

        FileInputStream readIn = new FileInputStream(filename);

        Scanner scnr = new Scanner(readIn);
        int row = scnr.nextInt();
        int column = scnr.nextInt();

        Matrix myMatrix = new Matrix();
        myMatrix.matrix = new double[row][column];

        myMatrix.setColCount(column);
        myMatrix.setRowCount(row);

        int rowCount = 0;

        for(int i = 0;scnr.hasNextDouble(); i++){
            if(i == column){
                i = 0;
                rowCount++;
            }
            myMatrix.matrix[rowCount][i] = scnr.nextDouble();
        }

            readIn.close();
            scnr.close();

        return myMatrix;
    }

    public void saveToFile(String filename) throws IOException{

        try{
            FileOutputStream file = new FileOutputStream(filename);
        } catch(FileNotFoundException exception){
            System.out.println("Unable to write to file");
            return;
        }
        FileOutputStream file = new FileOutputStream(filename);
        PrintWriter write = new PrintWriter(file);

        write.println(this.rows);
        write.println(this.columns);
        int i = 0, j = 0;
        
        for(int counter = 0; counter < (this.rows * this.columns); counter++){

            write.print(this.matrix[i][j] + " ");
            j++;

            if(j == this.columns){
                i++;
                j = 0;
                write.println();
            }
        }

        write.close();

    }

    public int getRowCount(){
        return this.rows;
    }

    public int getColCount(){
        return this.columns;
    }

    public double getEntry(int row, int col){

        if(this.rows <= row || this.columns <= col){
            return Double.NaN;

        } else{
            return this.matrix[row][col];
        }
    }

    public void setRowCount(int size){

        if(size > 100){
            size = 100;
        } else if(size < 0){
            size = 0;
        }

        this.rows = size;
        this.matrix = new double[this.rows][this.columns];

    }

    public void setColCount(int size){

        if(size > 100){
            size = 100;
        } else if(size < 0){
            size = 0;
        }

        this.columns = size;
        this.matrix = new double[this.rows][this.columns];

    }

    public boolean setEntry(int row, int col, double value){

        if(this.rows <= row || this.columns <= col){
            return false;

        } else{
            this.matrix[row][col] = value;
            return true;
        }
        
    }

    public void display(){

        int i, j; 

        for(i = 0; i < this.rows; i++){
            for(j = 0; j < this.columns; j++){
            if(j == 0){
                System.out.print(" |");
            }

            System.out.printf("%7.2f",this.matrix[i][j]);
            
            if(j == (this.columns - 1)){
                System.out.println(" |");
            }
        }
    }
    }

    public double getNorm(){
        int counter = 0;
        double squareSum = 0;
        int i = 0, j = 0;

        while(counter < (this.rows * this.columns)){
             squareSum = squareSum + (this.matrix[i][j] * this.matrix[i][j]);
             j++;
             counter++;

             if(j == this.columns){
                 j = 0;
                 i++;
             }

        }

        return sqrt(squareSum);
    }

    public Matrix transpose(){

        Matrix p = new Matrix();
        p.matrix = new double[this.columns][this.rows];
        p.setColCount(this.rows);
        p.setRowCount(this.columns);

        int i = 0, j = 0;

        for( i = 0; i < this.rows; i++){
            for(j = 0; j < this.columns; j++){
           // double temp = 
                p.matrix[j][i] = this.matrix[i][j];
            }
        }

        return p;
    }
}
