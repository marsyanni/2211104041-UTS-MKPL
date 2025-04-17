package lib;

public class TaxFunction {

	public static int calculateTax(EmployeeIncomeDetail detail) {
		validateInput(detail.numberOfMonthWorking);

		int nonTaxableIncome = calculateNonTaxableIncome(detail.isMarried, detail.numberOfChildren);
		int taxableIncome = calculateTaxableIncome(detail, nonTaxableIncome);

		return Math.max(taxableIncome, 0);
	}

	private static void validateInput(int numberOfMonthWorking) {
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
	}

	private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}

		int nonTaxableIncome = 54000000;
		if (isMarried) {
			nonTaxableIncome += 4500000;
		}
		nonTaxableIncome += numberOfChildren * 1500000;

		return nonTaxableIncome;
	}

	private static int calculateTaxableIncome(EmployeeIncomeDetail detail, int nonTaxableIncome) {
		double annualIncome = (detail.monthlySalary + detail.otherMonthlyIncome) * detail.numberOfMonthWorking;
		double taxableIncome = 0.05 * (annualIncome - detail.deductible - nonTaxableIncome);
		return (int) Math.round(taxableIncome);
	}
}
