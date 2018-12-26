package hw5;
import static org.junit.Assert.*;
import org.junit.Test;

import hw6.MarvelPaths2;

import java.io.*;
 public final class MarvelPathsTest {
	 @Test
	 public void Test_creation() {
		 MarvelPaths T = new MarvelPaths();
	 }
	 @Test
	 public void Test_creation_2() {
		 String filename = "data/marvel.csv";
		 MarvelPaths T = new MarvelPaths();	 
		 T.createNewGraph(filename);
	 }
	 @Test
	 public void Test_simpleuniverse() {
		 String filename = "data/marvel.csv";
		 MarvelPaths T = new MarvelPaths();	 
		 T.createNewGraph(filename);
		 assertEquals(T.findPath("FROST, CARMILLA", "KILLRAVEN/JONATHAN R"),"path from FROST, CARMILLA to KILLRAVEN/JONATHAN R:\nFROST, CARMILLA to KILLRAVEN/JONATHAN R via AA2 21\n");
	 }
	 @Test
	 public void Test_unknown() {
		 String filename = "data/marvel.csv";
		 MarvelPaths T = new MarvelPaths();	 
		 T.createNewGraph(filename);
		 assertEquals(T.findPath("FROST, CARMILLA", "WTF"),"unknown character WTF\n");
	 }
	 @Test
	 public void Test_samechar() {
		 String filename = "data/marvel.csv";
		 MarvelPaths T = new MarvelPaths();	 
		 T.createNewGraph(filename);
		 assertEquals(T.findPath("FROST, CARMILLA", "FROST, CARMILLA"),"path from FROST, CARMILLA to FROST, CARMILLA:\n");
	 }
	 @Test
	 public void Test_2invalid() {
		 String filename = "data/marvel.csv";
		 MarvelPaths T = new MarvelPaths();	 
		 T.createNewGraph(filename);
		 assertEquals(T.findPath("Sky penis", "Sky penis"),"unknown character Sky penis\n");
	 }
	 @Test
	 public void Test_path_1() {
		 String filename = "data/marvel.csv";
		 MarvelPaths T = new MarvelPaths();
		 T.createNewGraph(filename);
		 assertEquals(T.findPath("FROST, CARMILLA", "KILLRAVEN/JONATHAN R"),"path from FROST, CARMILLA to KILLRAVEN/JONATHAN R:\nFROST, CARMILLA to KILLRAVEN/JONATHAN R via AA2 21\n");
	 }
	 @Test
	 public void Test_2diff_unknown() {
		 String filename = "data/marvel.csv";
		 MarvelPaths T = new MarvelPaths();	 
		 T.createNewGraph(filename);
		 assertEquals(T.findPath("Sky penis", "Sky penis2"),"unknown character Sky penis\nunknown character Sky penis2\n");
	 }
	 
	

 }