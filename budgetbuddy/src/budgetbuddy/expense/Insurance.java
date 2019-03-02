package budgetbuddy.expense;

public class Insurance extends AbstractExpense {
	
	public static final double REC_PERCENT = 6;

	public Insurance(double amount) {
		super(amount, REC_PERCENT);
	}
}
