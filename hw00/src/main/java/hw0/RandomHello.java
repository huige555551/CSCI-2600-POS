package hw0;
import java.util.Random;
public class RandomHello {
	 public static void main(String[] argv) {
	        RandomHello randomHello = new RandomHello();
	        System.out.println(randomHello.getGreeting());
	    }
	 
	 
	 public String getGreeting() {
		 Random randomGenerator = new Random();
		 String[] greetings = new String[5];
		 greetings[0] = "Hello, World";
		 greetings[1] = "Hola Mundo";
		 greetings[2] = "Bonjour, le Monde";
		 greetings[3] = "Hallo Welt";
		 greetings[4] = "Ciao Mondo";
		 int m = randomGenerator.nextInt(5);
	        return greetings[m];
	    }
}

