package blockchain.twoHundredlines.code;

public class Block {

	private int index;
	private String previousHash;
	private Long timestamp;
	private String data;
	private String hash;

	public Block(int index, String previousHash, Long timestamp, String data, String hash) {
        this.index = index;
        this.previousHash = previousHash.toString();
        this.timestamp = timestamp;
        this.data = data;
        this.hash = hash.toString();
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public String toString() {
		return "Block [index=" + index + ", previousHash=" + previousHash
				+ ", timestamp=" + timestamp + ", data=" + data + ", hash="
				+ hash + "]";
	}
}
