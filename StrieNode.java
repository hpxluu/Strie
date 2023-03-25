public class StrieNode{

	private HashMap<Character, StrieNode> children; 
	private boolean endMarker;  
	private boolean flag;  	
	private static final int INIT_MAP_LENGTH = 5;
	
	public StrieNode() {
		this.children = new HashMap<Character, StrieNode>(INIT_MAP_LENGTH);
		this.endMarker = false; 
		this.flag = false;
	}
	
	public int getNumChildren(){
		return children.size();
	}
	
	public HashMap<Character, StrieNode> getAllChildren(){
		return children;
	}

	public void setEnd(){
		this.endMarker = true; 
		
	}
	
	public void unsetEnd(){
		this.endMarker = false;
	}
	
	public boolean isEnd(){
		return endMarker; 	
	}
	
	public boolean containsChild(char ch){		
		return children.contains(ch); 
	}
	
	public StrieNode getChild(char ch){
		return children.getValue(ch);
	}
	
	public void putChild(char ch, StrieNode node){
		if (containsChild(ch))
			children.update(ch, node);
		else if (!containsChild(ch)) {
			children.add(ch, node);
		}
	}
	
	public boolean removeChild(char ch){
		return children.remove(ch);
	}
	
	public void setFlag(){
		this.flag = true;
	}
	
	public void unSetFlag(){
		this.flag = false;
	}
	
	public boolean checkFlag(){
		return flag;	
	}
}
