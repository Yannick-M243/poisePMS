import java.sql.Date;
import java.util.Scanner;

public class Project {
	// Project attributes
	Scanner input = new Scanner(System.in);
	String name;
	String buildingType;
	String buildingAddress;
	String erfNum;
	int price;
	Date deadline;
	String status;
	Date completionDate;
	int amountPaid;

//Method to get Project details
	public void getProjectDetails() {
		String date;
		System.out.println("--------PROJECT DETAILS--------");
		System.out.print("Name : ");
		name = input.nextLine();
		System.out.print("Type of building : ");
		buildingType = input.nextLine();
		System.out.print("Building address : ");
		buildingAddress = input.nextLine();
		System.out.print("ERF number : ");
		erfNum = input.next();
		input.nextLine();

		System.out.print("Price : ");
		price = input.nextInt();
		input.nextLine();

		System.out.print("Due date(yyyy-mm-dd) : ");
		date = input.next();
		deadline = Date.valueOf(date);
		input.nextLine();

		status = "Pending";
		amountPaid = 0;
	}
}
