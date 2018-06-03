package Jogo;

import Peça.*;

public interface Observado {
	
	public void add(Observador o);
	
	public void remove(Observador o);
	
	public int[][] getCasas();
	
	public Peça[][] getPeças();
	
}
