public class TestEventQueue{
    public static void main(String[] args){

        City[] cityarray = CityGenerator.generate();
        Individual john = new Individual(cityarray);

        //Testing if event queue is properly implemented
        EventQueue eq = new EventQueue();

        int i = 1;
        while( i <= 50 ){
            Event ev = new Event(Event.MUTATION, i, john);
            eq.add(ev);

            i++;
        }

        //Testing iterable implementation and next()
        for ( Event e : eq ) {
            System.out.println( (eq.next()).time() );
        }

        System.out.println(eq.next());
    }

}