import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Database
{
    //String[] customerName = {"Harish", "Amar"};
    List<String> customerName = new ArrayList<>(Arrays.asList("Amar", "Harish"));
    //int[] visit = {15, 3};
    List<Integer> visit = new ArrayList<>(Arrays.asList(3, 15));

    int getNumVisit(String name) throws UserNotFoundException
    {     
        for(int i = 0; i < customerName.size(); i++)
        {
            if(customerName.get(i).equals(name))
            {
                int tmp = visit.get(i);
                visit.set(i, tmp++);
                return tmp;
            }
        }

        throw new UserNotFoundException(name);
    }

    void addCustomer(String name)
    {
        customerName.add(name);
        visit.add(1);
    }

    void inc(String name)
    {
        for(int i = 0; i < customerName.size(); i++)
        {
            if(name.equals(customerName.get(i)))
                visit.set(i, (visit.get(i))+1);
        }
    }
}

class UserNotFoundException extends Exception
{
    String name;
    UserNotFoundException(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "The user id " + name + " not found";
    }
}

class Product
{
    String name;
    int price;

    Product(String pName, int pPrice)
    {
        name = pName;
        price = pPrice;
    }
}

abstract class PersonType
{
    abstract void calculate();
    abstract void showBill();
}

class Person extends PersonType
{
    String name;
    String phno;
    Product prod[];
    int totalAmt;
  
    Person(String n, String ph, String[] prodName, int[] prodPrice)
    {
        prod = new Product[prodName.length];
        name = n;
        phno = ph;
        for(int i = 0; i < prodName.length; i++)
            prod[i] = new Product(prodName[i], prodPrice[i]);     
    }

    void calculate()
    {
        totalAmt = 0;
        for(int i = 0; i < prod.length; i++)
            totalAmt += prod[i].price;
    }
  
    void showBill()
    { 
        System.out.println("************************************************");
        System.out.println("Name : " + name + "\t\t\t" + "Mobile No : " + phno);
        System.out.println("------------------------------------------------");
        System.out.println("Products \t\t\t Price");
        System.out.println("************************************************");
        for(int i = 0; i < prod.length; i++)
            System.out.println(prod[i].name + "\t\t\t\t\t" + prod[i].price);
        System.out.println("------------------------------------------------");
        System.out.println("\t\t\t\t\t Total = " + totalAmt);
        System.out.println("------------------------------------------------\n");
    } 
}

class Customer extends Person
{
    String special; 
    
    Customer(String n, String ph, String[] prodName, int[] prodPrice)
    {
        super(n, ph, prodName, prodPrice);
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
    
    RegularCustomer(String n, String ph, String[] prodName, int[] prodPrice, int nVisit)
    {
        super(n, ph, prodName, prodPrice);
        numVisit = nVisit;
    }

    void genDiscount()
    {
        if(numVisit >= 3)
        {
            if(numVisit >= 25)
                disc = 30;
            else if(numVisit >= 15)
                disc = 20;
            else if(numVisit >= 3)
                disc = 10;
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


class ExceptionMain
{
    public static void main(String args[])
    {
        try (Scanner in = new Scanner(System.in)) 
        {
            Database db = new Database();           
            do{
                
                Customer c;
                String[] prodName = {"soap", "Shampoo", "Biscuit", "Oil"};
                int[] prodPrice = {40, 150, 150, 250};
                System.out.println("Your name: ");
                String name = in.next();
                       
                //if(db.getNumVisit(name) != 0)
                try
                {
                    c = new RegularCustomer(name, "8610405449", prodName, prodPrice, db.getNumVisit(name));        
                    db.inc(name);
                }
                catch(UserNotFoundException e)
                {
                //else
                    System.out.println(e);
                    System.out.println("Would you like to open a new User profile (Y/n): ");
                    String ch = in.next();
                    if(ch.equals("Y"))
                    {
                        db.addCustomer(name);                    
                    }
                    c = new Customer(name, "8610405449", prodName, prodPrice); 
            
                }
                c.bill(); 
                //Database tmpdb = new Database();
                for (int i : db.visit) 
                {
                    System.out.println(i);                    
                }
                System.out.println("Do u want to continue (Y/n): ");
            }while(in.next().equals("Y"));
        }
    }
}