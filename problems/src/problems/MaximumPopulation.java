package problems;

import java.util.HashMap;
import java.util.Map.Entry;

public class MaximumPopulation {
	
	public static void main(String[] args) {
		
		int[][] yearSets = {{1900,1910},{1909,1935},{1920,1940},{1925,1940},{1938,1955},{1942,1956},{1950,2000},{1960,1970},{1965,1985},{1900,2000}};
//		int[][] yearSets = {{1910,1921},{1920,1930}};
//		new MaximumPopulation().printMaxPopulationYear(yearSets);
		new MaximumPopulation().printMaxPopulationYearUsingMap(yearSets);
		
	}
	
	public void printMaxPopulationYear(int[][] yearSet) {
		
		int[] population = new int[101];
		int max = 0;
		
		
		// fill population array
		for (int[] set:yearSet) {
			for (int i = set[0]-1900; i <= set[1]-1900; i++) {
				population[i]++;
				if (population[i] > max) {
					max = population[i];
				}
			}
		}
		
		// print max year
		for (int i=0; i<101; i++) {
			if (population[i] == max) {
				System.out.println(i+1900);
			}
		}
		
		
	}
	
	public void printMaxPopulationYearUsingMap(int[][] yearSets) {
		
		HashMap<Integer, Integer> population = new HashMap<>();
		int max = 0;
		
		// fill population map
		for (int[] set:yearSets) {
			for (int i=set[0]; i<=set[1]; i++) {
				population.put(i, population.getOrDefault(i, 0) + 1);
				if (population.get(i) > max) {
					max = population.get(i);
				}
			}
		}
		
		// print max year
		for (Entry<Integer,Integer> set:population.entrySet()) {
			if (set.getValue().equals(max)) {
				System.out.println(set.getKey());
			}
		}
		
	}
	
}
