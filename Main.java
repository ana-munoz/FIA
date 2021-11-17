package crow_2;

public class Main {
	public static void main(String[] args) {
		try {
			
			//KP.getInstance().type=6;
			MKP.getInstance().type=5;
            /*	Esto resuelve cualquier instancia
             * for (int i = 0; i < 7; i++) {
                //KP.getInstance().type = i;
                //System.out.println(KP.getInstance().type);
                 //MKP.getInstance().type = i;
                for (int j = 0; j < 30; j++) {
                    StdRandom.newSeed();
                    (new Flock()).execute();
                }
            }*/
			for (int j = 0; j < 30; j++) {
                StdRandom.newSeed();
                (new Flock()).execute();
			}
        } catch (Exception e) {
            System.err.println(String.format("%s \n%s", e.getMessage(), e.getCause()));
        }
    }
}
