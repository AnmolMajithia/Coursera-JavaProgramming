public class StringFirstAssignment_2 {
    private String findSimpleGene(String dna, int startCodon, int stopCodon) {
        boolean lower = false;
        if(Character.isLowerCase(dna.charAt(0))) {
            lower = true;
        }
        dna = dna.toUpperCase();
        startCodon = dna.indexOf("ATG", startCodon);
        if(startCodon == -1) {
            return "";
        }
        stopCodon = dna.indexOf("TAA",stopCodon);
        if(stopCodon == -1) {
            return "";
        }
        if((stopCodon - startCodon) % 3 == 0) {
            if(lower) {
                dna = dna.toLowerCase();
            }
            return dna.substring(startCodon, stopCodon+3);
        } else {
            return "";
        }
    }

    private void testSimpleGene() {
        String dna = "AGCTATCGATAACG";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna,0,0));
        dna = "ATGAACGAAC";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna,0,0));
        dna = "ACACGCATCGAC";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna,0,0));
        dna = "atgagctaa";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna,0,10));
        dna = "ATGACTAA";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna,0,0));
    }

    public static void main(String[] args) {
        StringFirstAssignment_2 ob = new StringFirstAssignment_2();
        ob.testSimpleGene();
    }
}
