package budgetbuddy.expense;

public class BudgetManager {

	private static BudgetManager instance = new BudgetManager();

	private Income income;

	private Food food;

	private Gas gas;

	private Insurance insurance;

	private Rent rent;

	private Utilities utilities;

	
	
	public BudgetManager(Income i, Food f, Gas g, Insurance ins, Rent r, Utilities u) {
		income = i;
		food = f;
		gas = g;
		insurance = ins;
		rent = r;
		utilities = u;
	}

	public static BudgetManager getInstance() {
		if (instance == null) {
			instance = new BudgetManager(null, null, null, null, null, null);
		}
		return instance;
	}
	
	public String isOver() {
		String s = "";
		if (gas.isOver() ) {
			s += gas.toString();
		}
		if (food.isOver() ) {
			s += food.toString();
		}
		if (insurance.isOver() ) {
			s += insurance.toString();
		}
		if (rent.isOver()) {
			s += rent.toString();
		}
		if (utilities.isOver()) {
			s += utilities.toString();
		}
		return s;
	}

}
