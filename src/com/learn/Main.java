package com.learn;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("\n*Enter the number next to your desired choice*");

        //Menu
        while (true){
            System.out.print("\n\nMenu" +
                    "\n1. What is a BMI? How does it work? Things to know" +
                    "\n2. Calculate BMI" +
                    "\n3. Exit" +
                    "\n>>>");

            byte main_menu;
            while(true){
                try{
                    main_menu = input.nextByte();
                    if ( 0 < main_menu && main_menu < 4 ){
                        System.out.println(" ");
                        break;
                    }
                    else
                        throw new InputMismatchException();
                }
                catch(InputMismatchException e){
                    System.err.println("Please Enter a number between 1 and 3");
                    input.nextLine();
                }
            }
            if (main_menu == 3){
                System.out.println("Thank You For Using The Program");
                System.exit(0);
            }
            else if (main_menu == 1){
                try {
                    File myFile = new File("READ");
                    Scanner textFileReader = new Scanner(myFile);
                    while(textFileReader.hasNextLine()){
                        String text = textFileReader.nextLine();
                        System.out.println(text);
                    }
                    textFileReader.close();
                }
                catch(FileNotFoundException e){
                    System.err.println("Looks Like the File is not installed");
                    e.printStackTrace();
                }
            }
            else{
                System.out.print("Which unit of measurement do you prefer?" +
                        "\n1. Imperial" +
                        "\n2. Metric" +
                        "\n>>>");

                byte unit;
                while(true){
                    try{
                        unit = input.nextByte();
                        if (1 == unit || unit == 2){
                            break;
                        }
                        else{
                            throw new InputMismatchException();
                        }
                    }
                    catch(InputMismatchException e){
                        System.err.println("Enter 1 or 2");
                        input.nextLine();
                    }
                }

                float weight;
                float height;
                while(true) {
                    try {
                        System.out.print("\nWhat is your height? (inches or centimeters)" +
                                "\n>>>");
                        height = input.nextFloat();
                        System.out.print("\nWhat is your weight? (pounds or kilograms)" +
                                "\n>>>");
                        weight = input.nextFloat();
                        break;
                    } catch (Exception e) {
                        System.err.println("You need to provide a valid Height and Weight to continue\n");
                        input.next();
                    }
                }
                float bmi = BMI(unit, height, weight);
                System.out.println("\nYour BMI is " + bmi);
                int scaling = Scaling(bmi);
                if (scaling == 1){
                    System.out.println("Eat more");
                }
                else if (scaling < 4){
                    System.out.println("Eat better");
                }
                else{
                    System.out.println("You are at a good place");
                }
            }
        }
    }

    static float BMI (byte unit, float height, float weight){
        float bmi;
        if(unit == 2){
            bmi = weight / (float)Math.pow(weight, 2);
            return bmi;
        }
        bmi = weight / (float)Math.pow(height, 2) * 703;
        return bmi;
    }

    static int Scaling (float bmi){
        System.out.print("Based on that BMI you are considered to be ");
        if (bmi < 18.5){
            System.out.print("underweight");
            return 1;
        }
        else if (18.5 <= bmi && bmi < 24.9){
            System.out.println("normal weight");
            return 2;
        }
        else if (25 < bmi && bmi < 29.9){
            System.out.println("overweight");
            return 3;
        }
        else if (30 < bmi && bmi < 34.9){
            System.out.println("obese");
            return 4;
        }
        else if (35 < bmi && bmi < 39.9){
            System.out.println("very obese");
            return 5;
        }
        else{
            System.out.println("extremely obese");
            return 6;
        }
    }
}