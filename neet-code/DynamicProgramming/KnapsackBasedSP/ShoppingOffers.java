package KnapsackBasedSP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingOffers {

		
	
	
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        
    	Map<List<Integer>, Integer> dp = new HashMap<>();
    	
    	return dfs(price, special, needs, dp);
    	
    }
	
    
    private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> dp) {
    	
    	if (dp.containsKey(needs)) {
    		return dp.get(needs);
    	}
    	
    	// precompute minPrice
    	int minPrice = 0;
    	for (int i = 0 ; i < needs.size(); i++) {
    		minPrice += price.get(i) * needs.get(i);
    	}
    	
    	
    	for (List<Integer> offer: special) {
    		// if within needs 
    		boolean isValid = true;
    		
    		// check if needs.get(i) >= offer.get(i);
    		for (int i = 0; i < needs.size(); i++) {
    			if (needs.get(i) - offer.get(i) < 0) {
    				isValid = false;
    				break;
    			}
    		}
    		
    		if (isValid) {
    			List<Integer> temp = new ArrayList<>();
    			for (int i =0; i < needs.size(); i++) {
    				temp.add(needs.get(i) - offer.get(i));
    			}
    			
    			minPrice = Math.min(minPrice, offer.get(offer.size()-1) + dfs(price, special, temp, dp));
    		}
    	}
    	
    	dp.put(needs, minPrice);
    	
    	return minPrice;
    	
    }
	 
    
    
    
    
    
    
    
    
}
