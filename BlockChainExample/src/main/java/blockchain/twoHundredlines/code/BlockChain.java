package blockchain.twoHundredlines.code;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BlockChain {

	private List<Block> blockchain = new ArrayList<Block>(Arrays.asList(getGenesisBlock()));
	
	public String calculateHash(int index, String previousHash, Long timestamp, String data){
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");	        
			String input = index + previousHash + timestamp + data;
			//Applies sha256 to our input, 
			byte[] hash = digest.digest(input .getBytes("UTF-8"));	        
			StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Block generateNextBlock(String blockData){
		
		Block previousBlock = getLatestBlock();
	    int nextIndex = previousBlock.getIndex() + 1;
	    Long nextTimestamp = new Date().getTime() / 1000;
	    String nextHash = calculateHash(nextIndex, previousBlock.getHash(), nextTimestamp, blockData);
	    return new Block(nextIndex, previousBlock.getHash(), nextTimestamp, blockData, nextHash);
	}

	public boolean isValidNewBlock(Block newBlock, Block previousBlock){
		
		 if (previousBlock.getIndex() + 1 != newBlock.getIndex()) {
		        System.out.println("invalid index");
		        return false;
		    } else if (!previousBlock.getHash().equals(newBlock.getPreviousHash())) {
		    	System.out.println("invalid previoushash");
		        return false;
		    } else if (!calculateHashForBlock(newBlock).equals(newBlock.getHash())) {
		    	System.out.println("invalid hash: " + calculateHashForBlock(newBlock) + " " + newBlock.getHash());
		        return false;
		}
		 
		return false;
	}
	
	private String calculateHashForBlock(Block block) {
		return calculateHash(block.getIndex(), block.getPreviousHash(), block.getTimestamp(), block.getData());
	}

	public Block getGenesisBlock(){
		 return new Block(0, "0", 1465154705l, "my genesis block!!", "816534932c2b7154836da6afc367695e6337db8a921823784c14378abed4f7d7");
	}
	
	private Block getLatestBlock() {
		return blockchain.get(blockchain.size() - 1);
	}

	public List<Block> getBlockchain() {
		return blockchain;
	}
}
