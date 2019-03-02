package budgetbuddy.expense;

public class Gas extends AbstractExpense {
	
public static final double REC_PERCENT = 6;
	


	public Gas(double amount) {
		super(amount, REC_PERCENT);
	}

}
