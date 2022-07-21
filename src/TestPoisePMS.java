import java.sql.SQLException;
import java.util.Scanner;

public class TestPoisePMS {
	public static void main(String args[]) throws SQLException {
		// Initialising variables
		Scanner input = new Scanner(System.in);
		PoisePMS poised = new PoisePMS();
		char option = 'x';
		poised.getConnection();
		poised.statement = poised.conn.createStatement();

		do {
			// Display options
			System.out.println("Select an option \n" + "P. Print All Projects \n" + "M. Print All Managers \n"
					+ "C. Print all Customers \n" + "A. Print all Architects \n" + "1. Create a new project \n"
					+ "2. Update a Project \n" + "3. Update Project Status \n" + "4. Print past due projects \n"
					+ "5. Find a project \n" + "6. Pay for a project \n" + "7. Print Unfinalised Projects \n"
					+ "8. Delete a Project \n" + "0. Exit");

			// getting the user option
			option = input.next().charAt(0);

			// Calling the appropriate function regarding the option selected by the user
			switch (Character.toUpperCase(option)) {
			case 'P':
				poised.printAllprojects();
				break;
			case 'M':
				poised.printAllManagers();
				break;
			case 'C':
				poised.printAllCustomers();
				break;
			case 'A':
				poised.printAllArchitects();
				break;
			case '1':
				poised.createProject();
				break;
			case '2':
				poised.updateProject();
				break;
			case '3':
				poised.setProjectStatus();
				break;
			case '4':
				poised.printPastDueProjects();
				break;
			case '5':
				poised.searchProject();
				break;
			case '6':
				poised.pay4Project();
				break;
			case '7':
				poised.printUnfinishedProjects();
				break;
			case '8':
				poised.deleteProject();
				break;
			case '0':
				break;
			default:
				System.out.print("The option you have selected is not valid, please select a valid option");
			}
			// stop the loop when the user selects enter 0
		} while (option != '0');

		input.close();
		// Closing Connect with the database
		poised.closeConnection();
		System.out.print("Session Finished!");

	}
}
