package dndBoard;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;


	public class SQLReader {
	    
	    static int AC;
	    static int HP;
	    static int Strength;
	    static int Dexterity;
	    static int Constitution;
	    static int Intelligence;
	    static int Wisdom;
	    static int Charisma;
	    static String Attack1;
	    static String Attack2;
	    
	    
		
		
		public static void searchSQL(String monsterName) {
			
			try {
				
		        String host = "jdbc:derby://localhost:1527/testData";
		        String uName = "shawn";
		        String uPass = "shawn";
		        Connection con = DriverManager.getConnection(host, uName, uPass);
		        
		        Statement stmt = con.createStatement();
		        
		        String SQL = "SELECT * FROM monsterData WHERE monsterName = '" + monsterName + "'";
		        
		        ResultSet rs = stmt.executeQuery(SQL);
		        
		        rs.next();


		        AC = rs.getInt("AC");
		        HP = rs.getInt("HP");
		        Strength = rs.getInt("Strength");
		        Dexterity = rs.getInt("Dexterity");
		        Constitution = rs.getInt("Constitution");
		        Intelligence = rs.getInt("Intelligence");
		        Wisdom = rs.getInt("Wisdom");
		        Charisma = rs.getInt("Charisma");
		        Attack1 = rs.getString("Attack1");
		        Attack2 = rs.getString("Attack2");
		        	        
		        
		        System.out.println("pulling..."); }
		        
		        catch(SQLException err) {
		            
		            System.out.println(err.getMessage());
		            
		        }
			
		}
		
		
		public static int getHP() {
			
			return HP;
			
		}
		
		public static int getAC() {
			
			return AC;
			
		}
		
		public static int getStr() {
			
			return (int) Math.floor((Strength - 10.0)/2.0);
			
		}
		
		public static int getDex() {
					
			return (int) Math.floor((Dexterity - 10.0)/2.0);
					
		}
				
		public static int getCon() {
			
			return (int) Math.floor((Constitution - 10.0)/2.0);
			
		}
		
		public static int getInt() {
			
			return (int) Math.floor((Intelligence - 10.0)/2.0);
			
		}
		
		public static int getWis() {
			
			return (int) Math.floor((Wisdom - 10.0)/2.0);
			
		}
		
		public static int getCha() {
			
			return (int) Math.floor((Charisma - 10.0)/2.0);
			
		}
		
		public static String getAtk1() {
			
			return Attack1;
			
		}
		
		public static String getAtk2() {
			
			return Attack2;
			
		}

		
	    public static void main(String[] arg) {
	       
	        
	        
	        
	    }
	    
	}
