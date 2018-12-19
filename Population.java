import java.util.ArrayList;
public class Population{
	
	public static double omega;
	private ArrayList<Individual> populationList;
	public static double costMin;
	private static City[] bestPath;
	public int minCapacity = 10;
	
	// Creates an empty population.
	public Population(double omega){
		
		this.omega = omega;
		
		populationList = new ArrayList<Individual>();
	}
	
	// Adds an Individual to the population.
	public void add( Individual i ){
		
		populationList.add( i );
		
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
	
	
	// Check if individual i is in the population.
	public boolean contains(Individual i){
		
		int k = 0;
		while( k < size() ){
			
			if( populationList.get( k ).equals( i ) ){
				return true;
			}

			k++;
		}
		return false;
	}
	

	// Removes individual i if i exists in the population
	public void remove( Individual i ){
		
		populationList.remove( i ); 
	}
	
	
	//Returns the number of individuals in the population
	public int size(){
		
		return populationList.size();
	}
	
	/*
	* Models an epidemic. Precondition: population size must be at least 10.
	*/
	public void epidemic(){
		
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

	
	//Moves the 5 fittest individuals in the population to the last 5 indexes of the arraylist.
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
	
	
	//Moves the 5 least fit individuals in the population to the first 5 indexes of the arraylist.
	private void find5Weakest(){
		int i = 0;
		while( i < 5 ){
			
			int k = 1;
			while( k < size() ){
				
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
	
	// Returns the fitness of individual i.
	public double fitness(Individual i){
		
		return ( omega + ( ( ( (costMin)/(i.cost()) )*( ( (costMin)/(i.cost()) ) ) ) )/(1.0+2.0*omega) );
	}
	
	
	//Returns best path of this population.
	public City[] bestPath(){
		return this.bestPath;
	}
	
	
	//Returns a strin representation of the population.
	public String toString(){
		
		String str = "";
		
		int k = 0;
		while( k < size() ){
			str = str + " Fitness: " + fitness( populationList.get( k ) );
			
			k++;
		}
		return str;
	}



} 

