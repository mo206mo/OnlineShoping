package ViewProject;

import BAOProject.*;
import DTOProject.Customers;
import DTOProject.Product;
import java.util.Scanner;

public class CustomersView {
    boolean getOut = false;
    static boolean pass = false;
    public static boolean onlyAlphabets(String str, int n)
    {
        if (str == null || str == "") {
            return false;
        }
        for (int i = 0; i < n; i++) {

            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean onlyDigits(String num,int n){
        if (num == null || num == "") {
            return false;
        }
        for (int i = 0; i < n; i++){

            if(!Character.isDigit(num.charAt(i))&&n!=11){
                return false;
            }
        }
        return true;
    }

    private static WarehouseInter business = new BaoFactoryPro().getClassBao();


    public static void main(String args[]){

        InterCart obj = new BaoFactoryPro().getClassCart();


        Scanner in = new Scanner(System.in);
        CustomersView cv1 = new CustomersView();
        Customers c1 = new Customers();
        //Product product = new Product();
        Sales s1 = new Sales();
        //Cart myCart = new Cart();


        while(true){
            System.out.println("please enter your name : ");
            String name=in.next();
            int n=name.length();
            if(onlyAlphabets(name,n)==true){
                c1.setName(name);
                break;
            }
            else
                System.out.println("please enter valid name(Only characters)");
        }
        while(true) {
            System.out.println("please enter your phone number :");
            String phoneNumber = in.next();
            int n = phoneNumber.length();
            if(onlyDigits(phoneNumber,n)==true && n == 11){
                c1.setPhone(phoneNumber);
                break;
            }
            else
                System.out.println("Please enter valid phone number");
        }
        c1 = new Customers(c1.getName(), c1.getPhone());
        while (!cv1.getOut){
            business.viewDepartments();
            System.out.println("What department do you want to buy from it? (please enter number) ");
            departmentsEnum d = business.getDepartment(in.nextInt());
            business.viewProducts(d);
            System.out.println("which product do you want to put into the cart ? (enter it's id) ");
            int any = in.nextInt();

            System.out.println("the amount you want ?");
            Number amo = in.nextInt();
            obj.addProduct(business.getProduct(any , d), amo);
            pass = false;
            System.out.println("the product is added successfully");
            System.out.println("do you want to add more products ? (Y/N)");
            if (in.next().toLowerCase().equals("y"))
                continue;
            else
                cv1.getOut = true;

            System.out.println("your total is "+obj.getTotal());
            System.out.println("are you sure to pay ? (Y/N)");

            if(in.next().toLowerCase().equals("y")) {
                s1.makePayment(c1, (Cart) obj);
                System.out.println("Payment completed successfully , Good luck");
            }
            else{
                obj.removeCart();
                System.out.println("Not paid\n");
            }
        }
        cv1.getOut = false;
    }
}
