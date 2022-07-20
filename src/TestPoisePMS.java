import java.sql.SQLException;
import java.util.Scanner;

public class TestPoisePMS {
public static void main (String args[]) throws SQLException {
	Scanner input = new Scanner(System.in);
	PoisePMS poised = new PoisePMS();
	char option = 'X';
	poised.getConnection();
	poised.statement= poised.conn.createStatement();
	
	do {
	//Display options
	System.out.println("Select an option \n" + "1. Create a new project \n" + "2. Update a Project \n"
			+ "3. Update Project Status \n" + "4. Print past due projects \n" + "5. Find a project \n"
			+ "6. Print All Projects \n" + "0. Exit");
	
	option = input.next().charAt(0);
	
	//Calling the appropriate function regarding the option selected by the user
	switch (option) {
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
		poised.printAllprojects();
		break;
	case '0':
		break;
	default:
		System.out.print("The option you have selected is not valid, please select a valid option");
	}
	//stop the loop when the user selects enter 0
} while (option != '0');

	input.close();
	poised.closeConnection();
	System.out.print("Session Finished!");
	
}
}

