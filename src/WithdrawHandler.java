import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class WithdrawHandler {

    private final Map<Bill, Integer> moneyLeft;
    private final int requestedAmount;

    public WithdrawHandler(Map<Bill, Integer> moneyInAtm, int withdrawAmount) {
        this.moneyLeft = new ConcurrentHashMap<>(moneyInAtm);
        this.requestedAmount = withdrawAmount;
    }


    public boolean attemptWithdraw() {
        AtomicInteger withdrawAmount = new AtomicInteger(requestedAmount);

        for(Bill bills : moneyLeft.keySet()) {

            if(moneyLeft.containsKey(bills)) {

                int amountOfBills = withdrawAmount.get() / bills.value;

                if(amountOfBills > 0) {
                    for (int i = 0; i < amountOfBills && i < moneyLeft.get(bills); i++) {
                        withdrawAmount.set(withdrawAmount.get() - bills.value);
                    }

                    if(moneyLeft.get(bills) - amountOfBills == 0) {
                        moneyLeft.remove(bills);
                    }
                    else {
                        moneyLeft.compute(bills, (k, v) -> v - amountOfBills);
                    }

                }

            }
        }

        return withdrawAmount.get() == 0;

    }

    public Map<Bill, Integer> getMoneyLeft() {
        return moneyLeft;
    }
}
