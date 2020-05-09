public class StringFirstAssignment_1 {
    private String findSimpleGene(String dna) {
        int startCodon = dna.indexOf("ATG");
        if(startCodon == -1) {
            return "";
        }
        int stopCodon = dna.indexOf("TAA",startCodon+1);
        if(stopCodon == -1) {
            return "";
        }
        if((stopCodon - startCodon) % 3 == 0) {
            return dna.substring(startCodon, stopCodon+3);
        } else {
            return "";
        }
    }

    private void testSimpleGene() {
        String dna = "AGCTATCGATAACG";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna));
        dna = "ATGAACGAAC";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna));
        dna = "ACACGCATCGAC";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna));
        dna = "ATGAGCTAA";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna));
        dna = "ATGACTAA";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna));
    }

    public static void main(String[] args) {
        StringFirstAssignment_1 ob = new StringFirstAssignment_1();
        ob.testSimpleGene();
    }
}
