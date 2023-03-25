public class HashSet<T> {

	private LinkedLst<T>[] table;
	private int numItems;

	@SuppressWarnings("unchecked")
	public HashSet(int initLength){
		if (!(initLength >= 2)) {
			initLength = 2;
		}
		this.table = new LinkedLst[initLength];
		this.numItems = 0;
	}

	public int capacity() {
		return table.length;
	}
	
	public int size() {
		return numItems; 
	}
	
	public boolean add(T value) {
		boolean isAdded = false;
		
		double s = (double) size(), c = (double) capacity();
		if ((s/c) >= 2.0) {
			rehash(capacity()*2);
		}
		
		else {
			int index = Math.abs(value.hashCode() % capacity());
			
			if (table[index] == null) { // initialize the address pointer of the array
				LinkedLst<T> n = new LinkedLst<>();
				n.addLast(value); table[index] = n; numItems++; isAdded = true;
			}
			
			else if (table[index] != null && !(contains(value))) {
				table[index].addLast(value); 
				numItems++; isAdded = true;
			}
		}
		return isAdded;
	}
	
	public boolean remove(T value) {
		
		boolean isRemoved = false;
		int index = Math.abs(value.hashCode() % capacity());
		
		if (contains(value) && table[index] != null) {
			table[index].remove(value); isRemoved = true; numItems--;
		}
		return isRemoved; 
	}
	
	public boolean contains(T value) {
		
		boolean isContained = false;
		int index = Math.abs(value.hashCode() % capacity());
		if (table[index] != null) {
			isContained = value.equals(table[index].get(value));	
		}
		return isContained; 
	}
	
	public T get(T value) {
		
		int n = 0;
		while (n<capacity()) {
			if (table[n] == null) {
				n++; continue;
			}
			else if (value.equals(table[n].get(value))) {
				return table[n].get(value);
			}
			n++;
		}		
		return null; 
	}
	
	@SuppressWarnings("unchecked")
	public boolean rehash(int newCapacity) {
		
		boolean isRehashed = false;
		if (newCapacity > capacity()) {
			LinkedLst<T> e = allValues();
			LinkedLst<T>[] tableTemp = new LinkedLst[newCapacity];
			this.table = tableTemp; this.numItems = 0;
			while (e.size()!=0) {
				T tempVal = e.removeFirst();
				add(tempVal);
			}
			isRehashed = true;
		}		
		return isRehashed;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("ThreeTenHashSet (non-empty entries):\n");
		for(int i = 0; i < table.length; i++) {
			if(table[i] != null && table[i].size()!=0) {
				s.append(i);
				s.append(" :");
				s.append(table[i]);
				s.append("\n");
			}
		}
		return s.toString().trim();
	}
	
	public String toStringDebug() {
		StringBuilder s = new StringBuilder("ThreeTenHashSet (all entries):\n");
		for(int i = 0; i < table.length; i++) {
			s.append(i);
			s.append(" :");
			s.append(table[i]);
			s.append("\n");
		}
		return s.toString().trim();
	}

	public LinkedLst<T> allValues(){
		// return all items in set as a list
		LinkedLst<T> all = new LinkedLst<>();
		for(int i = 0; i < table.length; i++) {
			if (table[i]!=null){
				for (T value: table[i])
					all.addLast(value);
			}
		}
		return all;
	}
}
