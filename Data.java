/**
 * @author Eliandro Pizzonia 
 * This class represents the records that will be stored in the HashDictionary
*/
public class Data {
	
	// instance variables
    private String config;
    private int score;

    
    /**
     * @param config
     * @param score
     * A constructor which initializes a new Data object with the specified configuration and score
     */
    public Data(String config, int score){
        this.config = config;
        this.score = score;
    }

    
    /**
     * @return the configuration stored in this Data object
     */
    public String getConfiguration(){
        return config;
    }
    
    
    /**
     * @return the score stored in this Data object
     */
    public int getScore(){
        return score;
    }
}
