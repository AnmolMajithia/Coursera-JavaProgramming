import edu.duke.FileResource;
import edu.duke.StorageResource;

import java.io.File;

public class StringThirdAssignment_3 {
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

    private StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while(true) {
            String curr = findGene(dna, startIndex);
            if(curr.isEmpty()) {
                break;
            }
            geneList.add(curr);
            startIndex = dna.indexOf("ATG", startIndex) + curr.length();
        }
        return geneList;
    }

    private double cgRatio(String dna) {
        double c = 0;
        dna = dna.toUpperCase();
        for(int i = 0; i < dna.length(); i++) {
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G') c++;
        }
        return c/(double)dna.length();
    }

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

    private void processGenes(StorageResource sr) {
        int longerThan9 = 0;
        int cg35 = 0;
        String max = "";
        for(String s : sr.data()) {
            System.out.println("Number of CTG : " + countCTG(s));
            StorageResource genes = getAllGenes(s);
            int i = 0;
            for(String gene : genes.data()) {
                i++;
                if(gene.length() > max.length()) {
                    max = gene;
                }
                if(gene.length() > 60) {
                    System.out.println("Longer than 60 : " + s);
                    longerThan9++;
                }
                if(cgRatio(gene) > 0.35) {
                    System.out.println("CG Ration greater than 3.5 : " + s);
                    cg35++;
                }
            }
            System.out.println("Number of genes = " + i);
        }
        System.out.println("Number of Strings longer than 60 : " + longerThan9);
        System.out.println("Number of Strings with CG ration greater than 0.35 : " + cg35);
        System.out.println("Longest Gene : " + max);
        System.out.println("Length of longest gene : " + max.length());
    }

    private void testProcessGenes() {
        StorageResource sr = new StorageResource();
//        File dir = new File("/home/anmol/JavaProjects/Coursera/JavaProgramming/data/dna");
//        File[] directoryListing = dir.listFiles();
//        for (File child : directoryListing) {
//            sr.add(new FileResource(child).asString());
//        }
        sr.add(new FileResource("/home/anmol/JavaProjects/Coursera/JavaProgramming/data/dna/GRch38dnapart.fa").asString().toUpperCase());
        processGenes(sr);
    }

    public static void main(String[] args) {
        StringThirdAssignment_3 ob = new StringThirdAssignment_3();
        ob.testProcessGenes();
    }
}
