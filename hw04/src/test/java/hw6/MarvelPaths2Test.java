package hw6;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.Test;

import hw5.MarvelPaths;
import hw6.MarvelPaths2;

public class MarvelPaths2Test {


	@Test
	 public void Test_creation() {
		 MarvelPaths2 T = new MarvelPaths2();
	 }
	
	@Test
	 public void Test_creation_2() {
		 String filename = "data/test1.csv";
		 MarvelPaths2 T = new MarvelPaths2();	 
		 T.createNewGraph(filename);
	 }
	
	@Test
	 public void Test_path1() {
		 String filename = "data/marvel.csv";
		 MarvelPaths2 T = new MarvelPaths2();	 
		 T.createNewGraph(filename);
		 assertEquals(T.findPath("PRISM", "HUMAN ROBOT"),"path from PRISM to HUMAN ROBOT:\n" +
				"PRISM to SCALPHUNTER with weight 0.250\n"+
		 		"SCALPHUNTER to WOLVERINE/LOGAN  with weight 0.091\n" + 
		 		"WOLVERINE/LOGAN  to CYCLOPS/SCOTT SUMMER with weight 0.004\n" + 
		 		"CYCLOPS/SCOTT SUMMER to BEAST/HENRY &HANK& P with weight 0.003\n" + 
		 		"BEAST/HENRY &HANK& P to CAPTAIN AMERICA with weight 0.006\n" + 
		 		"CAPTAIN AMERICA to HUMAN ROBOT with weight 0.500\n" + 
		 		"total cost: 0.854\n");
	 }
	@Test
	 public void Test_Same_unknown() {
		 String filename = "data/test1.csv";
		 MarvelPaths2 T = new MarvelPaths2();	 
		 T.createNewGraph(filename);
		 assertEquals(T.findPath("Sky penis", "Sky penis"),"unknown character Sky penis\n");
	 }
	@Test
	 public void Test_2diff_unknown() {
		 String filename = "data/test1.csv";
		 MarvelPaths2 T = new MarvelPaths2();	 
		 T.createNewGraph(filename);
		 assertEquals(T.findPath("Sky penis", "Sky penis2"),"unknown character Sky penis\nunknown character Sky penis2\n");
	 }
	@Test
	 public void Test_one_Unkown() {
		 String filename = "data/test1.csv";
		 MarvelPaths2 T = new MarvelPaths2();	 
		 T.createNewGraph(filename);
		 assertEquals(T.findPath("Sky penis", "Joy"),"unknown character Sky penis\n");
	 }
	@Test
	 public void Test_one_Unkown2() {
		 String filename = "data/test1.csv";
		 MarvelPaths2 T = new MarvelPaths2();	 
		 T.createNewGraph(filename);
		 assertEquals(T.findPath("Joy","Sky penis"),"unknown character Sky penis\n");
	 }
	@Test
	 public void Test_Same_Char() {
		 String filename = "data/marvel.csv";
		 MarvelPaths2 T = new MarvelPaths2();	 
		 T.createNewGraph(filename);
		 assertEquals(T.findPath("HUMAN ROBOT","HUMAN ROBOT"),"path from HUMAN ROBOT to HUMAN ROBOT:\n" + "total cost: 0.000\n");
	 }

}
