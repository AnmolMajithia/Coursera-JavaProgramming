import edu.duke.*;

public class HelloWorld {
    public void runHello () {
        FileResource res = new FileResource("/home/anmol/JavaProjects/Coursera/JavaProgramming/data/hello_unicode.txt");
        for (String line : res.lines()) {
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        HelloWorld ob = new HelloWorld();
        ob.runHello();
    }
}
