/**
 * Cellular Automata Lab (Template Classes and Test Cases)
 * These are the template classes and test cases for the Cellular
 * Automata Lab. Written for the Woodstock School in Mussoorie,
 * Uttarakhand, India.
 *
 * @author Jeffrey Santos
 * @version 1.0
 */

public class CellularAutomata {
  public static void main(String[] args) {
    //  Tests for CellularAutomaton handling of n, the number of cells.
    //   Output: 11 cells, with the center cell filled.
    System.out.println(new CellularAutomaton(11));
    //   Output: 21 cells, with the center cell filled.
    System.out.println(new CellularAutomaton(21));
    //   Output: 51 cells, with the center cell filled.
    System.out.println(new CellularAutomaton(51));

    //  Tests for the evolve() method.
    //   Output: The following lines should print the sample evolution from
    //           the Background section of the lab.
    CellularAutomaton ca1 = new CellularAutomaton(15);
    System.out.println(ca1);
    ca1.evolve();
    ca1.evolve();
    ca1.evolve();

    //   Output: The following lines should print identical output as those
    //           above.
    CellularAutomaton ca2 = new CellularAutomaton(15, 90);
    System.out.println(ca2);
    ca2.evolve();
    ca2.evolve();
    ca2.evolve();

    //   Output: The following lines should print the solution to Activity #1,
    //           Question #2.
    CellularAutomaton ca3 = new CellularAutomaton(23, 57);
    System.out.println(ca3);
    for (int i = 1; i < 15; i++)
      ca3.evolve();

    //   Output: The following lines will produce similar starting output as
    //           the sample given in the Background; however, the edge cases
    //           will begin to shift the pattern once they begin being filled.
    WrappingCellularAutomaton wca1 = new WrappingCellularAutomaton(15, 90);
    System.out.println(wca1);
    for (int i = 1; i < 15; i++)
      wca1.evolve();

    //   Output: The following lines will produce similar output as the
    //           sample given in the background; however, the edge cases will
    //           be handled according to the special rule set.
    SpecialCaseCellularAutomaton scca1 = new SpecialCaseCellularAutomaton(15, 90, 11);
    System.out.println(scca1);
    for (int i = 1; i < 15; i++)
      scca1.evolve();
  }
}

class CellularAutomaton {
  public int[] cells;        //  array to hold the current cell states
  public int[] rules;        //  array to hold the current rule values

  /**
   * Initializes a CellularAutomaton of array size n. The rules should be
   * populated according to Rule #90 from the background section of the lab.
   * Additionally, the center cell should be turned 'on' (initialized to 1).
   *
   * @param n The size of the array of cells.
   *  Precondition: n > 0
   */
   public CellularAutomaton(int n) {
   cells = new int[n];
   int[] rule90 = {0, 1, 0, 1, 1, 0, 1, 0};
   rules = rule90;
   cells[n/2] = 1;
   }
  /**
   * Initializes a CellularAutomaton of array size n with a rule set in
   * accordance to the given rule number. Additionally, the center cell should
   * be turned 'on' (initialized to 1).
   *
   * @param n The size of the array of cells.
   *  Precondition: n > 0
   * @param ruleN The denary rule number the cellular automaton should follow.
   *  Precondition: 0 <= ruleN <= 255
   */
   public CellularAutomaton(int n, int ruleN) {
	   cells = new int[n];
	   rules =  getRulePattern(ruleN);
   if (n % 2 == 0) {
	   cells[n/2] = 1;
	   }
	   else cells[(n/2) + 1] =1;
   }

  /**
   * Evolves the current set of cells according to the stored rule set and
   * prints the result.
   */
  public void evolve() {
    cells = getNextGeneration();
    System.out.println(toString());
  }

  /**
   * Helper method to return the denary value representing the given cell's
   * neighbourhood.
   *
   * @param index  The index of the cell to get the neighbourhood value of.
   *  Precondition: index is valid for the current group of cells.
   * @return  A denary value representing the given cell's neighbourhood.
   */
  public int getNeighbourValue(int index) {
   if(index == 0) {
	   return(2* cells[index] + cells[index+1]);
	   
   }
   else if (index == cells.length - 1) 
	   return (4*cells[index-1] + 2*cells[index]);
   else {
	   return (4*cells[index-1] + 2 * cells[index] + cells[index+1]);
   }
   
  }


  /**
   * Helper method to return an array representing the next generation of cells.
   *
   * @return  An array representing the next generation of cells.
   */
  public int[] getNextGeneration() {
   int[] arr = new int[cells.length];
   for (int i = 0; i < cells.length-1; i++) {
	   arr[i] = rules[7-getNeighbourValue(i)];
	   
   }
 return arr;
  }
  

  /**
   * Helper method to return an array representing the rule set from a given
   * denary rule number.
   *
   * @param ruleN A denary value representing a rule number.
   *  Precondition: 0 <= ruleN <= 255
   * @return  An array representing the rule set from the given denary rule
   *          number.
   */
   public int[] getRulePattern(int ruleN) {
   int[] arr = new int[8];

 if(ruleN>=128){
	 arr[0] = 1;
	 ruleN = ruleN-128;
 } 
	 if(ruleN>=64){
		 arr[1] = 1;
		 ruleN = ruleN-64;
	 }
	 if(ruleN>=32){
	 arr[2] = 1;
	 ruleN = ruleN-32;
	 }else arr[2] = 0;
	 if(ruleN>=16){
	 arr[3] = 1;
	 ruleN = ruleN-16;
	 }else arr[3] = 0;
	 if(ruleN>=8){
		 arr[4] = 1;
	 ruleN = ruleN-8;
	 }else arr[4] = 0;
	 if(ruleN>=4){
	 arr[5] = 1;
	 ruleN = ruleN-4;
	 }else arr[5] = 0;
	 if(ruleN>=2){
	 arr[6] = 1;
	 ruleN = ruleN-2;
	 } else arr[6] = 0;
	 if(ruleN>=128){
	 arr[7] = 1;
	 ruleN = ruleN-1;
	 }else arr[7] = 0;
	return arr;
	 }
   
   
     
   

  /**
   * Override of the toString method for printing the cellular automaton.
   *
   * @return  A string giving a visual representation of the current group of
   *          cells in the cellular automaton.
   */
  public String toString() {
    String output = "";
    for (int i = 0; i < cells.length; i++)
      if (cells[i] == 0)
        output += (char)0x25A2;
      else
        output += (char)0x25A3;
    return output;
  }
}

class WrappingCellularAutomaton extends CellularAutomaton {
  public WrappingCellularAutomaton(int n) {
    super(n);
  }

  public WrappingCellularAutomaton(int n, int ruleN) {
    super(n, ruleN);
  }
  public int getNeighbourValue(int index) {
	   if(index == 0) {
		   return(4* cells[cells.length-1]+2* cells[index] + cells[index+1] );
		   
	   }
	   else if (index == cells.length - 1) 
		   return (4*cells[index-1] + 2*cells[index]+ cells[0]);
	   else {
		   return (4*cells[index-1] + 2 * cells[index] + cells[index+1]);
	   }
	   
	  }


}

class SpecialCaseCellularAutomaton extends CellularAutomaton {
  public int[] specialRules;
  public SpecialCaseCellularAutomaton(int n, int ruleN, int specialN) {
    super(n, ruleN);

     specialRules = getSpecialPattern(specialN);
  }

  public int[] getSpecialPattern(int ruleN) {
	   int[] arr = new int[4];
	   if(ruleN>=8){
			 arr[0] = 1;
			 ruleN = ruleN-8;
		 } 
	   if(ruleN>=4) {
		   arr[1] = 1;
				   ruleN = ruleN-4;
	   }
	   if(ruleN>=2) {
		   arr[2] = 1;
				   ruleN = ruleN-2;
	   } 
	   if(ruleN>=1) {
		   arr[3] = 1;
				   ruleN = ruleN-1;
	   }
	   return arr;
  }
  public int getNeighbourValue(int index) {
	   if(index == 0) {
		   return(2* cells[index] + cells[index+1]);
		   
	   }
	   else if (index == cells.length - 1) 
		   return (2*cells[index-1] + cells[index]);
	   else {
		   return (4*cells[index-1] + 2 * cells[index] + cells[index+1]);
	   }
	   
	  }
  public int[] getNextGeneration() {
	   int[] arr = new int[cells.length];
	   for (int i = 0; i < cells.length; i++) {
		   
		if(i ==0 || i == cells.length-1) {
			arr[i] = specialRules[3-getNeighbourValue(i)];
		}
		else arr[i] = rules[7-getNeighbourValue(i)];
	   }
			   return arr;
	  }
}
