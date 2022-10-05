package problems;

public class NumberToWords {

	public static void main(String[] args) {
		
		System.out.println(new NumberToWords().convertToWords(191));		

	}
	
	public String convertToWords(int num) {
		
		String[] digitMap = {"","one","two","three","four","five","six","seven","eight","nine"};
		String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "nineteen"};
		String[] tens = {"", "ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
		
		
		int[] digits = new int[3];
		String words = "";
		
		for (int i=2; i>=0; i--) {
			digits[i] = num%10;
			num /= 10;
		}
		
		words += digitMap[digits[0]] + " hundred";
		
		if (digits[2]==0) {
			if (digits[1] == 0) {
				return words;
			}
			words += " and " + tens[digits[1]];
			return words;
		}
						
		if (digits[1] == 1) {
			words += " and " + teens[digits[2]];
			return words;
		}
		
		words += " and " + tens[digits[1]] + " " + digitMap[digits[2]];
		return words;		
		
		
	}

}
