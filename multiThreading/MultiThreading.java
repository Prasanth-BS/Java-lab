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

class Person
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

class MyThread implements Runnable
{
    Database db = new Database();
    Scanner in = new Scanner(System.in);
    Customer c;
    Thread t;
    MyThread()
    {
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run()
    {
        String[] prodName = {"soap", "Shampoo", "Biscuit", "Oil"};
        int[] prodPrice = {40, 150, 150, 250};
        System.out.println("Name : ");
        String name = in.next();
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
            System.out.println("Execution Interrupted");
        }
        if(db.getNumVisit(name) != 0)
            c = new RegularCustomer(name, "8610405449", prodName, prodPrice, db.getNumVisit(name));
        else
            c = new Customer(name, "8610405449", prodName, prodPrice);
    }

    Customer returnVal()
    {
        return c;
    }
}

class MultiThreading
{
    public static void main(String args[]) throws InterruptedException
    {
        Customer c;
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread1.t.join();
        c = thread1.returnVal();
        c.bill();
        thread2.t.join();
        c = thread2.returnVal();
        c.bill();
    }
}