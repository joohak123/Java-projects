import java.util.Scanner;

/**
 * <dd><code>HiringSystem</code> is a main menu to navigate through to get
 * access to
 * <dd><code>HiringTable</code> for the
 * <dd><code>Applicant</code>
 * 
 * @author Joohak Lee 108699180 Recitation 1
 *
 */
public class HiringSystem {
	public static HiringTable table = new HiringTable();
	public static HiringTable backUpTable = new HiringTable();

	/**
	 * main driver for this class.
	 * 
	 * @param args main arguments to run.
	 * 
	 * @throws NegativeGPAException thrown when the GPA entered is negative.
	 */
	public static void main(String[] args) throws NegativeGPAException {
		Scanner input = new Scanner(System.in);
		boolean quit = false;
		while (quit == false) {
			System.out.println(String.format("%-6s%s", "(A)", "Add Applicant"));
			System.out.println(String.format("%-6s%s", "(R)", "Remove Applicant"));
			System.out.println(String.format("%-6s%s", "(G)", "Get Applicant"));
			System.out.println(String.format("%-6s%s", "(P)", "Print List"));
			System.out.println(String.format("%-6s%s", "(RS)", "Refine Search"));
			System.out.println(String.format("%-6s%s", "(S)", "Size"));
			System.out.println(String.format("%-6s%s", "(B)", "Backup"));
			System.out.println(String.format("%-6s%s", "(CB)", "Compare Backup"));
			System.out.println(String.format("%-6s%s", "(RB)", "Revert Backup"));
			System.out.println(String.format("%-6s%s", "(Q)", "Quit"));
			System.out.println("\n" + "Please Enter a command:");
			String command = input.nextLine();
			command = command.toUpperCase();
			switch (command) {
			case "A":
				addApplicant();
				command = "";
				break;
			case "R":
				removeApplicant();
				command = "";
				break;
			case "G":
				getApplicant();
				command = "";
				break;
			case "P":
				printList();
				command = "";
				break;
			case "RS":
				refineSearch();
				command = "";
				break;
			case "S":
				size();
				command = "";
				break;
			case "B":
				backUp();
				command = "";
				break;
			case "CB":
				compareBackUp();
				command = "";
				break;
			case "RB":
				revertBackUp();
				command = "";
				break;
			case "Q":
				System.out.println("Quitting program....");
				quit = true;
				break;
			default:
				System.out.println("Incorrect input" + "\n");
			}
			System.out.println();
		}
	}

	/**
	 * adds the applicant with the typed informations, uses
	 * <dd><code>addApplicant()</code> from the
	 * <dd><code>HiringTable</code>.
	 * 
	 */
	public static void addApplicant() {
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter Applicant Name: ");
			String name = input.nextLine();
			System.out.println("Enter Applicant GPA: ");
			String gpa = input.nextLine();
			int GPA = Integer.parseInt(gpa);
			System.out.println("Enter Applicant College: ");
			String college = input.nextLine();
			String[] company = new String[table.MAX_COMPANIES], skill = new String[table.MAX_SKILLS];
			int a = 0;
			for (int i = table.MAX_COMPANIES; i > 0; i--, a++) {
				System.out.println("Enter up to " + i + " Companies: ");
				String currentCompany = input.nextLine();
				if (currentCompany.equals("")) {
					String[] test = new String[a];
					for (int j = 0; j < a; j++) {
						test[j] = company[j];
					}
					company = test;
					break;
				}
				company[a] = currentCompany;
			}
			int b = 0;
			for (int i = table.MAX_SKILLS; i > 0; i--, b++) {
				System.out.println("Enter up to " + i + " Skills: ");
				String currentSkills = input.nextLine();
				if (currentSkills.equals("")) {
					String[] test = new String[b];
					for (int j = 0; j < b; j++) {
						test[j] = skill[j];
					}
					skill = test;
					break;
				}
				skill[b] = currentSkills;
			}
			Applicant newApplicant;
			try {
				newApplicant = new Applicant(company, name, GPA, college, skill);
				table.addApplicant(newApplicant);
				System.out.println("Applicant " + name + " has been successfully added to the hiring system");
			} catch (FullTableException e) {
				System.out.println("reached maximum number of applicants");
				System.out.println("returning to the main menu");
			} catch (NegativeGPAException e1) {
				System.out.println("Negative GPA, can't create the applicant");
				System.out.println("returning to the main menu");
			}
		} catch (Exception e) {
			System.out.println("Invalid Input");
		}
	}

	/**
	 * removes the applicant with the same name,
	 * <dd><code>removeApplicant()</code> from the
	 * <dd><code>HiringTable</code>.
	 */
	public static void removeApplicant() {
		Scanner input = new Scanner(System.in);
		String removalName = "";
		System.out.println("Enter applicant name: ");
		removalName = input.nextLine();
		try {
			table.removeApplicant(removalName);
			System.out.println("Applicant " + removalName + " has been successfully removed from the hiring system.");
		} catch (ApplicantNotFoundException e) {
			System.out.println("Applicant Not Found" + "\n" + "returning to the main menu");
		}
	}

	/**
	 * retrieves the select Applicant's informations with the same name, uses
	 * <dd><code>getApplicant()</code> from the
	 * <dd><code>HiringTable</code>.
	 */
	public static void getApplicant() {
		Scanner input = new Scanner(System.in);
		String getName = "";
		System.out.println("Enter Applicant Name:");
		getName = input.nextLine();
		try {
			Applicant applicant = table.getApplicant(getName);
			System.out.println("Applicant Name: " + applicant.getApplicantName());
			String company = "Applicant Applying From: ";
			for (String item : applicant.getCompanyName()) {
				company += item + ", ";
			}
			System.out.println(company.substring(0, company.length() - 2));
			System.out.println("Applicant GPA: " + applicant.getApplicantGPA());
			System.out.println("Applicant College: " + applicant.getApplicantCollege());
			String skill = "Applicant Skills: ";
			for (String item : applicant.getApplicantSkills()) {
				skill += item + ", ";
			}
			System.out.println(skill.substring(0, skill.length() - 2));
		} catch (ApplicantNotFoundException e) {
			System.out.println("Applicant Not Found" + "\n" + "returning to the main menu");
		}
	}

	/**
	 * prints out the list of the applicants in the list, uses
	 * <dd><code>printApplicantTable()</code> from the
	 * <dd><code>HiringTable</code>.
	 */
	public static void printList() {
		table.printApplicantTable();
	}

	/**
	 * search the list for the applicants with the select informations,uses
	 * <dd><code>refineSearch()</code> from the
	 * <dd><code>HiringTable</code>.
	 */
	public static void refineSearch() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a company to filter for: ");
		String company = input.nextLine();
		System.out.println("Enter a skill to filter for: ");
		String skill = input.nextLine();
		System.out.println("Enter a college to filter for: ");
		String college = input.nextLine();
		System.out.println("Enter the minimum GPA to filter for: ");
		String gpa = input.nextLine();
		Double GPA = 0.0;
		try {
			GPA = Double.parseDouble(gpa);
		} catch (Exception ex) {
			GPA = 0.0;
		}
		table.refineSearch(table, company, skill, college, GPA);
	}

	/**
	 * prints out the number of applicants in the list, uses
	 * <dd><code>.size()</code> from
	 * <dd><code>HiringTable</code>.
	 */
	public static void size() {
		System.out.println("There are " + table.size() + " applicants in the hiring system.");
	}

	/**
	 * backs up the current list of applicants by creating a clone, uses
	 * <dd><code>.clone()</code> from
	 * <dd><code>HiringTable</code>.
	 */
	public static void backUp() {
		backUpTable = (HiringTable) table.clone();
		System.out.println("Successfully created backup.");
	}

	/**
	 * compare the current list of the applicants with the other list of the
	 * applicants(clone), uses
	 * <dd><code>.equals()</code> from the
	 * <dd><code>HiringTable</code>.
	 */
	public static void compareBackUp() {
		if (backUpTable.equals(table)) {
			System.out.println("Current list is the same as the backup copy.");
		} else {
			System.out.println("Current list is not the same as the backup copy.");
		}
	}

	/**
	 * revert the current list of the applicants to the cloned applicants.
	 */
	public static void revertBackUp() {
		table = backUpTable;
		System.out.println("Successfully reverted to the backup copy.");
	}
}
