import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PoisePMS {
	// Creating new instances of Objects and declaring variables
	Scanner input = new Scanner(System.in);
	Project proj = new Project();
	Manager man = new Manager();
	Customer cust = new Customer();
	Architect arch = new Architect();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDateTime now = LocalDateTime.now();
	String currentDate = dtf.format(now);
	Connection conn;
	Statement statement;
	ResultSet results;
	int rowsAffected;

	// This is the method used to establish the connection with the database
	public void getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poise_db?useSSL=false", "otheruser",
					"swordfish");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// This is the method used to create new projects
	public void createProject() throws SQLException {
		int projectId = 0;
		String defaultName;

		// calling the method addProject to prompt users to enter new project details
		proj.getProjectDetails();

		// Executing a query to add values entered by the user to the corresponding
		// fields
		rowsAffected = statement.executeUpdate(
				"INSERT INTO projects (project_name,building_type,building_address,erf_num,project_price,due_date,status,completion_date,amount_paid) values ('"
						+ proj.name + "','" + proj.buildingType + "','" + proj.buildingAddress + "','" + proj.erfNum
						+ "','" + proj.price + "','" + proj.deadline + "','" + proj.status + "',NULL,'"
						+ proj.amountPaid + "')");
		System.out.println("Query complete, " + rowsAffected + " rows added in projects. \n");

		// getting the id of the project created to link it with the manager, customer
		// and architect tables
		results = statement.executeQuery("SELECT * FROM projects WHERE erf_num='" + proj.erfNum + "'");
		if (results.next()) {
			projectId = results.getInt("projectNo");
		}

		// calling the method addCustomer with the projectId as parameter to prompt the
		// user to enter customer details for this project
		cust.getCustomerDetails(projectId);
		rowsAffected = statement.executeUpdate(
				"INSERT INTO customers (customer_name,customer_email,customer_address,customer_tel,projectNo) values ('"
						+ cust.name + "','" + cust.email + "','" + cust.address + "','" + cust.tel + "','"
						+ cust.projectNum + "')");
		System.out.println("Query complete, " + rowsAffected + " rows added in customers. \n");

		// calling the method addManager with the projectId as parameter to prompt the
		// user to enter manager details for this project
		man.getManagerDetails(projectId);
		rowsAffected = statement.executeUpdate(
				"INSERT INTO managers (manager_name,manager_email,manager_address,manager_tel,projectNo) values ('"
						+ man.name + "','" + man.email + "','" + man.address + "','" + man.tel + "','" + man.projectNum
						+ "')");
		System.out.println("Query complete, " + rowsAffected + " rows added in managers. \n");

		// calling the method addArchitect with the projectId as parameter to prompt the
		// user to enter architect details for this project
		arch.getArchitectDetails(projectId);
		rowsAffected = statement.executeUpdate(
				"INSERT INTO architects (architect_name,architect_email,architect_address,architect_tel,projectNo) values ('"
						+ arch.name + "','" + arch.email + "','" + arch.address + "','" + arch.tel + "','"
						+ arch.projectNum + "')");
		System.out.println("Query complete, " + rowsAffected + " rows added in architects. \n");

		// If there is no project name entered, a default name using the building type
		// and the customer name will be used
		if (proj.name.equals(null) || proj.name.equals("")) {
			defaultName = proj.buildingType + " " + cust.name;

			// executing the query that will replace the empty name with a default name
			rowsAffected = statement.executeUpdate(
					"UPDATE projects SET project_name='" + defaultName + "' WHERE projectNo='" + projectId + "'");
			System.out.println("Query complete, " + rowsAffected + " rows updated in projects. \n");
		}

	}

	// This method update a selected project details
	public void updateProject() throws SQLException {
		// Selecting the project to update
		System.out.print("Enter the number of the project you want to update :");
		int selectedProject = input.nextInt();
		input.nextLine();

		char option = 'X';
		// Display options
		System.out.println("Select an option \n" + "1. Update project details \n" + "2. Update manager details \n"
				+ "3. Update architect details \n" + "4. Update customer details \n" + "0. Exit");

		option = input.next().charAt(0);

		// Calling the appropriate function regarding the option selected by the user
		switch (option) {
		case '1':
			//Getting new project details
			proj.getProjectDetails();
			rowsAffected = statement.executeUpdate("UPDATE projects SET project_name='" + proj.name
					+ "',building_type='" + proj.buildingType + "',building_address='" + proj.buildingAddress
					+ "',erf_num='" + proj.erfNum + "',project_price='" + proj.price + "',due_date='" + proj.deadline
					+ "', WHERE projectNo='" + selectedProject + "'");
			System.out.println("Query complete, " + rowsAffected + " rows updated in projects. \n");
			break;
		case '2':
			//Getting new manager details
			man.getManagerDetails(selectedProject);
			rowsAffected = statement.executeUpdate("UPDATE managers SET manager_name='" + man.name + "',manager_email='"
					+ man.email + "',manager_address='" + man.address + "',manager_tel='" + man.tel
					+ "' WHERE projectNo='" + selectedProject + "'");
			System.out.println("Query complete, " + rowsAffected + " rows updated in managers. \n");
			break;
		case '3':
			//Getting new architect details
			arch.getArchitectDetails(selectedProject);
			rowsAffected = statement.executeUpdate("UPDATE architects SET architect_name='" + arch.name
					+ "',architect_email='" + arch.email + "',architect_address='" + arch.address + "',architect_tel='"
					+ arch.tel + "' WHERE projectNo='" + selectedProject + "'");
			System.out.println("Query complete, " + rowsAffected + " rows updated in architects. \n");
			break;
		case '4':
			//Getting new customer details
			cust.getCustomerDetails(selectedProject);
			rowsAffected = statement.executeUpdate("UPDATE customers SET customer_name='" + cust.name
					+ "',customer_email='" + cust.email + "',customer_address='" + cust.address + "',customer_tel='"
					+ cust.tel + "' WHERE projectNo='" + selectedProject + "'");
			System.out.println("Query complete, " + rowsAffected + " rows updated in customers. \n");
			break;
		default:
			System.out.println("The option you have selected is not valid, please select a valid option");
		}
	}

	//Prints All Projects from the database
	public void printAllprojects() throws SQLException {
		System.out.println("	------------------------------ALL PROJECTS---------------------------------");
		results = statement.executeQuery("SELECT * FROM projects");
		while (results.next()) {
			System.out.println(results.getInt("projectNo") + ".   " + results.getString("project_name") + " - "
					+ results.getString("building_type") + " - " + results.getString("building_address") + " - "
					+ results.getString("erf_num") + " - " + results.getInt("project_price") + " - "
					+ results.getString("due_date") + " - " + results.getString("status") + " - "
					+ results.getString("completion_date") + " - " + results.getInt("amount_paid") + " - ");
		}
		System.out.println();
	}

	//This method changes the status of a selected project
	public void setProjectStatus() throws SQLException {
		// Selecting the project
		System.out.print("Enter the number of the project you want to change the status :");
		int selectedProject = input.nextInt();
		input.nextLine();

		char option = 'X';
		String status = "";

		// Display options
		System.out.println("Select an option \n" + "1. Pending  \n" + "2. In Progress \n" + "3. Almost finished \n"
				+ "4. Finalised \n" + "0. Exit");
		option = input.next().charAt(0);

		// Selecting the status according to the option selected
		switch (option) {
		case '1':
			status = "Pending";
			break;
		case '2':
			status = "In Progress";
			break;
		case '3':
			status = "Almost finished";
			break;
		case '4':
			status = "Finalised";
			break;
		default:
			System.out.print("The option you have selected is not valid, please select a valid option");
		}

		// If a project status is finalised, also add a completion date to it
		if (status.equals("Finalised")) {
			rowsAffected = statement.executeUpdate("UPDATE projects SET status='" + status + "',completion_date='"
					+ currentDate + "' WHERE projectNo='" + selectedProject + "'");
			System.out.println("Query complete, " + rowsAffected + " rows updated in projects. \n");
		} else {
			rowsAffected = statement.executeUpdate(
					"UPDATE projects SET status='" + status + "' WHERE projectNo='" + selectedProject + "'");
			System.out.println("Query complete, " + rowsAffected + " rows updated in projects. \n");
		}

		System.out.println("Project'status Successfully updated");

	}

	// This method closes the connections established with the database
	public void closeConnection() throws SQLException {
		results.close();
		statement.close();
		conn.close();
	}

	// This method only prints projects that have crossed the deadline
	public void printPastDueProjects() throws SQLException {
		System.out.println("	------------------------------PAST DUE PROJECTS---------------------------------");
		results = statement.executeQuery("SELECT * FROM projects WHERE due_date < '" + currentDate + "'");
		while (results.next()) {
			System.out.println(results.getInt("projectNo") + ".   " + results.getString("project_name") + " - "
					+ results.getString("building_type") + " - " + results.getString("building_address") + " - "
					+ results.getString("erf_num") + " - " + results.getInt("project_price") + " - "
					+ results.getString("due_date") + " - " + results.getString("status") + " - "
					+ results.getString("completion_date") + " - " + results.getInt("amount_paid") + " - ");
		}
		System.out.println();
	}

	// this method search a project in the database using its project number
	public void searchProject() throws SQLException {

		// Getting the project number to search
		System.out.print("Enter the number of the project you are looking for : ");
		int selectedProject = input.nextInt();
		input.nextLine();

		// executing the query that will return the details of the book we are looking
		// for
		results = statement.executeQuery(
				"SELECT project_name,building_type,building_address,erf_num,due_date,status FROM projects WHERE projectNo='"
						+ selectedProject + "'");
		if (results.next()) {
			System.out.print("Project Found : ");
			System.out.println(results.getString("project_name") + " - " + results.getString("building_type") + " - "
					+ results.getString("building_address") + " - " + results.getString("erf_num") + " - "
					+ results.getString("due_date") + " - " + results.getString("status") + " - ");
		} else {
			System.out.println("No project found with this project number \n");
		}
	}

	// This method add a value to amount paid
	public void pay4Project() throws SQLException {
		int price = 0;
		int currentAmount = 0;
		int amountToPay = 0;
		int total = 0;

		// Getting the project number to search
		System.out.print("Enter the number of the project you want to pay for : ");
		int selectedProject = input.nextInt();
		input.nextLine();

		// getting the project price and the current amount paid
		results = statement.executeQuery(
				"SELECT projectNo,project_price,amount_paid FROM projects WHERE projectNo='" + selectedProject + "'");
		if (results.next()) {
			price = results.getInt("project_price");
			currentAmount = results.getInt("amount_paid");
		}

		// getting the amount to pay
		System.out.print("Enter the amount to pay: ");
		amountToPay = input.nextInt();
		input.nextLine();

		// adding the new amount to the one existing in the database
		total = currentAmount + amountToPay;

		// Verify if the total paid does not exceed the project price
		if (total > price) {
			System.out.println("The total amount pay is over the project price");
		} else {
			rowsAffected = statement.executeUpdate(
					"UPDATE projects SET amount_paid='" + total + "' WHERE projectNo='" + selectedProject + "'");
			System.out.println("Query complete, " + rowsAffected + " rows updated in projects. \n");
			System.out.println("Amount Successfuly paid !");
		}
	}

	// This method deletes a project selected by the user
	public void deleteProject() throws SQLException {
		// Getting the project number to search
		System.out.print("Enter the number of the project you want to delete : ");
		int selectedProject = input.nextInt();
		input.nextLine();

		// executing the query to delete the project architect details
		rowsAffected = statement.executeUpdate("DELETE FROM architects WHERE projectNo='" + selectedProject + "'");
		System.out.println("Query complete, " + rowsAffected + " rows removed in architects. \n");

		// executing the query to delete the project manager details
		rowsAffected = statement.executeUpdate("DELETE FROM managers WHERE projectNo='" + selectedProject + "'");
		System.out.println("Query complete, " + rowsAffected + " rows removed in managers. \n");

		// executing the query to delete the project customer details
		rowsAffected = statement.executeUpdate("DELETE FROM customers WHERE projectNo='" + selectedProject + "'");
		System.out.println("Query complete, " + rowsAffected + " rows removed in customers. \n");

		// executing the query to delete the selected project from the database
		rowsAffected = statement.executeUpdate("DELETE FROM projects WHERE projectNo='" + selectedProject + "'");
		System.out.println("Query complete, " + rowsAffected + " rows removed in projects. \n");

		System.out.println("Project Successfully Deleted !");

	}

	// This method print project that are not finalised
	public void printUnfinishedProjects() throws SQLException {
		System.out.println("	------------------------------UNFINISHED PROJECTS---------------------------------");
		results = statement.executeQuery("SELECT * FROM projects WHERE status != 'Finalised'");
		while (results.next()) {
			System.out.println(results.getInt("projectNo") + ".   " + results.getString("project_name") + " - "
					+ results.getString("building_type") + " - " + results.getString("building_address") + " - "
					+ results.getString("erf_num") + " - " + results.getInt("project_price") + " - "
					+ results.getString("due_date") + " - " + results.getString("status") + " - "
					+ results.getString("completion_date") + " - " + results.getInt("amount_paid") + " - ");
		}
		System.out.println();
	}

	// Print All Customers
	public void printAllCustomers() throws SQLException {
		System.out.println("	------------------------------ALL CUSTOMERS---------------------------------");
		results = statement.executeQuery("SELECT * FROM customers");
		while (results.next()) {
			System.out.println(results.getInt("customerNo") + ".   " + results.getString("customer_name") + " - "
					+ results.getString("customer_email") + " - " + results.getString("customer_address") + " - "
					+ results.getString("customer_tel") + " - " + results.getInt("projectNo"));
		}
		System.out.println();
	}

	// Print All Managers
	public void printAllManagers() throws SQLException {
		System.out.println("	------------------------------ALL MANAGERS---------------------------------");
		results = statement.executeQuery("SELECT * FROM managers");
		while (results.next()) {
			System.out.println(results.getInt("managerNo") + ".   " + results.getString("manager_name") + " - "
					+ results.getString("manager_email") + " - " + results.getString("manager_address") + " - "
					+ results.getString("manager_tel") + " - " + results.getInt("projectNo"));
		}
		System.out.println();
	}

	// Print All architects
	public void printAllArchitects() throws SQLException {
		System.out.println("	------------------------------ALL ARCHITECTS---------------------------------");
		results = statement.executeQuery("SELECT * FROM architects");
		while (results.next()) {
			System.out.println(results.getInt("architectNo") + ".   " + results.getString("architect_name") + " - "
					+ results.getString("architect_email") + " - " + results.getString("architect_address") + " - "
					+ results.getString("architect_tel") + " - " + results.getInt("projectNo"));
		}
		System.out.println();
	}

}
