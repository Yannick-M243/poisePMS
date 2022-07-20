import java.sql.SQLException;

public class TestPoisePMS {
public static void main (String args[]) throws SQLException {
	PoisePMS poised = new PoisePMS();
	
	poised.getConnection();
	poised.statement= poised.conn.createStatement();
	
	poised.printAllprojects();
	
	poised.createProject();
}
}

