
public class QuebraLinha {
	static int larguraTela = 100;
	
	public static void main(String[] args) {
		
		char charBusca = ' ';
		int contaCaracteres = 0;
		int posicaoInicial = 0;
		int ultimaOcorrencia = 0;
		int posicaoAtual = 0;
		String texto = "";

		String linha = "Mario é contra ao tratamento precoce, cujo remédio tem como garoto propaganda o Lorde Jezziahs. Para Mario, é a mesma coisa que "
				+ "beber água artificial ou um suco de frutas do planeta de seu amigo Jabá da pizzaria The Hut. Sem efeito nenhum. Em Thera alguns "
				+ "são curados sem fazer nada. A maioria tem sintomas leves. Mas, os dois Klingdons foram tratados com placeb0K1na e não tiveram sorte.";
		
		System.out.println("Tamanho: " + linha.length());
		
		while((linha.length() - posicaoInicial ) > larguraTela) {
			while((posicaoAtual - posicaoInicial) < larguraTela ) { 
				ultimaOcorrencia = posicaoAtual; 
				posicaoAtual = linha.indexOf(charBusca, posicaoAtual + 1); 
				
				
			}

			
			texto = linha.substring(posicaoInicial, ultimaOcorrencia);
			imprimeCaracter(texto,10);

			posicaoAtual = ultimaOcorrencia + 1; 
			posicaoInicial = ultimaOcorrencia + 1;


		}
		texto = linha.substring(posicaoInicial);
		imprimeCaracter(texto,10);
		
		
	}
	
	
	private static void imprimeCaracter(String texto, int velocidade) {
		for(int i = 0; i < texto.length();i++) {
			char caracter = texto.charAt(i);
			System.out.print(caracter);
		}
		System.out.println("");
	}


}
