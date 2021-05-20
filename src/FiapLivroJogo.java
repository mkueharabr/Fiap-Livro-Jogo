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
	
	private static Scanner leitor = new Scanner(System.in);
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		boolean resposta = apresentacao();

		
		while(resposta) {
			
			
			limpaTela();
			abertura();
			
			limpaTela();	
			cenaKlingdonSentarJunto();
			
			resposta = false;
		}
		
		
		gameOver();
		
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
			rolarTela(5,0);
			
			imprimeTexto(gameOver());
			
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
				rolarTela(5,0);
				imprimeTexto(gameOver());
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
				pausaMiliSegundos(5000);
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
							nomeGamer + ", essa missão caso aceite-a, você decidirá o que é fato ou fake e o destino dele e ",
							"de todos os habitantes da galáxia desse reino não tão tão distante!",
							"",
							nomeGamer + ", se vc for capturado, morto ou virar zumbi, negaremos tudo perante à nação.",
							"Boa sorte! Bom jogo e que a força... da decisão esteja com vc!",
							"",
							"Alerta: Essa é uma obra de ficção. E qualquer personagem, lugar, acontecimentos citados aqui não",
							"foram baseados em fatos reais! Esse jogo NÃO faz referências a nenhum filme. É pura ficção... científica!",
							"Para uma melhor experiência gere o .jar e execute-o na linha de comando."
		};
		
	
		imprimeCaracter(intro, 15);
		System.out.println("");
		
		pausaMiliSegundos(1000);
		
		imprimeCaracter(titulo,20);
		System.out.println("");
		
		pausaMiliSegundos(1000);
		
		imprimeCaracter(resumo, 20);
		
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
				"                                                                                                             ",
				"Mario está há 3 meses no espaço indo de planeta a´planeta e de nave a nave para fazer a                      ",
				"manutenção de uma lista enorme de equipamentos e conta na sua equipe com seres de vários                     ",
				"locais distantes da galáxia, além de alguns droides.                                                         ",
				"                                                                                                             ",
				"Na pausa do almoço, dois colegas Klingdons se aproximam da mesa e pergunta se os lugares                     ",
				"estão vagos. Diz as más linguas que a doença zumbi se originou no planeta deles e foi                        ",
				"fabricando por algum cientista.                                                                              ",
				"Porém até o momento poucas pessoas ficaram doentes. O que você aconselharia?                                 ",
				"  ",
				"1 - Os lugares estão ocupados.                                                                               ",
				"2 - Sim, está livre.                                                                                         ",
				"  "
		};
		
		imprimeTexto(enredo);
		System.out.print("Digite a sua resposta: ");
		resposta = leitor.nextInt();

		System.out.println("");
		
		switch(resposta) {
		case 1:
			
			System.out.println("Mário pensou dessa maneira por que em breve retornará pra casa. Sua preocupação é com sua familia.  ");
			System.out.println("Ele não agiu de maneira grosseira, apenas se preveniu.");
			break;
		case 2:
			System.out.println("Para Mario isso não seria nenhum problema visto que não há o que se preocupar. É só uma gripezinha. ");
			break;
		}
		
	}
}
