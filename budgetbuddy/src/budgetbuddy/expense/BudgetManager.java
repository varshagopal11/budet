package budgetbuddy.expense;

public class BudgetManager {

	private static BudgetManager instance = new BudgetManager();

	private Income income;

	private Food food;

	private Gas gas;

	private Insurance insurance;

	private Rent rent;

	private Utilities utilities;

	public BudgetManager() {
		this(null, null, null, null, null, null);
	}

	public BudgetManager(Income i, Food f, Gas g, Insurance ins, Rent r, Utilities u) {
		income = i;
		food = f;
		gas = g;
		insurance = ins;
		rent = r;
		utilities = u;
	}
	
	public boolean isOver(AbstractExpense e) {
		if (e instanceof Food) {
			
		}
		return false;
	}

	public static BudgetManager getInstance() {
		if (instance == null) {
			instance = new BudgetManager();
		}
		return instance;
	}
	
	

}
