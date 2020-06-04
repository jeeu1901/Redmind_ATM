public class Main {
    public static void main(String[] args) {

        ATM atm = new ATM();

        atm.addBills(Bill.ONE_HUNDRED, 5);
        atm.addBills(Bill.FIVE_HUNDRED, 3);
        atm.addBills(Bill.THOUSAND, 2);

        atm.logAtm();
        System.out.println("-----------------------------------");

        atm.withdraw(1500);
        atm.withdraw(700);
        atm.withdraw(400);
        atm.withdraw(1100);
        atm.withdraw(1000);
        atm.withdraw(700);
        atm.withdraw(300);

        System.out.println("-----------------------------------");
        atm.logAtm();

    }
}
