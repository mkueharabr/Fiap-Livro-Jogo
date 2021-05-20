import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FiapLivroJogo {
/*
 * 	Autor: Marcelo Kenji Uehara
 * 
 *  Nome do Jogo: Mario numa galáxia distante combatendo o virus zumbi
 *  
 *  Atividade realizada no módulo de Lógica de Programação de imersão Java Xpert
 *  
 */

	private static int idadeGamer;
	private static String nomeGamer;
	private static final int idadeMinimaGamer = 16;
	private static boolean continuaSaga = true;
	
	private static Scanner leitor = new Scanner(System.in);
	
	private static enum StatusSaude {
		VIVO, MORTO, DOENTE, ZUMBI
	}
	
	private static StatusSaude statusAtual;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		statusAtual = StatusSaude.VIVO;
		String resposta;
		
		continuaSaga = apresentacao();
		
		if (continuaSaga) {
			limpaTela();
			abertura();
			
			while(continuaSaga) {
						
				limpaTela();	
				cenaKlingdonSentarJunto();
				
				limpaTela();
				continuaSaga = cenaKlingdonNaMesa();
				
				rolarTela(10,0);
				imprimeTexto(gameOver());
				
				System.out.println("Sua saga chegou a um final. Você gostaria de continuar e conhecer outras possibilidades?");
				System.out.println("Digite (S - Sim, N - Não): ");
				resposta = leitor.next();		
				
				if (resposta.equalsIgnoreCase("N")) {
					continuaSaga = false;
				} else {
					continuaSaga = true;
				}
			}
			
		} else {
			rolarTela(10,0);
			imprimeTexto(gameOver());
			
		}
		
		leitor.close();

	}
	
	private static boolean apresentacao() throws IOException, InterruptedException {
		
		String resposta;
		boolean	continuar = true;
		
		limpaTela();
		
		System.out.println("Olá, sou Jarvis, seu assistente virtual. Antes de começarmos preciso de algumas");
		System.out.println("informações pessoais.");
		System.out.println("");
		
		System.out.print("Por favor, digite o seu nome: ");
		nomeGamer = leitor.next();
		
		System.out.print("E agora a sua idade: ");
		idadeGamer = leitor.nextInt();
		
		if(idadeGamer < idadeMinimaGamer) {
			limpaTela();
			
			System.out.println("Olá " + nomeGamer + ". Infelizmente esse jogo não é recomendado para menores de " + 
					idadeMinimaGamer + " anos.");
			continuar = false;

			
		} else {
			limpaTela();
			
			System.out.println(nomeGamer + ", esse jogo não é recomendado para pessoas sensíveis, nervosas ou alérgicas ao modo texto do DOS. ");
			System.out.println("");
			System.out.println("Se esse for o seu caso temos as seguintes opções:");
			System.out.println("S - Para sair");
			System.out.println("C - para continuar");
			System.out.println("");
			System.out.print("Digite a sua decisão: ");
			resposta = leitor.next();
			
			if(resposta.toUpperCase().startsWith("S")) {
				limpaTela();
				System.out.println("Obrigado por sua sábia decisão.");
				continuar = false;

			}
		}

		return continuar;
	}

	private static void pausaMiliSegundos(int miliSeg) {
		try {
			TimeUnit.MILLISECONDS.sleep(miliSeg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	private static void limpaTela() throws IOException, InterruptedException {
		if (System.getProperty("os.name").contains("Windows")) {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} else {
			Runtime.getRuntime().exec("clear");
		}
            
	}
	
	private static void rolarTela(int linhas, int tempoMiliSeg) {
		for(int i = 0; i <= linhas; i++) {
			System.out.println("");
			pausaMiliSegundos(tempoMiliSeg);
		}
	}
	
	private static void imprimeTexto(String[] texto) {
		for (String linha : texto) {
			if (linha == "") {
				pausaMiliSegundos(2000);
			}
			System.out.println(linha);
		}
	}
	
	private static void imprimeCaracter(String[] texto, int velocidade) {
		for (String linha : texto) {
			if (linha == "") {
				System.out.println("");
				pausaMiliSegundos(velocidade * 100);
			} else {
				for(int i = 0; i < linha.length();i++) {
					char caracter = linha.charAt(i);
					System.out.print(caracter);
					pausaMiliSegundos(velocidade);
				}
				System.out.println("");
			}

		}		
	}
	
	
	private static String[] thera() {
		String[] thera = {
				"                             *    .-'.  ':'-.",
				"                                .''::: .:    '.             *",
				"                               /   :::::'      \\",
				"                    *         ;.    ':' `       ;",
				"                              |       '..       |",
				"                              ; '      ::::.    ;",
				"                               \\       '::::   /",
				"                                '.      :::  .'",
				"jgs                                '-.___'_.-'       *"
		};
		
		return thera;
		
	}
	
	private static String[] gameOver() {
		String[] gameOver = {
				"                    _______      ___      .___  ___.  _______ ",
				"                   /  _____|    /   \\     |   \\/   | |   ____|",
				"                  |  |  __     /  ^  \\    |  \\  /  | |  |__   ",
				"                  |  | |_ |   /  /_\\  \\   |  |\\/|  | |   __|  ",
				"                  |  |__| |  /  _____  \\  |  |  |  | |  |____ ",
				"                   \\______| /__/     \\__\\ |__|  |__| |_______|",
				"                                                              ",
				"                   ______   ____    ____  _______ .______      ",
				"                  /  __  \\  \\   \\  /   / |   ____||   _  \\     ",
				"                 |  |  |  |  \\   \\/   /  |  |__   |  |_)  |    ",
				"                 |  |  |  |   \\      /   |   __|  |      /     ",
				"                 |  `--'  |    \\    /    |  |____ |  |\\  \\----.",
				"                  \\______/      \\__/     |_______|| _| `._____|" ,
				"",
				"                       ASCII Art by www.network-science.de"   
		};
		
		return gameOver;		    
	}
	
	private static void abertura() {
		
		String[] titulo = {"                  MARIO NUMA GALÁXIA DISTANTE COMBATENDO O VIRUS ZUMBI"};
		String[] intro = {"Há muito tempo atrás numa galáxia não tão distante ..."};
		String [] resumo = {"Mario, o nosso herói, é um engenheiro encanador quântico que vive num planeta azul",
							"conhecido com Thera, onde mora com a família e pets num reino chamado Cattleland.",
							"",
							"Thera e suas 4 luas ficam a algumas centenas de parsecs de distância de Babooine. No reino de",
							"Cattleland, algumas pessoas ainda acreditam que o planeta é plano e em outras teorias",
							"conspiratórias, como a de que o novo vírus zumbi desconhecido ser criação do Império Klingdon.",
							"Well... convenhamos, uma coisa não tem nada a ver com a outra meus amigos!",
							"",
							"E também do fato ou fake que o rei mitológico Jeziahs é da linhagem sombria da força! Sera?",
							"Lord Jeziahs e seus fiéis súditos parecem viver em um outro mundo, onde tudo está em perfeita harmonia",
							"e que a ameaça do vírus está sob controle, afinal eles tem em mãos a cura para toda a galáxia:",
							"a poderosa placeb0K1na. Caso não tome você corre o risco de virar Dhanos, King G. Roll ou até o Browser!",
							"",
							"Mas, a poderosa Federação Galática da Saúde tem fortes evidências científicas que a mesma é", 
							"responsável em matar seletivamente alguns seres pois, é fabricado com nano robôs dotados IA.",
							"",
							"Chega de spoilers... Para falar a verdade, poucos irão ler até aqui. Se você chegou até esse ponto",
							"meus sinceros agradecimentos ;). Você é um guerreiro. Enfim, esse é o cenário atual que vive nosso herói.",
							"",
							nomeGamer + ", essa missão caso aceite-a, você decidirá o que é fato ou fake, qual caminho deve ser tomado",
							"além do destino dele e de todos os habitantes da galáxia e desse reino não tão tão distante!",
							"",
							nomeGamer + ", se vc for capturado, morto ou virar zumbi, negaremos tudo perante à nação.",
							"Boa sorte! Bom jogo e que a força... da decisão esteja com vc!",
							"",
							"Alerta: Essa é uma obra de ficção. E qualquer personagem, lugar, acontecimentos citados aqui não",
							"foram baseados em fatos reais! Esse jogo NÃO faz referências a nenhum filme. É pura ficção... científica!",
							"Podemos garantir que durante os seus " + idadeGamer + " anos de vida você não viu nada igual.",
							"",
							"Para uma melhor experiência gere o .jar e execute-o na linha de comando."
		};
		
	
		imprimeCaracter(intro, 10);
		System.out.println("");
		
		pausaMiliSegundos(1000);
		
		imprimeCaracter(titulo,10);
		System.out.println("");
		
		pausaMiliSegundos(1000);
		
		imprimeCaracter(resumo, 5);
		
		pausaMiliSegundos(3000);
		rolarTela(10, 300);
		
		imprimeTexto(thera());
		rolarTela(15, 300);
		pausaMiliSegundos(1000);

	}
	
	private static void cenaKlingdonSentarJunto() {

		int resposta;
		
		String[] enredo = {
				"Nosso herói está a bordo do intergalático cruzador MK70xx fazendo reparos no hiper                           ",
				"computador quântico PulsarVx, quando pela janelinha avista o 'planetinha azul' e imagina                     ",
				"quando retornará para casa.                                                                                  ",
				"",
				"Mario está há 3 meses no espaço indo de planeta em planeta e de nave a nave para fazer a                      ",
				"manutenção de uma lista enorme de equipamentos e conta na sua equipe com seres de vários                     ",
				"locais distantes da galáxia, além de alguns droides.                                                         ",
				"",
				"Na pausa do almoço, dois colegas Klingdons se aproximam da mesa e pergunta se os lugares                     ",
				"estão vagos. Diz as más linguas que a doença zumbi se originou no planeta deles e foi                        ",
				"fabricado por algum cientista.                                                                              ",
				"",
				"Porém até o momento poucas pessoas ficaram doentes. O que você aconselharia?                                 ",
				"",
				"1 - Os lugares estão ocupados.                                                                               ",
				"2 - Sim, está livre.                                                                                         "
		};
		
		imprimeCaracter(enredo, 5);
		System.out.println("");
		System.out.print("Digite a sua resposta: ");
		resposta = leitor.nextInt();

		System.out.println("");
		
		switch(resposta) {
		case 1:
			
			System.out.println("Mário pensou dessa maneira por que em breve retornará pra casa. Sua preocupação é com sua familia.  ");
			System.out.println("Ele não agiu de maneira grosseira, apenas se preveniu.");
			break;
			
		default:
			System.out.println("Para Mario isso não seria nenhum problema visto que não há o que se preocupar. É só uma gripezinha. ");
			break;
		}
		
		
	}
	
	
	private static boolean cenaKlingdonNaMesa() {
		int resposta;
		
		String[] enredo = {
				"Na verdade os Klingdons perguntaram por ironia. Eles não tem modos e independentemente da",
				"resposta eles iriam sentar do mesmo jeito. O problema é que eles falam gritando, gesticulam",
				"muito, batem na mesa e salivam muito. E se deixar, pegam a sua comida e bebida ou oferece",
				"a bebida popular altamente alcoólica para os humanos.",
				"",
				"E claro, encheram o copo do nosso amigo. O último que se recusou ou reclamou foi parar na",
				"enfermaria ou como costumavam dizer, a sete palmos debaixo da terra. Recusá-la é altamente",
				"ofensivo para eles. Vai entender, né?",
				"",
				"Na TV holográfica está passando notícias mais recentes dizendo que o planeta Klingdon a coisa",
				"está cada vez mais séria. A suspeita é que um animal doente encontrado morto é o vetor zero.",
				"Mas, o detalhe é que ele se alimenta de uma fruta que é a base dessa maldita bebida.",
				"",
				"Mario sabe disso por que no ano passado foi arrumar o computador responsável pela colheita e",
				"produção da bebida. E lá não existe controle de qualidade. Frutos comidos e podres fazem parte",
				"da composição da bebida!",
				"",
				"Um dos Klingdons propõe um brinde. Sabendo disso o que Mario deveria fazer?",
				"",
				"1 - Se recusa a beber. Se for parar na enfermaria pode voltar mais cedo pra casa.",
				"2 - Bebe, achando que é a única alternativa em sair vivo.",
				"3 - Dá uma de distraído, bate 'sem querer' o cotovelo no copo e o deixa cair."
		};
		
		imprimeCaracter(enredo, 5);
		System.out.println("");
		System.out.print("Digite a sua resposta: ");
		resposta = leitor.nextInt();
		System.out.println("");
		
		switch(resposta) {
		case 1:
			System.out.println("Ao recusar a bebida, os dois Klingdons partem para cima do Mario. E algo surpreendente acontece.");
			System.out.println("Segundo relatos ele parece que cresceu ao ser desafiado e ficou metalizado e forte com o adhamantium.");
			System.out.println("No final os dois Klingdons é que foram na enfermaria! Depois disso todos passaram a respeitá-lo.");
			System.out.println("");
			
			continuaSaga = true;
			break;
		case 2:
			System.out.println("Como ele não recusou da primeira vez, todos os dias os Klingdons enche o caneco do nosso amigo.");
			System.out.println("Porém, alguns dias depois, um dos Klingdons adoece e vira zumbi. O outro morre dias depois.");
			System.out.println("O sistema de AI detectou todos que tiveram contato, deixando-os em quarentena.");
			System.out.println("Mario morre semanas depois. A autópsia não foi clara se foi devido à doença ou à bebida que afetou o");
			System.out.println("fígado e outros órgãos de tal maneira que nem um artificial resolveria.");
			System.out.println("");
			
			statusAtual = StatusSaude.MORTO;
			
			return false;

		default:
			System.out.println("Um deles fica furioso com o descuido e desperdício e só não partiu para briga por que na semana passada.");
			System.out.println("ele foi salvo pelo nosso engenheiro encanador. Esse é um dos poucos motivos para que eles não te matem.");
			System.out.println("");
			
			continuaSaga = true;
			break;
			
		}
		
		return continuaSaga;

	}
}
