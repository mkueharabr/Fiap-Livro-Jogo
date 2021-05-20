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
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		boolean resposta = apresentacao();
		
		while(resposta) {
			limpaTela();
			abertura();
			limpaTela();	
			
			resposta = false;
		}

	}
	
	private static boolean apresentacao() throws IOException, InterruptedException {
		Scanner leitor = new Scanner(System.in);
		String resposta;
		boolean	continuar = true;
		
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
		
		leitor.close();
		
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
		
		String titulo = "                  Mario numa galáxia distante combatendo o virus zumbi";
		String intro = "Há muito tempo atrás numa galáxia não tão distante ...";
		String [] resumo = {"Mario, o nosso herói, é um engenheiro encanador espacial que vive num planeta azul",
							"conhecido com Thera, onde vive com a família e pets num reino chamado Cattleland.",
							"",
							"Thera e suas 4 luas ficam a algumas centenas de parsecs de distância de Tatooine. No reino de",
							"Cattleland onde algumas pessoas ainda acreditam que o planeta é plano e em outras teorias",
							"conspiratórias, como a de que um novo vírus zumbi desconhecido ser criação do Império Klingon.",
							"Well... convenhamos, uma coisa não tem nada a ver com a outra meus amigos!",
							"",
							"E também do fato ou fake que o rei mitológico Jeziahs é da linhagem sombria da força! Sera?",
							"Lord Jeziahs e seus fiéis súditos parecem viver em um outro mundo, onde tudo está em perfeita harmonia",
							"e que a ameaça do vírus está sob controle, afinal eles tem em mãos a cura para toda a galáxia:",
							"a poderosa placeboK1na. Mas, a poderosa Federação Galática tem fortes evidências científicas que",
							"a mesma é responsável em matar seletivamente algumas pessoas pois, contém nano robôs de IA.",
							"",
							"Chega de spoilers... Para falar a verdade, poucos irão ler até aqui. Se você chegou até esse ponto",
							"meus sinceros agradecimentos ;). Enfim, esse é o cenário atual que vive nosso herói.",
							nomeGamer + ", essa missão caso aceite-a, você decidirá o que é fato ou fake e o destino dele e ",
							"de todos os habitantes da galáxia desse reino não tão tão distante!",
							"",
							nomeGamer + ", se vc for capturado, morto ou virar zumbi, negaremos tudo perante à nação.",
							"Boa sorte! Bom jogo e que a força... da decisão esteja com vc!",
							"",
							"Alerta: Essa é uma obra de ficção. E qualquer personagem, lugar, acontecimentos citados aqui não",
							"foram baseados em fatos reais! Esse jogo NÃO faz referências a nenhum filme. É pura ficção... científica!",
							"Para uma melhor experiência gere o .jar e execute-o na linha de comando"
		};
		
		System.out.println(intro);
		System.out.println("");
		
		pausaMiliSegundos(2000);
		
		System.out.println(titulo.toUpperCase());
		System.out.println("");
		
		pausaMiliSegundos(2000);
		
		imprimeTexto(resumo);
		
		pausaMiliSegundos(3000);
		rolarTela(10, 300);
		
		imprimeTexto(thera());
		rolarTela(15, 300);
		pausaMiliSegundos(1000);

	}
}
