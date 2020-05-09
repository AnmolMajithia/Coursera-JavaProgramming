import edu.duke.URLResource;

public class StringFirstAssignment_4 {
    private void urlres() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for(String s : ur.words()) {
            s = s.toLowerCase();
            int pos = s.indexOf("youtube.com");
            if(pos != -1) {
                int start = s.lastIndexOf("\"", pos);
                int end = s.indexOf("\"", pos+1);
                System.out.println(s.substring(start+1, end));
            }
        }
    }

    public static void main(String[] args) {
        StringFirstAssignment_4 ob = new StringFirstAssignment_4();
        ob.urlres();
    }
}
