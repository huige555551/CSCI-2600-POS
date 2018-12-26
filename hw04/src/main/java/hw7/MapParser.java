package hw7;
import java.io.*;
import java.util.*;
import javafx.util.*;
import hw4.edge;
import hw4.Graph;
import java.math.*;
/**
 * 
 * @author alanluo
 *MapParser reads data from two files and store it into a graph.
 */
public class MapParser {
	public static void readfile(String node_filename, String edge_filename, Graph<Building,Double> map)
	throws IOException{
		BufferedReader node_reader = new BufferedReader(new FileReader(node_filename));
		String line =null;
		HashMap<Integer,Building > loc = new HashMap<Integer,Building>();
		while((line =node_reader.readLine())!=null) {
			String[] data = line.split(",");
			String title = data[0];
			int id = Integer.valueOf(data[1]);
			int x =Integer.valueOf(data[2]);
			int y = Integer.valueOf(data[3]);
			Building bu = new Building(title,id,x,y);
			map.addNode(bu);
			loc.put(id, bu);
		}
		BufferedReader edge_reader = new BufferedReader(new FileReader(edge_filename));
		while((line = edge_reader.readLine())!=null) {
			String[] data = line.split(",");
            int id1 = Integer.valueOf(data[0]);
            int id2 = Integer.valueOf(data[1]);
			Building bu_1 = loc.get(id1);
			Building bu_2 = loc.get(id2);
			double dist = Math.sqrt(Math.pow(bu_1.getY()-bu_2.getY(),2)+Math.pow(bu_1.getX()-bu_2.getX(), 2));
			map.addEdge(bu_1, bu_2,dist);
			map.addEdge(bu_2, bu_1, dist);
		}
		
		
	}

}
