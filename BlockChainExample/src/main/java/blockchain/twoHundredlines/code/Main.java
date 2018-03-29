package blockchain.twoHundredlines.code;

import com.google.gson.GsonBuilder;

public class Main {

	public static void main(String[] args) {
		BlockChain blockChain = new BlockChain();

		Block blok2 = blockChain.generateNextBlock("2.block");
		blockChain.getBlockchain().add(blok2);
		
		Block blok3 = blockChain.generateNextBlock("3.block");
		blockChain.getBlockchain().add(blok3);

		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain.getBlockchain());
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
		
	}

}
