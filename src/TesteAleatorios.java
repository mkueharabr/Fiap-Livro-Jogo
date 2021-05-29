
public class TesteAleatorios {
	public enum CapituloJogo {
		INTRO (0), 
		LONGEDECASA(1), 
		AMAVEISKLINGDONS(2), 
		HOUSTON(3), 
		ZUMBI(4), 
		PLACEBOKINA(5),
		FINAL(6),
		BONUS01(7),
		CONFIG(8);
		
		private int indice;
		
		CapituloJogo(int indice){
			this.indice = indice;
		}
		
		public int getIndice() {
			return this.indice;
		}
	}
		
		
	public static void main(String[] args) {

		CapituloJogo capituloJogo = CapituloJogo.LONGEDECASA;
		
		String[] capitulo = {
				"MARIO NUMA GALÁXIA NÃO TÃO TÃO DISTANTE COMBATENDO O VIRUS ZUMBI",
				"Capítulo 01 - Longe de casa a mais de uma semana solar, parsecs e parsecs distante",
				"Capítulo 02 - Os 'amáveis Klingdons' e sua bebiba super batizada",
				"Capítulo 03 - Houston, we have a problem!",
				"Capítulo 04 - O vírus Zumbi",
				"Capítulo 05 - Placeb0K1na, placeb0K1na, placeb0k1na de Jezziahs"
		};
		
		System.out.println("capitulo " + capituloJogo.getIndice() + " : " + capitulo[capituloJogo.getIndice()]);

		
	}

}
