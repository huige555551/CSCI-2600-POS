package hw7;
import java.io.*;

import hw7.*;
import static org.junit.Assert.*;

import org.junit.Test;
public class CampusMapTest {
	@Test
	public void Building1() {
		Building b = new Building();
		assertEquals(b.getId(),0);
		assertEquals(b.getTitle(),"");
		assertEquals(b.getX(),0);
		assertEquals(b.getY(),0);
	}
	@Test
	public void Building2() {
		Building b = new Building("ABC",1,2,3);
		assertEquals(b.getId(),1);
		assertEquals(b.getTitle(),"ABC");
		assertEquals(b.getX(),2);
		assertEquals(b.getY(),3);
	}
	@Test
	public void Building3() {
		Building b = new Building("ABC",1,2,3);
		Building c = new Building("abc",2,3,3);
		assertEquals(b.compareTo(c),-1);
		assertEquals(b.getDirection(c),"East");
		assertEquals(b.equals(c),false);
		assertEquals(b.equals("ABC"),true);	
	}
	@Test
	public void MapcreationTest() {
		CampusMap map = new CampusMap();
		map.createNewMap("data/RPI_map_data_Nodes.csv","data/RPI_map_data_Edges.csv");
		
	}
	@Test
	public void testfindShortestPaths() { 
		CampusMap map1 = new CampusMap();
		map1.createNewMap("data/RPI_map_data_Nodes.csv","data/RPI_map_data_Edges.csv");
		assertEquals(map1.findShortestPath("",""),"Unknown building: []");
		assertEquals(map1.findShortestPath("a", "a"),"Unknown building: [a]");
		assertEquals(map1.findShortestPath("a", "b"),"Unknown building: [a]\nUnknown building: [b]");
		assertEquals(map1.findShortestPath("Sharp Hall","a"),"Unknown building: [a]");
		assertEquals(map1.findShortestPath("a","Sharp Hall"),"Unknown building: [a]");
		assertEquals(map1.findShortestPath("Sharp Hall", "Sharp Hall"),"Path from Sharp Hall to Sharp Hall:\n"
				+ "Total distance: 0.000 pixel units.");
		assertEquals(map1.findShortestPath("Polytechnic Residence Commons", "Blitman Residence Commons"),
				"There is no path from Polytechnic Residence Commons to Blitman Residence Commons.");
		assertEquals(map1.findShortestPath("","Sharp Hall"),"Unknown building: []");
		assertEquals(map1.findShortestPath("Sharp Hall",""),"Unknown building: []");
		assertEquals(map1.findShortestPath("108","Davison Hall"),"Unknown building: [108]");
		assertEquals(map1.findShortestPath("Davison Hall","124"),"Unknown building: [124]");
		assertEquals(map1.findShortestPath("15","108"),"Unknown building: [108]");
		assertEquals(map1.findShortestPath("108","15"),"Unknown building: [108]");
		assertEquals(map1.findShortestPath("Field House Houston", "EMPAC"),"Path from Field House Houston to EMPAC:"
				+ "\n\tWalk West to (Intersection 113)"
				+ "\n\tWalk West to (Intersection 111)"
				+ "\n\tWalk West to (Intersection 117)"
				+ "\n\tWalk West to (Intersection 112)"
				+ "\n\tWalk SouthWest to (2021 Peoples Avenue)"
				+ "\n\tWalk West to (Beman Park Firehouse)"
				+ "\n\tWalk West to (Alumni House)"
				+ "\n\tWalk SouthWest to (H Building)"
				+ "\n\tWalk South to (North Hall)"
				+ "\n\tWalk SouthWest to (Intersection 132)"
				+ "\n\tWalk SouthWest to (Troy Building)"
				+ "\n\tWalk SouthWest to (Intersection 133)"
				+ "\n\tWalk SouthWest to (Intersection 134)"
				+ "\n\tWalk SouthWest to (Lally Hall)"
				+ "\n\tWalk South to (Folsom Library)"
				+ "\n\tWalk SouthWest to (EMPAC)"
				+ "\nTotal distance: 1473.118 pixel units.");
		
	}

}
