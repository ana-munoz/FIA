package crow_2;

public class Crow {
	
	//public KP instance = KP.getInstance();
	public MKP instance = MKP.getInstance();
	private final int type = instance.type;
	protected final int nVariables = instance.numVariables[type];
    
    //X es la posición del cuervo
	private int[] x = new int[nVariables];
	
	//la mejor posición = pBest; depende de las variables que tenga
    protected int[] pBest = new int[nVariables];
    
    //la iteración del cuervo; aka cuervo seguido
    private double[] v = new double[nVariables];
    //AP
    double AP = 0.5;
    //Largo del vuelo
    float flightLength;
    //memoria
    //float[] memory; //probando con pBest

    public Crow() {
        for (int j = 0; j < nVariables; j++) {
            x[j] = StdRandom.uniform(2);
            v[j] = StdRandom.uniform();  
        }
        //memory = new float[nVariables];
    }

    protected boolean isBetterThanPBest() {
        return fitness() > fitnessPBest();
    }

    protected boolean isBetterThan(Crow g) {
        return fitnessPBest() > g.fitnessPBest();
    }

    protected void updatePBest() {
        System.arraycopy(x, 0, pBest, 0, x.length);
    }

    private float fitness() { 
        float beneficio = 0;
        for(int i=0; i < nVariables; i++) {
        	beneficio += x[i] * instance.prices[type][i];
        }
        return beneficio;
    }
    //esto es el fitness de la memoria
    private float fitnessPBest() {
    	float benefi = 0;
        for(int i=0; i < nVariables; i++) {
        	benefi += pBest[i] * instance.prices[type][i];
        }
        return benefi;
    }

    protected boolean isFeasible() {
    	 if(instance.problem == 1) 
             return isFeasibleCapability();
         else if (instance.problem == 2)
             return isFeasibleMultidimensionalCapability();
		return false;
    }

    private boolean isFeasibleCapability() {
    	float beneficio = 0;
        for(int i=0; i<nVariables; i++) {
        	//aquí se obtiene lo que pesa la solución
        	beneficio += x[i] * instance.pesos[type][0][i];
        }
        //pregunto: cabe?, sino, no es factible.
        return beneficio <= instance.capacidad[type][0];
    }

	private boolean isFeasibleMultidimensionalCapability() {
		int suma, i=0;
		boolean beneficio = true;
		while(i<instance.numMochilas[type] && beneficio) {
			suma=0;
			for(int j=0; j<nVariables; j++) {
				suma+= x[j] * instance.pesos[type][i][j]; 
			}
			beneficio = suma <= instance.capacidad[type][i];
			i++;
		}
		return beneficio;
		
    }

	/*protected void repare() {
		vealo el siguiente capítulo :D
    }*/

    protected void move(Crow followedCrow, float AP, float flightLenght) {
        
    	for (int j = 0; j < nVariables; j++) {
        	if (StdRandom.uniform() >= AP) {
        		x[j] = (int) (x[j] + StdRandom.uniform() * flightLength * (followedCrow.pBest[j]-x[j]));
        	} else {
        		x[j] = StdRandom.uniform(2);
        	}
        	x[j] = toBinary(x[j] + v[j]);
        	
            /* update velocity */
            /*v[j] = v[j] * w
                    + c1 * StdRandom.uniform() * (pBest[j] - x[j])
                    + c2 * StdRandom.uniform() * (g.pBest[j] - x[j]);*/
            /* update position */
            /*x[j] = toBinary(x[j] + v[j]);*/
        }
    }

	private float optimal() {
		return instance.optimal[type];
	}

	private float diff() {
		return optimal() - fitnessPBest();
	}

	private float rpd() {
		return diff() / optimal() * 100f;
	}

    protected void copy(Object object)  {
        if (object instanceof Crow) {
            System.arraycopy(((Crow) object).x, 0, this.x, 0, nVariables);
            System.arraycopy(((Crow) object).pBest, 0, this.pBest, 0, nVariables);
            System.arraycopy(((Crow) object).v, 0, this.v, 0, nVariables);
        }
    }

    private int toBinary(double x) {
        return StdRandom.uniform() <= (1 / (1 + Math.pow(Math.E, -x))) ? 1 : 0;
    }

    @Override
    public String toString() {
		return String.format(
			"optimal value: %.1f, pBest-fitness: %.1f, diff: %s, rpd: %.2f%%, pBest: %s", 
			optimal(), 
			fitnessPBest(), 
			diff(),
			rpd(),
			java.util.Arrays.toString(pBest)
		);
    }
}
