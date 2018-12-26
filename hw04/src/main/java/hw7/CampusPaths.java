package hw7;
import java.util.*;
public class CampusPaths{
	public static void main(String[] arg) {
		CampusMap map = new CampusMap();
		map.createNewMap("data/RPI_map_data_Nodes.csv","data/RPI_map_data_Edges.csv");
		Scanner s = new Scanner(System.in);
		String input = s.nextLine();
		String menu = "Menu:\n\tType b to list all buildings\n\ttype r to Find the shortest route between two buildings"
				+ "\n\tType q to quit the program\n";
				
		while(input !=null) {
			if(input.equals("b")) {
				Iterator<String> b = map.getAllBuildings();
				while(b.hasNext()) {
					System.out.println(b.next()); 
				}
			}
			else if(input.equals("r")) {
				System.out.print("First building id/name, followed by Enter: ");
	    	    String building1 = s.nextLine();
	    		System.out.print("Second building id/name, followed by Enter: ");
	    	    String building2 = s.nextLine();
	    	    String result = map.findShortestPath(building1, building2);
	    	    System.out.println(result); 
			}
			else if(input.equals("m")) {
				System.out.println(menu);
			}
			else if(input.equals("q")){
				return;
			}
			else {
				System.out.println("Unknown option");
			}
			input=s.nextLine();
		}
		
				
	}
	
}