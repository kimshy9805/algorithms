import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Hash 안에 timestamp List를 만들어서 get이 불릴때마다 lower bound bs 로 value 찾기 
public class TimeBasedKeyValueStore {
	Map<String, List<Pair>> hash = new HashMap<>();
	
	
    public TimeBasedKeyValueStore() {
        
    }
    
    public void set(String key, String value, int timestamp) {
    	if (!hash.containsKey(key)) {
    		hash.put(key, new ArrayList<>());
    		hash.get(key).add(new Pair (value, timestamp));
    		return;
    	}
    	
    	hash.get(key).add(new Pair(value, timestamp));
    	
    }
    
    public String get(String key, int timestamp) {
    	if (!hash.containsKey(key)) {
    		return "";
    	}
    	
    	List<Pair> pairs = hash.get(key);
    	
    	int l = 0;
    	int r = pairs.size() - 1;
    	
    	while(l < r) {
    		int mid = l + (r-l)/2;
    		
    		if (timestamp <= pairs.get(mid).timestamp) {
    			r = mid;
    		} else {
    			l = mid + 1;
    		}
    	}
    	
    	return pairs.get(l).timestamp <= timestamp ? pairs.get(l).value : "";
    	
    	
    }
    
    class Pair {
    	String value;
    	Integer timestamp;
    	
    	Pair(String value, Integer timestamp) {
    		this.value = value;
    		this.timestamp = timestamp;
    	}
    }
    
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */