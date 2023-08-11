package ClassicalDP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BitwiseORsOfSubarrays {
	
	public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> ans = new HashSet<>();
        Set<Integer> prevORs = new HashSet<>();

        for (int i : arr) {
            Set<Integer> currORs = new HashSet<>();
            currORs.add(i);

            for (int j : prevORs) {
                currORs.add(i | j);
            }

            prevORs = currORs;
            ans.addAll(prevORs);
        }

        return ans.size();
	}
	
	static Set<Integer> set = new HashSet<>();
	
	
    public int subarrayBitwiseORs1(int[] arr) {
    	dfs(0, arr, new ArrayList<>(), 0);
    	return set.size()-1;
    }
    
	
	private int dfs(int i, int[] ar, List<Integer> currList, int currBit) {
		if (i >= ar.length) {
			System.out.println(currList + " " + currBit);
			set.add(currBit);
			return currBit;
		}
		
		// add
		currList.add(ar[i]);
		dfs(i+1, ar, currList, currBit | ar[i]);
		currList.remove(currList.size()-1);
		
		// skip
		dfs(i+1, ar, currList, currBit);
		
		return currBit;
	}
    
    
    
    
}
