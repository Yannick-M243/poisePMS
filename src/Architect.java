import java.util.Scanner;

public class Architect {
Scanner input = new Scanner(System.in);
int projectNum;
String name;
String email;
String address;
String tel;

public void getArchitectDetails(int projectID) {
	
	System.out.println("--------PROJECT ARHITECT DETAILS--------");
	System.out.print("Name : ");
	name = input.nextLine();
	System.out.print("Email : ");
	email = input.nextLine();
	System.out.print("Addesse : ");
	address = input.nextLine();
	System.out.print("Tel : ");
	tel = input.next();
	
	
	projectNum = projectID;
}

public int getProjectNum() {
	return projectNum;
}

public void setProjectNum(int projectNum) {
	this.projectNum = projectNum;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}

}
