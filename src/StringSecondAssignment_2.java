public class StringSecondAssignment_2 {
    private int howMany(String a, String b) {
        int count = 0;
        int curr = b.indexOf(a);
        while(curr != -1) {
            count++;
            curr = b.indexOf(a,curr+a.length());
        }
        return count;
    }

    private void testHowMany() {
        System.out.println(howMany("GAA","ATGAACGAATTGAATC"));
        System.out.println(howMany("AA","ATAAAA"));
        System.out.println(howMany("abc","defghijklmnopqrstuvwxyz"));
    }

    public static void main(String[] args) {
        StringSecondAssignment_2 ob = new StringSecondAssignment_2();
        ob.testHowMany();
    }
}
