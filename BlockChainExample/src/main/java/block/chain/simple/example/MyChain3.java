package block.chain.simple.example;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class MyChain3 {
	
	public static int difficulty = 5;
	public static ArrayList<Block> blockchain = new ArrayList<Block>(); 

	public static void main(String[] args) {	
		//add our blocks to the blockchain ArrayList:
		
		String previousHash = "0";
		for (int i = 0; i < 4; i++) {
			Block block = new Block("Hi, Im " + i + ". block", previousHash);
			blockchain.add(block);

			System.out.println("Trying to Mine block " + i + " ... ");
			block.mineBlock(difficulty);
			
			previousHash = block.getHash();
		}
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.getHash().equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.getHash().equals(currentBlock.getPreviousHash()) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.getHash().substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}

}