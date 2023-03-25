public class HashMap<K,V> {
	
	private ThreeTenHashSet<Pair<K,V>> hashTable;
	private static class Pair<K,V> {

		K key;
		V value;
		
		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object o) {
			if(o instanceof Pair) {
				Pair<K,V> pair = (Pair<K,V>)o;
				return pair.key.equals(key);  
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return key.hashCode();
		}
		
		@Override
		public String toString() {
			return "<" + key + "," + value + ">";
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
	}
	
	public HashMap(int initLength){
		hashTable = new ThreeTenHashSet<Pair<K,V>>(initLength);	
	}
	
	public boolean add(K key, V value) {
		if (key==null || value==null)
			return false;
			
		Pair<K,V> pair = new Pair<>(key, value);
		return hashTable.add(pair);
	}

	public boolean update(K key, V value) {
		if (key==null || value==null) 
			return false;
			
		Pair<K,V> pair = new Pair<>(key, value);
		if(!remove(key)) {
			return false;
		}
		return hashTable.add(pair);
	}
	
	public boolean remove(K key) {
		if (key==null)
			return false;
		
		Pair<K,V> pair = new Pair<>(key, null);
		return hashTable.remove(pair);
	}
	
	public int size() {
		return hashTable.size();
	}

	public boolean contains(K key){
		if (key==null)
			return false;
					
		return hashTable.contains(new Pair<>(key,null));
	}

	public boolean has(K key, V value){
		if (key==null)
			return false;
			
		Pair<K, V> pair = new Pair<>(key, null);
		return this.contains(key) && (hashTable.get(pair)).getValue().equals(value);
		
	}
		
	public V getValue(K key){
	
		if (this.contains(key)){
			Pair<K, V> pair = new Pair<>(key, null);
			return hashTable.get(pair).getValue();
		}
		else
			return null;
	}

	public SimpleList<K> getKeys(){
		SimpleList<Pair<K,V>> allValues = hashTable.allValues();
		SimpleList<K> keys = new SimpleList<>();
		
		for (Pair<K,V> pair: allValues){
			keys.addLast(pair.getKey());
		}
		return keys;
	}

	@Override
	public String toString() {
		return hashTable.toString();
	}
}