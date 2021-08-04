import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
	
	public static Connection conexion;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scn = new Scanner(System.in);
		
		String db;
		String query;
		int num; 
		int i;
		
		System.out.println("Introduce el nombre de la base de datos");
		db = scn.next();
		crearBD(db);
		System.out.println("Numero de tablas a crear?");
		num = scn.nextInt();
		String[] tablas = new String[num]; 
		
		for (i = 0; i < num; i++) {
			System.out.println("Introduce el nombre de la tabla " + (i+1));
			tablas[i] = scn.next();
			scn.nextLine(); 
			query = "CREATE TABLE " + tablas[i];
			System.out.println("Escribe la query");
			query += scn.nextLine();
			crearTabla(db, query);
		}
		
		for (i = 0; i < tablas.length; i++) {
			query = "Insert into " + tablas[i];
			System.out.println("Escribe la query a insertar para los datos de la tabla" + tablas[i]);
			query += scn.nextLine();
			insertarDatos(db, query);
		}
		
		scn.close();
		
	}

	public static void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.126:3306?userTimezone=true&serverTimezone=UTC","remote","Rsantos?1");
			System.out.println("Connected!");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Error 404");
			System.out.println(e);
		}
		
	}

	public static void closeConnection() {
		try {
			conexion.close();
			System.out.println("Disconnected!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void crearBD(String nombre) {
		Connect();
		try {
			String Query = "Create Database " + nombre;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("La base de datos " + nombre + " creada");
			closeConnection();
		} catch(SQLException e) {
			System.out.println("Error");
			System.out.println(e);
		}
	}
	
	public static void crearTabla(String db, String Query) {
		Connect();
		try {
			String QueryDB = "USE " + db;
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(QueryDB);
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada");
			
		} catch (SQLException e) {
			System.out.println("Error");
			System.out.println(e);
		}
	}

	public static void insertarDatos (String db, String Query) {
		Connect();
		try {
			String QueryDB = "USE " + db;
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(QueryDB);
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Datos insertados");
			
		} catch (SQLException e) {
			System.out.println("Error");
			System.out.println(e);
		}
	}
}