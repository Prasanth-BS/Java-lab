import java.io.*;
import java.util.Scanner;

class Database
{
    int getNumVisit(String name)
    {
        if(name.equals("Harish"))
            return 15;
        else if(name.equals("Amar"))
            return 3;
        else
            return 0;
    }
}

class Person
{
    String name;
    String phno;
    int[] productPrice = new int[10];
    String[] productName = new String[10];
    int totalAmt;
  
    Person(String n, String ph, String[] pName, int[] pPrice)
    {
        name = n;
        phno = ph;
        productName = pName;
        productPrice = pPrice;
    }

    void calculate()
    {
        totalAmt = 0;
        for(int i = 0; i < productPrice.length; i++)
            totalAmt += productPrice[i];
    }
  
    void showBill()
    { 
        System.out.println("************************************************");
        System.out.println("Name : " + name + "\t\t\t" + "Mobile No : " + phno);
        System.out.println("------------------------------------------------");
        System.out.println("Products \t\t\t Price");
        System.out.println("************************************************");
        for(int i = 0; i < productPrice.length; i++)
            System.out.println(productName[i] + "\t\t\t\t\t" + productPrice[i]);
        System.out.println("------------------------------------------------");
        System.out.println("\t\t\t\t\t Total = " + totalAmt);
        System.out.println("------------------------------------------------\n");
    } 
}

class Customer extends Person
{
    String special; 
    Customer(String n, String ph, String[] pName, int[] pPrice)
    {
        super(n, ph, pName, pPrice);
        special = "Customer";
    }
  
    void bill()
    {
        System.out.println("\t\t\t\t\t" + special + " Invoice");
        calculate();
        showBill();
    }
}

class RegularCustomer extends Customer
{
    int numVisit, disc, discount, discountedVal;
    
    RegularCustomer(String n, String ph, String[] pName, int[] pPrice, int nVisit)
    {
        super(n, ph, pName, pPrice);
        numVisit = nVisit;
    }

    void genDiscount()
    {
        if(numVisit >= 3)
        {
            if(numVisit >= 5)
                disc = 10;
            else if(numVisit >= 15)
                disc = 20;
            else if(numVisit >= 25)
                disc = 30;
            else
                disc = 40;
        }
        
    }

    void bill()
    {
        super.bill();
        genDiscount();
        discount = (totalAmt/100) * disc;
        discountedVal = totalAmt - discount;
        System.out.println("\t\t\t\t\t Discount = " + discount);
        System.out.println("\t\t\t\t\t Discounted amount = " + discountedVal);
    }

}


class runtimePolymorphism_4
{
    public static void main(String args[])
    {
        try (Scanner in = new Scanner(System.in)) {
            Database db = new Database();
            Customer c;
            String prodNames[] = {"Soap", "Shampoo", "Biscuit", "Oil", "Detergent", "Vegetables"};
            int prodPrice[] = {40, 150, 35, 250, 500, 600};
            System.out.println("Your name: ");
            String name = in.next();
            if(db.getNumVisit(name) != 0)
                c = new RegularCustomer(name, "8610405449", prodNames, prodPrice, db.getNumVisit(name));        
            else
                c = new Customer("name", "8610405449", prodNames, prodPrice); 
            c.bill();
        }
    }
}
