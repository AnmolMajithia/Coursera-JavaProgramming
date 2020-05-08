import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int c = 0;
        for (Point currPt : s.getPoints()) {
            c++;
        }
        return c;
    }

    public double getAverageLength(Shape s) {
        return getPerimeter(s)/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        double max = Double.MIN_VALUE;
        Point prev = s.getLastPoint();
        for(Point curr : s.getPoints()) {
            double len = prev.distance(curr);
            if(len > max) {
                max = len;
            }
            prev = curr;
        }
        return max;
    }

    public double getLargestX(Shape s) {
        double max = Double.MIN_VALUE;
        for(Point curr : s.getPoints()) {
            if(curr.getX() > max) {
                max = curr.getX();
            }
        }
        return max;
    }

    public double getLargestPerimeterMultipleFiles() {
        double max = Double.MIN_VALUE;
        File dir = new File("/home/anmol/JavaProjects/Coursera/JavaProgramming/data/perimeterAssignment");
        File[] directoryListing = dir.listFiles();
        for (File child : directoryListing) {
            Shape s = new Shape(new FileResource(child));
            double peri = getPerimeter(s);
            if(peri > max) {
                max = peri;
            }
        }
        return max;
    }

    public String getFileWithLargestPerimeter() {
        double max = Double.MIN_VALUE;
        String res = "";
        File dir = new File("/home/anmol/JavaProjects/Coursera/JavaProgramming/data/perimeterAssignment");
        File[] directoryListing = dir.listFiles();
        for (File child : directoryListing) {
            if(child.getName().startsWith("ex")) {
                Shape s = new Shape(new FileResource(child));
                double peri = getPerimeter(s);
                if (peri > max) {
                    max = peri;
                    res = child.getName();
                }
            }
        }
        return res;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource("/home/anmol/JavaProjects/Coursera/JavaProgramming/data/perimeterAssignment/datatest1.txt");
        Shape s = new Shape(fr);
        System.out.println("Number of points: " + getNumPoints(s));
        System.out.println("Average Length: " + getAverageLength(s));
        System.out.println("Largest side: " + getLargestSide(s));
        System.out.println("Largest X: " + getLargestX(s));
        System.out.println("Perimeter = " + getPerimeter(s));
    }

    public void testPerimeterMultipleFiles() {
        System.out.println("Largest Perimeter: " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("File with Largest Perimeter: " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
//    public void triangle(){
//        Shape triangle = new Shape();
//        triangle.addPoint(new Point(0,0));
//        triangle.addPoint(new Point(6,0));
//        triangle.addPoint(new Point(3,6));
//        for (Point p : triangle.getPoints()){
//            System.out.println(p);
//        }
//        double peri = getPerimeter(triangle);
//        System.out.println("perimeter = "+peri);
//    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
//        DirectoryResource dr = new DirectoryResource();
//        for (File f : dr.selectedFiles()) {
//            System.out.println(f);
//        }
        File dir = new File("/home/anmol/JavaProjects/Coursera/JavaProgramming/data/perimeterAssignment");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                String str = child.toString();
                if(str.endsWith("txt")) {
                    System.out.println(str);
                }
            }
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
//        pr.printFileNames();
    }
}
