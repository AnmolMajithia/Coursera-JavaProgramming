public class StringThirdAssignment_2 {
    private int countCTG(String b) {
        String a = "CTG";
        int count = 0;
        int curr = b.indexOf(a);
        while(curr != -1) {
            count++;
            curr = b.indexOf(a,curr+a.length());
        }
        return count;
    }

    private double cgRatio(String dna) {
        double c = 0;
        dna = dna.toUpperCase();
        for(int i = 0; i < dna.length(); i++) {
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G') c++;
        }
        return c/(double)dna.length();
    }

    public static void main(String[] args) {
        StringThirdAssignment_2 ob = new StringThirdAssignment_2();
        System.out.println(ob.cgRatio("ATGCCATAG"));
        System.out.println(ob.countCTG("ABSHFGACTGAHSGDACTGASDASHKCTGSDA"));
    }
}
