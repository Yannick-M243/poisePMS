import java.util.Scanner;

public class Customer {
	// Customer attributes
	Scanner input = new Scanner(System.in);
	int projectNum;
	String name;
	String email;
	String address;
	String tel;

	// Method to get customer details
	public void getCustomerDetails(int projectID) {
		System.out.println("--------PROJECT CUSTOMER DETAILS--------");
		System.out.print("Name : ");
		name = input.nextLine();
		System.out.print("Email : ");
		email = input.nextLine();
		System.out.print("Addesse : ");
		address = input.nextLine();
		System.out.print("Tel : ");
		tel = input.next();
		input.nextLine();

		projectNum = projectID;
	}

}
