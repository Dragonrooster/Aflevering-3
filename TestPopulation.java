public class TestPopulation{
	public static void main(String[] args){
		
	//Creates population pop and adds Individuals to pop
		City[] cities = CityGenerator.generate();
		Population pop = new Population( 0.05 );
		
		int k=0; 
		while(k<20){
			Individual i = new Individual(cities);
			pop.add( i );
			
			k++;
		}
		
	//Prints Population pop, the size of pop and the best path in pop and its cost. 
		System.out.println( pop );
		
		System.out.println();
		System.out.println();
	
		System.out.println("The initial size of the Population is " + pop.size() );
		
		System.out.println();
		System.out.println();
		
		System.out.println("The best path found is:");
		
		int l = 0;
		while( l < pop.bestPath().length ){
			
			System.out.print( (pop.bestPath()[l]).name());
			
			if( l < pop.bestPath().length - 1 ){
				System.out.print(" -> ");
				
			}
			l++;
		}	
		
		double distance = 0;
		
		int j = 0;
		while( j < pop.bestPath().length - 1 ) {
			distance += (pop.bestPath()[j]).distanceTo((pop.bestPath()[j+1]));
			j++;
		}
		
		distance += (pop.bestPath()[0]).distanceTo((pop.bestPath()[ pop.bestPath().length - 1 ]));
		
		System.out.println();
		System.out.println( "The cost for the best path is " + distance );
		
	//Creates an epidemic, prints pop and pop's size after epidemic
		
		pop.epidemic();
		
		System.out.println();
		System.out.println( "An epidemic occurred." );
		
		System.out.println( "Size after epidemic " + pop.size() );
		
		System.out.println();
		
		System.out.println( "The popaltion after the epidemic " );
		
		System.out.println( pop );
		
	//Creates two new populations and adds two individuals to the first population.
		
		Population pop1 = new Population( 0.05 );
        Population pop2 = new Population( 0.05 );
		
        Individual one = new Individual( cities );
		Individual two = new Individual( cities );

		pop1.add( one );
		pop2.add( two );

		EventQueue eq1 = new EventQueue();
		Event ev1 = new Event( Event.MUTATION , 1 , one );
		Event ev2 = new Event( Event.MUTATION , 1 , two );
		
		System.out.println();
		System.out.println();
		
	// Check if individual one is in pop1 and pop2
		System.out.println("It is " + (pop1.contains(one)) + " that Individual one exists in Population1");
        System.out.println("It is " + (pop1.contains(two)) + " that Individual two exists in Population1");
        System.out.println("It is " + (pop2.contains(two)) + " that Individual two exists in Population2");
		System.out.println("It is " + (pop2.contains(one)) + " that Individual one exists in Population2");
		
		System.out.println();
		System.out.println();
		
	// Removes Individual one from pop1 and pop2 
		
		pop1.remove( one );
		System.out.println( "Removing one from pop1. Size of pop1: " + pop1.size() );
		pop2.remove( one );

	}
}