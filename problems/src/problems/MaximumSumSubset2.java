package problems;

import java.util.Arrays;

public class MaximumSumSubset2 {
	
	static int[] maxArray;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) {
		
		int nums[] = {1,-2,3,-4,20,1,-2,-3}; 
		new MaximumSumSubset2().findMaximumSubset(nums);
		System.out.println(Arrays.toString(maxArray));
		System.out.println(max);

	}
	
	public void findMaximumSubset(int[] nums) {
		
//		System.out.println(Arrays.toString(nums));
		
		int currMax = sum(nums);
		if (currMax > max) {
			maxArray = nums;
			max = currMax;
		}
		
		if (nums.length == 2) {
			return ;
		}
		
		findMaximumSubset(Arrays.copyOfRange(nums, 0, nums.length-1));
		
		findMaximumSubset(Arrays.copyOfRange(nums, 1, nums.length));
		
		
	}
	
	public int sum(int[] nums) {
		
		int sum = 0;
		for (int n:nums) {
			sum += n;
		}
		return sum;
		
	}

}
