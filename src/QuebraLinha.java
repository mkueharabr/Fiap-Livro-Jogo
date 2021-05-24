import java.util.Scanner;

public class QuebraLinha {
	static int larguraTela = 120;
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		String justificativa;
		
		char charBusca = ' ';
		int contaCaracteres = 0;
		int posicaoInicial = 0;
		int ultimaOcorrencia = 0;
		int posicaoAtual = 0;
		String texto = "";

		String[] enredo = {
				"Durante a quarentena, Mario ficou muito doente. Os Klingdons têm costume de beber na garrafa mesmo e dividir com os outros.",
				"E agora nosso amigo tenta resistir bravamente na UTI onde se encontra sedado e inconsciente. Esse procedimento é totalmente "
			  + "normal e visa preservar todos os sinais vitais do paciente. Seu corpo é totalmente escaneado para identificar o problema.",
				"",
				"Os médicos estão em dúvida se o estado do paciente é por conta da bebida Abhysmum, que é altamente tóxicas ao humanos se consumida"
			  + "por um algum tempo e acabam com o fígado e outros órgãos ou se é por causa do vírus. Como a doença é recente, os médicos ainda não "
			  + "conhecem todos os sintomas que afetam o povo de Thera. E nenhum deles é de lá.",
				"",
				"As próximas horas serão cruciais para a sobrevivência do nosso amigo.",
				"",
				"Sabendo do histórico anterior dos Klingdons e antes do diagnóstico estar pronto e o plano de saúde autorizar os próximos procedimentos, "
			  + "os médicos dão as seguintes opções de tratamento:",
				"",
				"1 - Se for considerar a bebida, transplantar órgãos artificiais antes que comecem a falhar.",
				"2 - Se for o vírus, como Mario é de Thera, os médicos devem seguir o protocolo de lá e aconselham o tratamento precoce a base de placeb0K1na",
				"3 - Não faz nada no momento. Apenas monitaram o paciente, dão alguns medicamentos e esperam pela sua recuperação.",
				"",
				
		};			
		
		imprimeQuebra(enredo, 10);
		
		System.out.println("Coloque a sua opinião: ");
		justificativa = leitor.next();
		System.out.println("");
		System.out.println("justificativa: " + justificativa);
		
		leitor.close();
	
		
		
	}
	
	private static void imprimeQuebra(String[] srtTexto, int velocidade) {
		char charBusca = ' ';
		String texto;
		int posicaoInicial = 0;
		int ultimaOcorrencia = 0;
		int posicaoAtual = 0;
		
		for(String linha: srtTexto) {
			if(linha.length() > 0) {
				texto = linha;
				posicaoInicial = 0;
				ultimaOcorrencia = 0;
				posicaoAtual = 0;
				
				//System.out.println("posicaoInicial: " + posicaoInicial);
				//System.out.println("ultimaOcorrencia: " + ultimaOcorrencia);
				//System.out.println("posicaoAtual: " + posicaoAtual);
				
				while((linha.length() - posicaoInicial ) > larguraTela) {
					while(((posicaoAtual - posicaoInicial) < larguraTela) && (posicaoAtual >= 0) ) { 
						ultimaOcorrencia = posicaoAtual; 
						posicaoAtual = linha.indexOf(charBusca, posicaoAtual + 1); 
						
						
					}
					//System.out.println(" - posicaoInicial: " + posicaoInicial);
					//System.out.println(" - ultimaOcorrencia: " + ultimaOcorrencia);
					//System.out.println(" - posicaoAtual: " + posicaoAtual);
					
					texto = linha.substring(posicaoInicial, ultimaOcorrencia);
					
					//System.out.println("Texto:  " + texto);
					
					imprimeCaracter(texto,velocidade);

					posicaoAtual = ultimaOcorrencia + 1; 
					posicaoInicial = ultimaOcorrencia + 1;

				}
				texto = linha.substring(posicaoInicial);
				imprimeCaracter(texto,velocidade);
			} else {
				System.out.println("");
			}
			
		}
		
		

	}

	
	
	private static void imprimeCaracter(String linha, int velocidade) {
		int contaCaracteres = 0;
		

		if (linha == "") {
			System.out.println("");
			
		} else {
			contaCaracteres = 0;
			for(int i = 0; i < linha.length();i++) {
				char caracter = linha.charAt(i);
				contaCaracteres ++;

				if(contaCaracteres > larguraTela) {
					System.out.println("");
					contaCaracteres = 1;
				} 

				System.out.print(caracter);

			
			}
			System.out.println("");
		}

		
	}


}
