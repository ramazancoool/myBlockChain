package block.chain.simple.example;

import java.util.Date;
/**
 * In reality each miner will start iterating from a random point. 
 * </p>Some miners may even try random numbers for nonce. 
 * </p>Also it’s worth noting that at the harder difficulties solutions may require more than integer.MAX_VALUE, 
 * </p>miners can then try changing the timestamp.
 *
 */
public class Block {

	private String hash;
	private String previousHash;
	private String data; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce = 0;
	
	//Block Constructor.
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	
	//Calculate new hash based on blocks contents
		public String calculateHash() {
			String calculatedhash = StringUtil.applySha256( 
					previousHash +
					Long.toString(timeStamp) +
					Integer.toString(nonce) + 
					data 
					);
			return calculatedhash;
	}
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}

	public String getHash() {
		return hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}
}