package hw7;
import java.util.*;
import hw4.Graph;
import hw4.edge;
import hw6.MarvelPaths2;
import javafx.util.Pair;

import java.math.*;
import java.io.*;
/**
 * CampusMap is a representation of a map of buildings. 
 * It is implemented with a graph storing buildings and the path labeled with distance between them.
 *
 */
public class CampusMap {

	private Graph<Building,Double> map;
	/**
	 * @effect create an empty map
	 */
	public void CampusMap() {
		this.map = new Graph<Building,Double>();
	}
	/**
	 * 
	 * @param filename1 node file
	 * @param filename2 edge file
	 * @effect create a Map that reads data from two given file.
	 * print StackTrace if it cannot open file
	 */
	public void createNewMap(String filename1, String filename2) {
		try { 
			this.map = new Graph<Building,Double>();
			MapParser.readfile(filename1,filename2, this.map);
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
	/**
	 * 
	 * @return an iterator that traverse through all the buildings' title in alphabetical order
	 */
	public Iterator<String> getAllBuildings() {   
		Iterator<Building> x = map.listNodes();
		ArrayList<String> a = new ArrayList<String>();
		Building b;
		while (x.hasNext()) { 
			b = x.next();
			if (b.getTitle().equals("")) {
				continue;  
			}
			String name = b.getTitle()+","+b.getId();
			a.add(name);
		}
		Collections.sort(a);
		return a.iterator();  
	}
	/**
	 * 
	 * @param building1: name or id of start building
	 * @param building2: name or id of destination 
	 * @return the String represents the shortest path from start building to destination
	 * @throws NullPointerException if input contains null
	 */
	public String findShortestPath(String building1,String building2) {
		if(building1==null ||building2==null) {
			throw new NullPointerException("Input cannot be null");
		}
		Iterator<Building> node_it = this.map.listNodes();
		Building building;
		String output = "";
		Building Start=null;
		Building End=null;
		boolean node1_exist = false;
		boolean node2_exist = false;
		while(node_it.hasNext()) {
			building = node_it.next();
			if(building.equals(building1)) {
				if(!building.getTitle().equals("")) {
				node1_exist = true;
				Start = building;
				}
			}
			if((!building2.equals(""))&&building.equals(building2)) {
				if(!building.getTitle().equals("")) {
				node2_exist = true;
				End = building;
				}
			}
		}
		if(!node1_exist&&!node2_exist) {
			if(building1.equals(building2)) {
				output = output.concat("Unknown building: [" +building1+"]");
			}
			else {
				output += "Unknown building: [" +building1+"]\n";
				output += "Unknown building: [" +building2+"]";
			}
			return output;
			
		}
		if(!node1_exist) {
			output += "Unknown building: [" +building1+"]";
			return output;
		}
		if(!node2_exist) {
			output += "Unknown building: [" +building2+"]";
			return output;
		}
		
		if(building1.equals(building2)) {
			output +="Path from "+ Start.getTitle() + " to " +End.getTitle()+":\n";
			output+= String.format("Total distance: %.3f pixel units.", 0.00);
			return output;
		}
		
		HashMap<Building, Double> Dist = new HashMap<Building, Double>();
		ArrayList<Building> Path = new ArrayList<Building>();
		if(MarvelPaths2.Dijkstra(Path,Dist,this.map,Start,End)) {
			output +="Path from "+ Start.getTitle() + " to " +End.getTitle()+":\n";
			for(int i = Path.size()-1; i > 0; i--) 
			{ 
				
				output += "\tWalk "+Path.get(i).getDirection(Path.get(i-1))+" to (";
				if(Path.get(i-1).getTitle().equals("")) {
					output+="Intersection "+Path.get(i-1).getId()+")\n";
				}else {
					output +=Path.get(i-1).getTitle()+")\n";
				}
				
			}
			
			output += String.format("Total distance: %.3f pixel units.", Dist.get(End));
		}else {
			output += "There is no path from "+Start.getTitle()+" to "+End.getTitle()+".";
		}
		
		
		return output;
	}
	public static void main(String[] arg) {
		

    }

}
