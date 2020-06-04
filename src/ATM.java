import java.util.HashMap;
import java.util.Map;

public class ATM {

    private Map<Bill, Integer> moneyInAtm;

    public ATM() {
        moneyInAtm = new HashMap<>();
    }

    public void addBills(Bill value, int amount) {
        moneyInAtm.put(value, amount);
    }

    public boolean verifyAtmBalance(int requestedAmount) {
        int totalLeft = moneyInAtm
                .entrySet()
                .stream()
                .mapToInt(bill -> bill.getKey().value * bill.getValue())
                .sum();

        if(requestedAmount > totalLeft || totalLeft == 0) {
            logResult(requestedAmount, false);
            return false;
        }


        return true;
    }

    public void withdraw(int withdrawAmount) {
        WithdrawHandler withdrawHandler = new WithdrawHandler(moneyInAtm, withdrawAmount);

        if(verifyAtmBalance(withdrawAmount)) {
           if(withdrawHandler.attemptWithdraw()) {
               moneyInAtm = withdrawHandler.getMoneyLeft();
               logResult(withdrawAmount, true);
           }
           else {
               logResult(withdrawAmount, false);
           }

        }

    }

    public void logResult(int withdrawAmount, boolean success) {
        if(success) {
            System.out.println("Sucess: the amount of " + withdrawAmount + " has been withdrawn");
        }
        else {
            System.out.println("Fail: the amount of " + withdrawAmount + " failed to be withdrawn");
        }
    }

    public void logAtm() {

        moneyInAtm.forEach((k, v) -> {
            System.out.println("Money in ATM: " + k  + " " + k.value + " "  + v);
        });

        int totalLeft = moneyInAtm
                .entrySet()
                .stream()
                .mapToInt(bill -> bill.getKey().value * bill.getValue())
                .sum();

        System.out.println("Balance: " + totalLeft);


    }

}
