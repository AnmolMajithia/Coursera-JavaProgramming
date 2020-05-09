public class StringFirstAssignment_2 {
    private String findSimpleGene(String dna, String startCodon, String stopCodon) {
        boolean lower = false;
        if(Character.isLowerCase(dna.charAt(0))) {
            lower = true;
        }
        dna = dna.toUpperCase();
        int start = dna.indexOf(startCodon);
        if(start == -1) {
            return "";
        }
        int end = dna.indexOf(stopCodon, start+1);
        if(end == -1) {
            return "";
        }
        if((end - start) % 3 == 0) {
            if(lower) {
                dna = dna.toLowerCase();
            }
            return dna.substring(start, end+stopCodon.length());
        } else {
            return "";
        }
    }

    private void testSimpleGene() {
        String dna = "AGCTATCGATAACG";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna,"ATG","TAA"));
        dna = "ATGAACGAAC";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna,"ATG","TAA"));
        dna = "ACACGCATCGAC";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna,"ATG","TAA"));
        dna = "atgagctaa";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna,"ATG","TAA"));
        dna = "ATGACTAA";
        System.out.println("DNA : " + dna + "\nGene : " + findSimpleGene(dna,"ATG","TAA"));
    }

    public static void main(String[] args) {
        StringFirstAssignment_2 ob = new StringFirstAssignment_2();
        ob.testSimpleGene();
    }
}
