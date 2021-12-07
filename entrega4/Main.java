package crow_2;

public class Main {
	public static void main(String[] args) {
		try {			
			/* UNA INSTANCIA*/
			
			 //KP.getInstance().type=4;
			  MKP.getInstance().type=3;
			  
			  for (int j = 0; j < 30; j++) { 
				  StdRandom.newSeed(); 
				  (new Flock()).execute();
			  }
				 
			
			/* TODAS LAS INSTANCIAS*/
			
			  /*for (int i = 0; i <= 7; i++) { 
				  //KP.getInstance().type = i;
				  //System.out.println(KP.getInstance().type); 
				  	MKP.getInstance().type = i;
				  	System.out.println(MKP.getInstance().type); 
				  for (int j = 0; j < 30; j++) {
					  StdRandom.newSeed(); 
					  (new Flock()).execute(); 
					  } 
				  }*/
			 
			 
        } catch (Exception e) {
            System.err.println(String.format("%s \n%s", e.getMessage(), e.getCause()));
        }
    }
}
