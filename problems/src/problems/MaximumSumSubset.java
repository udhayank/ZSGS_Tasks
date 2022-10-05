package problems;

import java.util.Arrays;

public class MaximumSumSubset {

	public static void main(String[] args) {
		
		int nums[] = {1,-2,3,-4,20,1,-2,-3}; 
		new MaximumSumSubset().findSubset(nums);

	}
	
	public void findSubset(int[] nums) {
		
		int start = 0;
		int end = 1;
		
		int left = start;
		
		int currMax = nums[0] + nums[1];
        int maxSum = nums[0] + nums[1];
		
		for (int i=2; i<nums.length; i++){
			if ((currMax + nums[i]) > (nums[i] + nums[i-1])) {
				currMax += nums[i];
			} else {
				currMax = nums[i] + nums[i-1];
				left = i-1;
			}
            
            if (currMax > maxSum){
                maxSum = currMax;
                start = left;
                end = i;
            }
        }
		
		System.out.println(Arrays.toString(Arrays.copyOfRange(nums, start, end+1)));
		
	}

}
