package bayesclassifier;

import java.util.ArrayList;

/**
 * Class with the main method to run the algorithm.
 * @author Fabian Witt, Steven Brandt
 */
public class BayesClassifier {
    
    private static final String C_SEPERATOR = ",";
    private static final int C_XVAL = 10;

    /**
     * Main method which contains the workflow.
     * @param args Arguments from the console (infile.txt outfile.html).
     */
    public static void main(String[] args) {
        
        if(args.length==2){
            
            String infile = args[0];
            String outfile = args[1];

            Load load = new Load(infile, C_SEPERATOR);
            ArrayList<String[]> globalResult = new ArrayList<>();

            for(int i = 1; i <= C_XVAL; i++){

                Data data = new Data(load.getData(), 66.6666667);

                ArrayList<String[]> trainData = data.getTrainData();
                ArrayList<String[]> testData = data.getTestData();

                Classify classify = new Classify(trainData, testData);
                ArrayList<String[]> result = classify.getResult();

                globalResult.addAll(result);
            }       
            

            Matrix confusionMatrix = new Matrix(globalResult,C_XVAL);
            //confusionMatrix.printColsole();
            //confusionMatrix.writeFile(outfile);
            confusionMatrix.writeHtmlFile(outfile);
            System.out.println("File saved.");
        }
        else{
            System.out.println("[ERROR] Wrong parameters set. (infile.txt outfile.html)");
        }
    }
}