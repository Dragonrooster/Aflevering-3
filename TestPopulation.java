public class TestPopulation{
	public static void main(String[] args){
		
		City[] cities = CityGenerator.generate();
		Population pop = new Population( 0.05 );
		
		int k=0; 
		while(k<20){
			Individual i = new Individual(cities);
			pop.add( i );
			
			k++;
		}
		
		System.out.println( pop.size() );
		System.out.println( pop );
		
		pop.epidemic();
		
		System.out.println( pop.size() );
		System.out.println( pop );
	}
}