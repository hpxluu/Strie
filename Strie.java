public class Strie{

	private StrieNode root;  
	private int numWords = 0; 

	public Strie(){
		this.root = new StrieNode();
		this.numWords = 0;
	}
	
	public int numWords(){
		return numWords; 
	}
	
	public StrieNode getRoot(){
		return root; 
	
	}
	
	public void insert(String word){
		
		int i = 0; StrieNode tempNode = root;
		while (i<word.length()) {
			char ch = word.charAt(i);
			
			if (!(tempNode.containsChild(ch))) { 
				tempNode.putChild(ch,new StrieNode());
			}
			i++; tempNode = tempNode.getChild(ch);
		}
		tempNode.setEnd(); numWords++;
	}
	
	public boolean contains(String word){
		
		boolean isContained = false; StrieNode tempNode = root; 
		int i = 0; String compare = "";
		while (i<word.length()) {
			
			char ch = word.charAt(i);
			if (tempNode!=null) {
				if (tempNode.containsChild(ch)) {
					compare += ch;
				}
				
				if (compare.equals(word) && i == word.length()-1 && tempNode.getChild(ch).isEnd()) {
					isContained = true; break;
				}
			}
			if (tempNode==null) {
				break;
			}
			tempNode = tempNode.getChild(ch);
			i++;
		}
		return isContained;
	}

	public boolean remove(String word){
		
		boolean isRemoved = false, isBranch = false;
		StrieNode tempNode = root; 
		Strie tempObj = new Strie(); 
		tempObj.root = root;
		
		if (tempObj.contains(word)) {
			int i = 0;
			while (i<word.length()) {
				char ch = word.charAt(i);
				tempNode = tempNode.getChild(ch);
				if (tempNode.isEnd() && tempNode.getNumChildren() >= 1 && i == word.length()-1) {
					tempNode.unsetEnd(); numWords--; isRemoved = true; break;
				}				
				i++;
			}
			
			int j = 0; 
			tempNode = root;
			while (j<word.length()) {
				char ch2 = word.charAt(j); isBranch = false;
				StrieNode tempNode2 = tempNode.getChild(word.charAt(j)); int k = j+1;
				while (k<word.length()) {
					tempNode2 = tempNode2.getChild(word.charAt(k));
					if (tempNode2.isEnd() || tempNode2.getNumChildren() > 1) {
						isBranch = true; break;
					}
					k++;
				}
				if (!isBranch && tempNode.getChild(ch2).getNumChildren()<=1 && tempNode.getChild(ch2).isEnd()) {
					tempNode.removeChild(ch2); numWords--; isRemoved = true; break;
				}
				tempNode = tempNode.getChild(ch2); j++;
			}
		}
		return isRemoved;
	}
	
	public String levelOrderTraversal(){
		
		String levelOrder = ""; 
		StrieNode tempNode = root;
		
		if (numWords()!=0) {
			LinkedLst<StrieNode> q = new LinkedLst<StrieNode>();
			
			for (Character ch:tempNode.getAllChildren().getKeys()) {
				q.addLast(tempNode.getChild(ch));
				
				if (levelOrder.equals(""))
					levelOrder += ch;
				else 
					levelOrder += " " + ch;
			}
			
			while (q.size()!=0) {
				
				if (tempNode == root)
					tempNode = q.removeFirst();
				
				if (tempNode.getNumChildren()!=0) {
					for (Character ch:tempNode.getAllChildren().getKeys()) {
						q.addLast(tempNode.getChild(ch)); levelOrder += " " + ch;
					}
				}
				tempNode = q.removeFirst();
			}
		}
		return levelOrder;
	}

	private void getStrieWords(LinkedLst<String> wordlst, StrieNode tempNode, String strie) {
		
		if (tempNode.getNumChildren()==0) {
			wordlst.addLast(strie); return;
		}
		
		else if (tempNode.getNumChildren()!=0) {
			
			if(tempNode.isEnd()) {
				wordlst.addLast(strie);
			}
			
			for(char ch: tempNode.getAllChildren().getKeys()) {
				String temp = strie; getStrieWords(wordlst, tempNode.getChild(ch), temp += ch);
			}
		}
	}
	 
	public LinkedLst<String> getStrieWords() {
		
		StrieNode tempNode = root; 
		String strie = ""; 
		LinkedLst<String> wordlst = null;
		
		if (numWords()!=0) {
			getStrieWords(wordlst = new LinkedLst<String>(), tempNode, strie);
		}
		return wordlst;
	}	
}
