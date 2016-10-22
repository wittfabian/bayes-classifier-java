package bayesclassifier;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * counts attributes in the data-set
 *
 * @author Fabian Witt, Steven Brandt
 */
public class Counter {
    
    private ArrayList<String[]> m_data;
    
    /**
     * set the data-set
     *
     * @param data data-set
     */
    public Counter(ArrayList<String[]> data){  
        m_data = data;
    }
    
    /**
     * count the value "value" at the position "pos"
     *
     * @param value value we search for
     * @param pos position in the String-Array
     * @return counted value
     */
    public double count(String value, int pos){
        
        double result = 0.0;
        
        Iterator<String[]> iter = m_data.iterator();
        
        while(iter.hasNext()){
            String[] aktIter = iter.next();
            
            if(aktIter[pos].equals(value))
                result++;
        }
        
        return result;
    }
    
    /**
     * count the value "value" at the position "pos" provided the value "value2" at the position "pos2"
     *
     * @param value first value we search for
     * @param pos position of the first value in the String-Array
     * @param value2 second value we search for (condition)
     * @param pos2 position of the second value in the String-Array (condition)
     * @return counted value
     */
    public double count(String value, int pos, String value2, int pos2){
        
        double result = 0.0;
        
        Iterator<String[]> iter = m_data.iterator();
        
        while(iter.hasNext()){
            String[] aktIter = iter.next();
            
            if(aktIter[pos].equals(value) && aktIter[pos2].equals(value2))
                result++;
        }
        
        return result;
    }
}