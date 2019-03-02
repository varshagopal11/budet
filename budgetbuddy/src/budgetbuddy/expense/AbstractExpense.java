package budgetbuddy.expense;

public class AbstractExpense {

	double amount;

	double recPercent;

	/**
	 * Constructor that declares all fields
	 */
	public AbstractExpense(double amount) {
		setAmount(amount);
		setRecPercent(10);
	}

	/**
	 * Calls upper constructor
	 * @param params
	 */
	public AbstractExpense(double amount, double recPercent) {
		setAmount(amount);
		setRecPercent(recPercent);
	}

	public double getAmount() {
		return amount;
	}

	public double getRecPercent() {
		return recPercent;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setRecPercent(double recPercent) {
		this.recPercent = recPercent;
	}
}
