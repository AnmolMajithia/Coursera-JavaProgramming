import edu.duke.StorageResource;

public class StringThirdAssignment_1 {
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

    public static void main(String[] args) {
        StringThirdAssignment_1 ob = new StringThirdAssignment_1();
        StorageResource res = ob.getAllGenes("ATGXXYYXXTAAXYATGXXXTAGXYXXYXYXATGXYXXYXTGA");
        for(String s : res.data()) {
            System.out.println(s);
        }
    }
}
