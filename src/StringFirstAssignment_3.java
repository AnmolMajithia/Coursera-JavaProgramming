public class StringFirstAssignment_3 {
    private boolean twoOccurences(String s1, String s2) {
        int f1 = s2.indexOf(s1);
        int f2 = s2.indexOf(s1,f1+1);
        return f1 != -1 && f2 != -1;
    }

    private String lastPart(String s1, String s2) {
        int f = s2.indexOf(s1);
        if(f != -1) {
            return s2.substring(f+s1.length());
        } else {
            return s2;
        }
    }

    private void testing() {
        String s1 = "an";
        String s2 = "banana";
        System.out.println("String a: " + s1 + "\nString b: " + s2 + "\nResult: " + twoOccurences(s1,s2) + "\nLast Part: " + lastPart(s1,s2) + "\n");
        s1 = "by";
        s2 = "A story by Abby Long";
        System.out.println("String a: " + s1 + "\nString b: " + s2 + "\nResult: " + twoOccurences(s1,s2) + "\nLast Part: " + lastPart(s1,s2) + "\n");
        s1 = "atg";
        s2 = "ctgtatgta";
        System.out.println("String a: " + s1 + "\nString b: " + s2 + "\nResult: " + twoOccurences(s1,s2) + "\nLast Part: " + lastPart(s1,s2) + "\n");
        s1 = "zoo";
        s2 = "forest";
        System.out.println("String a: " + s1 + "\nString b: " + s2 + "\nResult: " + twoOccurences(s1,s2) + "\nLast Part: " + lastPart(s1,s2) + "\n");
    }

    public static void main(String[] args) {
        StringFirstAssignment_3 ob = new StringFirstAssignment_3();
        ob.testing();
    }
}
