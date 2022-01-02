import java.io.*;

class Bill
{
    String cusName, cusNo;
    String[] cusProd = new String[10];
    int total;
    int[] prodPrice = new int[10];

    Bill(int[] price, String[] pname)
    {
        cusName = "Anonymous";
        cusNo = null;
        prodPrice = price;
        cusProd = pname;
    }

    Bill(String n, String no, int[] price, String[] pname)
    {
        cusName = n;
        cusNo = no;
        prodPrice = price;
        cusProd = pname;
    }

    void calculate()
    {
        total = 0;
        for(int i = 0; i < prodPrice.length; i++)
            total += prodPrice[i];
    }

    void showBill()
    {
        System.out.println("Name : " + cusName + "\t\tMobile NO : " + cusNo);
        System.out.println("Products\t\t\t\tPrice");
        for(int i = 0; i < prodPrice.length; i++)
            System.out.println(cusProd[i] + "\t\t\t\t\t" + prodPrice[i]);
        System.out.println("\t\tTotal = " + total + "\n\n");
    }
}

class constructorOverload_4b
{
    public static void main(String[] args) 
    {
        String tmpname[] = {"Biscuit", "Soap", "Detergent"};
        int tmparr[] = {30, 50, 70};
        Bill b1 = new Bill(tmparr, tmpname);           
        b1.calculate();
        b1.showBill();
        
        int tmparr1[] = {50, 90, 100};
        String tmpname1[] = {"Biscuits", "Drinks", "Bread"};
        Bill b2 = new Bill("Hari", "789063929", tmparr1, tmpname1);
        b2.calculate();
        b2.showBill();
    }
}
