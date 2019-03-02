package budgetbuddy.expense;

public class Rent extends AbstractExpense {

	public static final double REC_PERCENT = 30;
	
	public Rent(double amount) {
		super(amount, REC_PERCENT);
	}

		
}
