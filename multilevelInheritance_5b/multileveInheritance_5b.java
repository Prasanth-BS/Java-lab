import java.io.*;

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
  
  void showBill(String spl)
  { 
    System.out.println("\t\t\t\t\t" + spl + " Invoice");
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
    /*name = n;
    phno = ph;
    productName = pName;
    productPrice = pPrice;*/
    special = "Customer";
  }
  
  void bill()
  {
    calculate();
    showBill(special);
  }
}

class Supplier extends Person
{
  String special;
  Supplier(String n, String ph, String[] pName, int[] pPrice)
  {
    super(n, ph, pName, pPrice);
    special = "Supplier";
  }
  
  void bill()
  {
    calculate();
    showBill(special); 
    System.out.println("The shopkeeper has to give this " + totalAmt + " to the supplier.");
  } 

}


class multilevelInheritance_5b
{
  public static void main(String args[])
  {
    String prodNames[] = {"Soap", "Shampoo", "Biscuit", "Oil", "Detergent", "Vegetables"};
    int prodPrice[] = {40, 150, 35, 250, 500, 600};
    Customer c = new Customer("Harish", "8610405449", prodNames, prodPrice); 
    c.bill();

    Supplier s = new Supplier("Aakash", "9150793879", prodNames, prodPrice);
    s.bill();
    
  }
}
