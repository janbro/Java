public class SeatingChartTester {
	public static void main(String[] args){
		String[][] statChart=new String[][]{new String[]{"Alejandro Munoz","Nick Munoz","Max Miller","Eric Rubio"}
													,{"David Onch","Julia Doscher","Katie Gandomi","Daniel Galvez"}
													,{"Gabi Buaglia","Gabriel Pererle","Juan Pamolina","Marcos Martinez"}
													,{"Martinez Achu","Ryan Buzon","Carlos Alberto","Dani Giraldo"}
			};
		SeatingChart stats=new SeatingChart(statChart);
		System.out.println(stats);
		stats.swap(0,3,1,2);
		System.out.println("Is Peter Lee here? "+stats.contains("Peter Lee"));
		stats.setSeat(1,0,"Peter Lee");
		System.out.println("Is Peter Lee here? "+stats.contains("Peter Lee")+"\n");
		stats.setSeat(1,1,"Robert Gitten");
		System.out.println(stats);
		stats.swap("Robert Gitten","Katie Gandomi");
		System.out.println(stats);
		System.out.println("Max Miller is at seat ("+stats.getRow("Max Miller")+","+stats.getCol("Max Miller")+").");
		System.out.println(stats.get(0,1)+" is at seat (0,1).\n");
		
		SeatingChart a=new SeatingChart(2,4);
		System.out.println(a);
		a.setSeat(0,0,"A Person");
		System.out.println(a);
	}
}
