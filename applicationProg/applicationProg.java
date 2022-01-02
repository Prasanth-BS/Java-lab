import java.io.*;
import java.util.*;

class Bill
{
    String cusName, cusNo;
    String[] cusProd = new String[10];
    int total, noof;
    int[] prodPrice = new int[10];
    Scanner in = new Scanner(System.in);
    
    void getDetails()
    {
        System.out.println("Enter the following details");
        System.out.println("Customer Name:");
        cusName = in.nextLine();
        System.out.println("Mobile No");
        cusNo = in.nextLine();
        System.out.println("Enter the no of Products");
        noof = in.nextInt();
        System.out.println("Enter the product name and price ");
        for(int i = 0; i < noof; i++)
        {
            cusProd[i] = in.next();
            prodPrice[i] = in.nextInt();
        }
        
    }

    void calculate()
    {
        total = 0;
        for(int i = 0; i < noof; i++)
            total += prodPrice[i];
    }

    void showBill()
    {
        System.out.println("Name : " + cusName + "\t\tMobile NO : " + cusNo);
        System.out.println("Products\t\t\t\tPrice");
        for(int i = 0; i < noof; i++)
            System.out.println(cusProd[i] + "\t\t\t\t\t" + prodPrice[i]);
        System.out.println("\t\tTotal = " + total);
    }
}

class applicationProg
{
    public static void main(String[] args) 
    {
        Bill b = new Bill();
        b.getDetails();
        b.calculate();
        b.showBill();
        b.calculate();
        
    }
}
