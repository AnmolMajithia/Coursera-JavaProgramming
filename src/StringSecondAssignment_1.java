public class StringSecondAssignment_1 {
    private int findStopCodon(String dna, int startIndex, String stopCodon) {
        int curr = dna.indexOf(stopCodon, startIndex+3);
        while(curr != -1) {
            if((curr-startIndex)%3 == 0) {
                return curr;
            } else {
                curr = dna.indexOf(stopCodon, curr+1);
            }
        }
        return -1;
    }

    private void testFindStopCodon() {
        String dna = "ATGACCATCACCATAACAACCACATAA";
        System.out.println(findStopCodon(dna,0,"TAA"));
        System.out.println(findStopCodon(dna,0,"CAT"));
        System.out.println(findStopCodon(dna,8,"TAA"));
    }

    private String findGene(String dna, int start) {
        int startIndex = dna.indexOf("ATG", start);
        if(startIndex == -1) return "";
        int taa = findStopCodon(dna, startIndex, "TAA");
        int tag = findStopCodon(dna, startIndex, "TAG");
        int tga = findStopCodon(dna, startIndex, "TGA");
        int min;
        if(taa == -1 || tga != -1 && tga<taa) {
            min = tga;
        } else {
            min = taa;
        }
        if(min == -1 || tag != -1 && tag<min) {
            min = tag;
        }
        if(min == -1) return "";
        return dna.substring(startIndex, min+3);
    }

    private void testFindGene() {
        String dna = "ASASDFZDVASDASD";
        System.out.println("DNA : " + dna + "\nGene : " + findGene(dna,0));
        dna = "ANVATGXYYYXXTAA";
        System.out.println("DNA : " + dna + "\nGene : " + findGene(dna,0));
        dna = "AGSDATGXXYYXXTAGTAATGA";
        System.out.println("DNA : " + dna + "\nGene : " + findGene(dna,0));
        dna = "ASDATGASDDFBDSFASD";
        System.out.println("DNA : " + dna + "\nGene : " + findGene(dna,0));
    }

    private void printAllGenes(String dna) {
        int startIndex = 0;
        while(true) {
            String curr = findGene(dna, startIndex);
            if(curr.isEmpty()) {
                break;
            }
            System.out.println(curr);
            startIndex = dna.indexOf("ATG", startIndex) + curr.length();
        }
    }

    public static void main(String[] args) {
        StringSecondAssignment_1 ob = new StringSecondAssignment_1();
        ob.testFindStopCodon();
        ob.testFindGene();
        ob.printAllGenes("ATGXXYYXXTAAXYATGXXXTAGXYXXYXYXATGXYXXYXTGA");
    }
}
