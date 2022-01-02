import java.io.*;

class Bill
{
    String cusName, cusNo;
    String[] cusProd = new String[10];
    int total = 0;
    int discount = 0;
    int discountedTotal = 0;
    int[] prodPrice = new int[10];

    Bill(String n, String no, int[] price, String[] pname)
    {
        cusName = n;
        cusNo = no;
        prodPrice = price;
        cusProd = pname;
    }

    void calculate(int disc)
    {
        for(int i = 0; i < prodPrice.length; i++)
            total += prodPrice[i];

        discount = (total/100) * disc;            
        discountedTotal = total - discount;
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
        
        System.out.println("\t\tTotal            = " + total);
        System.out.println("\t\tDiscount         = " + discount);
        System.out.println("\t\tDiscounted Total = " + discountedTotal + "\n\n");
    }

}

class methodOverloading_4a
{
    public static void main(String[] args) 
    {
        int tmparr[] = {30, 50, 70};
        String tmpname[] = {"Biscuits", "Drinks", "Bread"};
        Bill b1 = new Bill("Prasanth", "8610605449", tmparr, tmpname);
        Bill b2 = new Bill("Prasanth", "8610605449", tmparr, tmpname);

        b1.calculate();
        System.out.println("Invoice for normal customer");
        b1.showBill();
        
        b2.calculate(40);
        System.out.println("Invoice for Regular customer with discount");
        b2.showBill();
    }
}
