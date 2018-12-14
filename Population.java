import java.util.ArrayList;
public class Population{
	
	public static double omega;
	private ArrayList<Individual> populationList;
	private int counter = 0;
	public static double costMin;
	private static City[] bestPath;
	public int minCapacity = 10;
	
	/*								KOMMENTARER
	 * Et eller andet sted skal individet få en fitness spørgsmålet er hvor?
	 *
	 *
	 */
	 
	
	
	// constructor build an empty arraylist population that can hold Individuals 
	public Population(double omega){
		
		this.omega = omega;
		
		populationList = new ArrayList<Individual>();
	}
	
	// Adds an Individual to the arraylist population and saves the bestPath and it's cost. 
	public void add( Individual i ){
		
		populationList.add( i ); //add'er den index eller element
		
		if( size() == 1 ){
			this.costMin = i.cost();
			this.bestPath = i.path();
		}
		else if( costMin > i.cost() ){
			this.costMin = i.cost();
			this.bestPath = i.path();
		}
		
		if( size() == minCapacity ){
			populationList.ensureCapacity( 2*minCapacity );
			minCapacity = 2*minCapacity;
		}
	}
	
	
	// Check if individual i is in the population
	public boolean contains(Individual i){
		
		if( populationList.isEmpty() || populationList.size() < counter ){
			counter=0; //ikke sikker på den her
			return false;
		}
		else{
			counter++;
			return ( i.equals( populationList.get( counter ) ) ) ? true : contains( i );
		}
	}
	

	// Removes individual i if i exist and reevaluates if costMin is changed (Det her laver en liste med en helt masse tomme index som jeg forstår Lui siger nej)
	public void remove( Individual i ){
		
		populationList.remove( i ); 
	}
	
	
	//returns the number of individual in the population
	public int size(){
		
		return populationList.size(); // see: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
	}
	
	//models an epidemic
	public void epidemic(){
		
		if(size() < 10){
			throw new RuntimeException("Population size must be at least 10 to model an epidemic");
		}
		
		find5Fittest();
		find5Weakest();
		
		int k = 0;
		while( k < 5 ){
			populationList.remove( k );
			k++;
		}
		
		k = 0;
		while( k < size() - 5 ){
			if( RandomUtils.getRandomEvent( 1 - ( fitness( populationList.get( k ) ) * fitness( populationList.get( k ) ) ) ) ){
				populationList.remove( k );
			}
			
			k++;
		}
	}

	
	//Moves the 5 fittest individuals in the population to the last 5 indexes
	private void find5Fittest(){
		
		int i = 0;
		while( i < 5 ){
			
			int k = 0;
			while( k + 1 < size() ){
				
				if( fitness( populationList.get( k ) ) > fitness( populationList.get( k + 1 ) ) ){
					
					Individual temp = populationList.get( k );
					populationList.set( k, populationList.get( k + 1 ) );
					populationList.set( k + 1, temp );
					
				}
				
				k++;
			}
			
			i++;
		}
		
	}
	
	
	//Moves the 5 least-fittest individuals in the population to the first 5 indexes
	private void find5Weakest(){
		int i = 0;
		while( i < 5 ){
			
			int k = 0;
			while( k + 1 < size() ){
				
				if( fitness( populationList.get( size() - k ) ) < fitness( populationList.get( size() - k - 1 ) ) ){
					
					Individual temp = populationList.get( size() - k );
					populationList.set( size() - k, populationList.get( size() - k - 1 ) );
					populationList.set( size() - k - 1, temp );
					
				}
				
				k++;
			}
			
			i++;
		}
	}
	
	// Returns the fitness of individual i
	public static double fitness(Individual i){
		
		return ( omega + ( ( ( (costMin)/(i.cost()) )*( ( (costMin)/(i.cost()) ) ) ) )/(1.0+2.0*omega) );
	}
	
	
	//Returns best path of this population.
	public City[] bestPath(){
		return this.bestPath;
	}
} 

