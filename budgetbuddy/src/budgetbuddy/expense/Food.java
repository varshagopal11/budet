package budgetbuddy.expense;

public class Food extends AbstractExpense {

	public static final double REC_PERCENT = 15;
	
	public Food(double amount) {
		super(amount, REC_PERCENT);
	}
}
