package fuzzylogicsystem;

import java.io.IOException;

public class FuzzyLogicSystem {
    public static final String FILENAME_RESULT_FOR_INFERENCE = "/Users/davisusanibar/Downloads/demo/Requirments/fuzzyouttype1.csv";
    public static final String FILENAME_RESULT_FOR_REDUCE = "/Users/davisusanibar/Downloads/demo/Requirments/fuzzyouttype2.csv";


    public static void main(String[] args) throws IOException {

        // 1.- Inference

        // Create filetype1 & obtaint list of meal/diet availables
        try {
            DaibetesFuzzySystemType1.generateRules();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }

        System.out.println("-------- REDUCER-------- ");
        System.out.println("-------- REDUCER-------- ");
        System.out.println("-------- REDUCER-------- ");

        // 2.- Reducer

        // Create filetype2 & obtaint list of meal/diet availables for reducers
        try {
            DaibetesFuzzySystemType2FCM.generateRules();
        } catch (IOException e) {
            e.printStackTrace();
            //throw e;
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
