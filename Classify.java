package bayesclassifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class uses the training data to classify the test data with the Naive
 * Bayes classification.
 * @author Fabian Witt, Steven Brandt
 */
public class Classify {
    
    private final ArrayList<String[]> m_trainData;
    private final ArrayList<String[]> m_testData;
    private final ArrayList<String[]> m_result;

    /**
     * Consturctor to get the data and automatically classify the test data.
     * @param trainData The training data.
     * @param testData The test data.
     */
    public Classify(ArrayList<String[]> trainData, ArrayList<String[]> testData) {
        this.m_trainData = trainData;
        this.m_testData = testData;
        this.m_result = makeClassification();
    }

    /**
     * Getter, which returns the result of the classification.
     * @return Returns a list with the target and the classification values.
     */
    public ArrayList<String[]> getResult() {
        return m_result;
    }
    
    
    /**
     * Method to classify the test data.
     * @return Returns a list of all target classes and the classifications.
     */
    private ArrayList<String[]> makeClassification(){
        ArrayList<String[]> result = new ArrayList<>();
        
        int classpos = m_trainData.get(0).length-1;
        HashMap<String,Double> classes = new HashMap<>();
        double trainsize = (double)m_trainData.size();
        
        //get the different class values and their count
        Counter counter = new Counter(m_trainData);
        for(int i = 0; i<m_trainData.size();i++){
            String current = m_trainData.get(i)[classpos];
            if(!classes.containsKey(current)){
                classes.put(current, counter.count(current, classpos));
            }
        }
        
        //iterate through test examples and classify each one
        Iterator iter_data = m_testData.iterator();
        while(iter_data.hasNext()){
            String[] tmp = (String[])iter_data.next();
            HashMap<String,Double> probs = new HashMap<>();
            
            //iterate through classes
            Iterator iter_classes = classes.entrySet().iterator();
            while(iter_classes.hasNext()){
                Map.Entry pairs = (Map.Entry)iter_classes.next();
                double tmpprob = (double)pairs.getValue()/trainsize;

                //iterate through attributes
                for(int i = 0; i<tmp.length-1;i++){
                    String attvalue = tmp[i];
                    double attclassanz = counter.count(attvalue, i, (String)pairs.getKey(), classpos);
                    tmpprob = tmpprob * attclassanz/(double)pairs.getValue();
                }
                probs.put((String)pairs.getKey(), tmpprob);
            }
            //iterate through probabilities and select the classification
            Iterator iter_probs = probs.entrySet().iterator();
            double maxprob = 0;
            String maxclass = "";
            while(iter_probs.hasNext()){
                Map.Entry classpairs = (Map.Entry)iter_probs.next();
                if((double)classpairs.getValue() >= maxprob){
                    maxprob = (double)classpairs.getValue();
                    maxclass = (String)classpairs.getKey();
                }
            }
            String[] resstring = new String[2];
            resstring[0] = tmp[classpos];
            resstring[1] = maxclass;
            result.add(resstring);
        }
        return result;
    }
}
