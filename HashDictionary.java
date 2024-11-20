// importing the linked list class
import java.util.LinkedList;

/**
 *  @author Eliandro Pizzonia
 *  This class implements the Dictionary ADT using a hash table
 */
public class HashDictionary implements DictionaryADT {
	
	// instance variables: A linked list to store Data variables and a counter
	private LinkedList<Data>[] moveTable;
	private int counter;
	
	
	/**
	 * @param size
	 * constructor to initialize hash table to specified size
	 */
	public HashDictionary(int size) {
		this.moveTable = new LinkedList[size];
		this.counter = 0;	
	}
	
	
	/**
	 * private helper method to compute hash value for config string
	 * @param config
	 * @return hash value for config string
	 */
	private int getHashValue(String config) {
		int hash = 0;
		for (int i = 0; i < config.length(); i++) {
			hash = ((config.charAt(i)) * 31 + hash*11)% this.moveTable.length;
		}
		return hash;
	}
	
	
	/**
	 * Adds record to the dictionary
	 * @throws DictionaryException
	 * @param record
	 * @return collision
	 */
	public int put(Data record)throws DictionaryException {
		
		// check if record already exists
		if(get(record.getConfiguration()) != -1) {
			throw new DictionaryException();
		}
		
		
		int index = getHashValue(record.getConfiguration());
		
		int collision = -1;
		
		// checking for collision
		if(this.moveTable[index]!= null) {
			collision = 1;
		}
		
		// create linked list if none already exist at the index
		if (this.moveTable[index] == null) {
			this.moveTable[index] = new LinkedList<Data>();
			collision = 0;
		}
		
		// add record to linked list at certain index
		this.moveTable[index].addLast(record);
		this.counter += 1;
		
		return collision;
	}
	
	
	/**
	 * Removes the record with the given config from the dictionary
	 *  @throws DictionaryException
	 *  @param config
	 */
	public void remove(String config) throws DictionaryException {
		
		// check if record exists
		if (get(config) == -1) {
			throw new DictionaryException();
		}

		int index = getHashValue(config);
		
		// iterate through the linked list to find and remove record
		for(int i = 0; i < this.moveTable[index].size(); i++) {
			Data record = this.moveTable[index].get(i);
			if (record.getConfiguration().equals(config)) {
				this.moveTable[index].remove(i);
				counter -= 1;
			}
		}
		
		// sets linked list to null if it is empty after removal
		if(this.moveTable[index].size() == 0) {
			this.moveTable[index] = null;
		}		
	}
	
	
	/**
	 * @param config
	 * @return the score stored in the record of the dictionary with key config, or -1 if 
	 *config is not in the dictionary
	 */
	public int get(String config) {
		
		int index = getHashValue(config);
		
		// returns -1 if no linked list exists at index
		if(this.moveTable[index] == null) {
			return -1;
		} 
		
		// finding record in linked list
		for(int i = 0; i < this.moveTable[index].size(); i++) {
			Data record = this.moveTable[index].get(i);
			if (record.getConfiguration().equals(config)) {
				return record.getScore();
			}
		}
		
		return -1;
	}

	
	/**
	 * @return the number of Data objects stored in the dictionary
	 */
	public int numRecords() {
		return this.counter;
	}

}