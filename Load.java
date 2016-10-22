package bayesclassifier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class will prepare the data
 * 
 * @author Fabian Witt, Steven Brandt
 */
public class Load {
    
    private ArrayList<String[]> m_data;
    private String m_splitSign;
    private String m_inFile;
    
    /**
     * Load the data-set and split it into training and test data
     *
     * @param inFile String with the path of the file
     * @param splitSign String with the seperator string
     */
    public Load(String inFile, String splitSign){
        
        m_data = new ArrayList<>();
        m_inFile = inFile;
        m_splitSign = splitSign;
        
        try {
            readFile();
        } catch (IOException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * returns the ArrayList of the data-set
     *
     * @return m_data
     */
    public ArrayList<String[]> getData(){
        return m_data;
    }

    /**
     * Set the seperator string
     *
     * @param m_splitSign String with the seperator string
     */
    public void setSplitSign(String m_splitSign) {
        this.m_splitSign = m_splitSign;
    }

    /**
     * Set the path of the file
     *
     * @param m_inFile String with the path of the file
     */
    public void setInFile(String m_inFile) {
        this.m_inFile = m_inFile;
    }
    
    /**
     * Read the file within the path "m_inFile" and the seperator "m_splitSign"
     * 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void readFile() throws FileNotFoundException, IOException {
        
        FileReader fr = new FileReader(m_inFile);
        BufferedReader br = new BufferedReader(fr);

        String zeile = "";

        while( (zeile = br.readLine()) != null )
        {  
            m_data.add( zeile.split(m_splitSign) );
        }

        br.close();
    }     
}