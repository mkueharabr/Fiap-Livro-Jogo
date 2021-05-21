import java.io.IOException;
import java.util.ArrayList;
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
	private static final int larguraTela = 100;
	
	private static final String strEspaco     = " | ";
	private static final String strTentativas = " + ";
	private static final String strCapitulo   = " |-> ";
	private static final String strResposta   = " |   |-> ";
	private static int contaTentativas;
	
	private static ArrayList<String> caminho = new ArrayList<String>();
	
	
	private static Scanner leitor = new Scanner(System.in);
	
	public enum StatusJogo {
		VIVO ("vivo"), 
		MORTO("morto"), 
		DOENTE("doente"), 
		ZUMBI("zumbi"), 
		SENSIVEL("pessoa sensível ou nervosa"), 
		BLOQUEADO("bloqueado: não aconselhável a jogar");
		
		private String descricao;
		
		StatusJogo(String descricao){
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return descricao;
		}
	}
	
	private static StatusJogo statusAtual;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		statusAtual = StatusJogo.VIVO;
		String resposta;
		
		continuaSaga = apresentacao();
		
		if (continuaSaga) {
			limpaTela();
			abertura();
			
			while(continuaSaga) {
				contaTentativas ++;
				caminho.add(strEspaco);
				caminho.add(strTentativas + "Aventura # " + contaTentativas);
				limpaTela();	
				cenaKlingdonSentarJunto();
				
				limpaTela();
				continuaSaga = cenaKlingdonNaMesa();
				
				rolarTela(10, 10);
				imprimeTexto(gameOver(), false);
				
				rolarTela(10,0);
				System.out.println(nomeGamer + " sua saga chegou a um final. Abaixo um breve histórico das suas escolhas: ");
				imprimeListaCaminho(caminho);
				
				System.out.println("Você gostaria de continuar e conhecer outras possibilidades?");
				System.out.println("Digite (S - Sim, N - Não): ");
				resposta = leitor.next();		
				
				if (resposta.equalsIgnoreCase("N")) {
					continuaSaga = false;
				} else {
					continuaSaga = true;
				}
			}
			
		} else {
			rolarTela(10,10);
			imprimeTexto(gameOver(), false);
			
			rolarTela(10,10);
			System.out.println("Hasta la vista! Esse foi o seu breve caminho nessa jornada. Espero que você volte, baby!");
			imprimeListaCaminho(caminho);
			
		}
		
		leitor.close();

	}
	
	
	
	
	private static void pausaEntreTelas() {
		String resposta;
		
		System.out.println("");
		System.out.print("Digite 1 para continuar a saga... ");
		resposta = leitor.next();
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
	
	private static void imprimeTexto(String[] texto, boolean centralizado) {
		for (String linha : texto) {
			if (linha == "") {
				pausaMiliSegundos(1000);
			}
			if(centralizado) {
				centralizarTexto(linha, larguraTela);
			} else {
				System.out.println(linha);
			}
				
			
		}
	}
	
	private static void imprimeCaracter(String[] texto, int velocidade) {
		for (String linha : texto) {
			if (linha == "") {
				System.out.println("");
				pausaMiliSegundos(velocidade * 20);
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
	
	private static void centralizarTexto(String texto, int tamTela) {
		int inicioCol = (tamTela - texto.length())/2;
		String novoTexto;
		
		novoTexto = " ".repeat(inicioCol) + texto;
		System.out.println(novoTexto);
		
	}
	
	
	private static void imprimeListaCaminho(ArrayList<String> historico) {
		// acrescenta o status no jogo
		historico.add(strEspaco);
		historico.add(strCapitulo + "* Seu status final no jogo: " + statusAtual.getDescricao());
		
		System.out.println("");
		for(String texto : historico) {
			System.out.println(texto);
		}
		System.out.println("");
	}


	
	/*
	 ******************************************************************************************
	 *
	 *   Início dos ASCII arts
	 * 
	 * 
	 ******************************************************************************************
	 */
	
	private static String[] stop() {
		String[] stop = {
				"",
				"            uuuuuuuuuuuuuuuuuuuu             ",
				"          u' uuuuuuuuuuuuuuuuuu 'u           ",
				"        u' u@@@@@@@@@@@@@@@@@@@@u 'u         ",
				"      u' u@@@@@@@@@@@@@@@@@@@@@@@@u 'u       ",
				"    u' u@@@@@@@@@@@@@@@@@@@@@@@@@@@@u 'u     ",
				"  u' u@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@u 'u   ",
				"u' u@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@u 'u ",
				"@ @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @ ",
				"@ @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @ ",
				"@ @@@' ... '@...  ...@' ... '@@@  ... '@@@ @ ",
				"@ @@@u `'@@@@@@@  @@@  @@@@@  @@  @@@  @@@ @ ",
				"@ @@@@@@uu '@@@@  @@@  @@@@@  @@  ''' u@@@ @ ",
				"@ @@@''@@@  @@@@  @@@u '@@@' u@@  @@@@@@@@ @ ",
				"@ @@@@....,@@@@@..@@@@@....,@@@@..@@@@@@@@ @ ",
				"@ @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @ ",
				"'u '@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@' u' ",
				"  'u '@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@' u'   ",
				"    'u '@@@@@@@@@@@@@@@@@@@@@@@@@@@@' u'     ",
				"      'u '@@@@@@@@@@@@@@@@@@@@@@@@' u'       ",
				"        'u '@@@@@@@@@@@@@@@@@@@@' u'         ",
				"          'u '''''''''''''''''' u'           ",
				"            ''''''''''''''''''''             ",
				""
		};
		
		return stop;
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
				"                       ASCII Art by www.network-science.de",
				"",
				"              MARIO CONTRA O VÍRUS ZUMBI BY MARCELO KENJI UEHARA",
				""
		};
		
		return gameOver;		    
	}
	
	
	/*
	 ******************************************************************************************
	 *
	 *   Início dos enredos do Livro Jogo 
	 * 
	 * 
	 ******************************************************************************************
	 */
	
	private static boolean apresentacao() throws IOException, InterruptedException {
		
		String resposta, aviso = "";
		boolean	continuar = true;
		
		caminho.add("Apresentação");
		
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
			
			aviso = "Olá " + nomeGamer + ". Infelizmente esse jogo não é recomendado para menores de " + 
					idadeMinimaGamer + " anos.";
			
			caminho.add(strCapitulo + aviso);
			statusAtual = StatusJogo.BLOQUEADO;

			imprimeTexto(stop(), true);
			
			System.out.println(aviso);
			System.out.println("Provavelmente véio vc achará esse jogo entendiante demais. Vai jogar PS2! Ops, PS3? PS4? PS5? Enfim...");
			
			continuar = false;
			
			pausaMiliSegundos(4000);

			
		} else {
			limpaTela();
			
			System.out.println(nomeGamer + ", esse jogo não é recomendado para pessoas sensíveis, nervosas ou alérgicas ao modo texto. ¯\\_(oO)_/¯");
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
				
				caminho.add(strCapitulo + "Pessoa sensível, nervosa ou alérgica ao modo texto.");
				statusAtual = StatusJogo.SENSIVEL;
				pausaMiliSegundos(4000);

			}
		}

		return continuar;
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
							"foram baseados em fatos reais! Esse jogo NÃO faz referências a NENHUM filme. É pura ficção... científica!",
							"Podemos garantir que durante os seus " + idadeGamer + " anos de vida você não viu nada igual.",
							"",
							"Importante: não existe decisão certa ou errada.",
							"",
							"Para uma melhor experiência gere o .jar e execute-o na linha de comando."
		};
		
	
		imprimeCaracter(intro, 5);
		System.out.println("");
		
		pausaMiliSegundos(1000);
		
		imprimeCaracter(titulo,10);
		System.out.println("");
		
		pausaMiliSegundos(1000);
		
		imprimeCaracter(resumo, 15);
		
		pausaMiliSegundos(3000);
		rolarTela(10, 300);
		
		imprimeTexto(thera(), false);
		rolarTela(15, 300);
		pausaMiliSegundos(1000);
		

	}
	
	private static void cenaKlingdonSentarJunto() {

		int resposta;
		String titulo;
		
		String[] respostaOcupado = {
				"Mário pensou dessa maneira por que em breve retornará pra casa. Sua preocupação é com sua familia.",
				"Ele não agiu de maneira grosseira, apenas se preveniu. Mesmo porquê os Klingdons não considera",
				"esse nobre gesto de guardar lugar para um amigo como uma gentileza ou companherismo.",
				"",
				"A única coisa que ele pensa agora é em evitar confusão e chegar inteiro em casa."				
		};
		
		String[] respostaLivre = {
				"Para Mario isso não seria nenhum problema visto que não há o que se preocupar. É só uma gripezinha. ",
				"As últimas notícias mostram que a doença está apenas no início e em alguns planetas específicos.",
				"Para ele e todos nessa espaçonave isso pode levar anos até chegar nesse quadrante."
				
		};
		
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
				"estão vagos. Dizem as más linguas que a doença zumbi se originou no planeta deles e foi                        ",
				"fabricado por algum cientista.                                                                              ",
				"",
				"Porém até o momento poucas pessoas ficaram doentes. O que você aconselharia?                                 ",
				"",
				"1 - Os lugares estão ocupados.                                                                               ",
				"2 - Sim, está livre.                                                                                         "
		};
		
		titulo = "Capítulo 01 - Longe de casa, longe de tudo";
		caminho.add(strCapitulo + titulo);
		
		centralizarTexto(titulo, larguraTela);
		System.out.println("");
		
		imprimeCaracter(enredo, 5);
		System.out.println("");
		
		System.out.print("Digite a sua resposta: ");
		resposta = leitor.nextInt();

		System.out.println("");
		
		switch(resposta) {
		case 1:
			// lugar ocupado
			imprimeTexto(respostaOcupado, false);

			caminho.add(strResposta + "Lugar ocupado na mesa");
			
			break;
			
		default:
			// qualquer outra opção, o lugar está livre
			imprimeTexto(respostaLivre, false);
			caminho.add(strResposta + "Lugar vago na mesa");
			
		}
		
		pausaEntreTelas();
		
		
	}
	
	
	private static boolean cenaKlingdonNaMesa() {
		int resposta;
		String titulo;
		
		String[] respostaRecusarBebida = {
			"Ao recusar a bebida, os dois Klingdons partem para cima do Mario. E algo surpreendente acontece.",
			"Segundo relatos ele parece que cresceu ao ser desafiado e ficou metalizado e forte como adhamantium.",
			"",
			"No final os dois Klingdons é que foram na enfermaria! Depois disso todos passaram a respeitá-lo.",
			"Reza a lenda que Mario aprendeu alguns 'truques' quando atendeu a um chamado de uma nave que tinha afundado",
			"num pântano e viu 'coisas' que um velhinho baixinho e orelhudo ensinava ao seu jovem aprendiz.",
			"",
			"Após arrumar vários entupimentos no acelerador de partículas da aeronave, o bom velhinho disse ao",
			"Mario algo como: 'a força dentro de você eu sinto!'. Coitado mal fala português direito, pensou Mario",
			"sem entender do que se tratava. Não até hoje, após nocatear os dois.",
			""
		};
		
		String[] respostaBeber = {
				"Como ele não recusou da primeira vez, todos os dias os Klingdons enche o caneco do nosso amigo.",
				"Porém, alguns dias depois, um dos Klingdons adoece e vira zumbi. O outro morre dias depois.",
				"",
				"O sistema de AI detectou todos que tiveram contato, deixando-os em quarentena.",
				"Mario morre semanas depois. A autópsia não foi clara se foi devido à doença ou à bebida que afetou o",
				"fígado e outros órgãos de tal maneira que nem um artificial resolveria.",
				"",
				"Outros dizem que, como os órgãos estão em falta devido à um esquema de corrupção onde se desvia uma",
				"grande quantidade de b1tM0edas, ultimamente só pessoas ricas que conseguem. E esse, infelizmente",
				"não era o caso do nosso honesto e humilde engenheiro encanador.",
				""				
		};
		
		String[] respostaDerrubarBebida = {
				"Ahhhh o 'velho truque' do esbarrão do cotovelo na bebida batizada do inimigo! Elementar meu caro... Mario!",
				"Maaaasss, um deles fica furioso com o descuido e desperdício e só não partiu para briga por que na semana passada",
				"ele foi salvo pelo nosso engenheiro encanador. Afinal o próximo mercado fica a anos-luz de distância.",
				"",
				"Salvar a pele deles é um dos poucos motivos para que eles não te matem. Afinal são guerreiros.",
				"Entretanto, não espere que isso dure para sempre. A sua próxima pisada na bola pode ser fatal.",
				"",
				"Não diria que Mario ganhou um vida extra. Acredito mais em algumas barras de energia.",
				""
		};
		
		String[] enredo = {
				"Na verdade os Klingdons perguntaram por sarcasmo. Eles não tem modos e independentemente da",
				"resposta eles iriam sentar do mesmo jeito. O problema é que eles falam gritando, gesticulam",
				"muito, batem na mesa e salivam muito. E se deixar, pegam a sua comida e bebida ou oferece",
				"a bebida Abhysmum altamente alcoólica e perigosa para os humanos.",
				"Essa é pior do que a BuracoNegro dos piratas espacias do Carybeean ou a darkSideSith Black Vhader",
				"Label Edition do Império.",
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
		
		titulo = "Capítulo 02 - Os 'amáveis Klingdons' e sua bebiba super batizada";
		caminho.add(strCapitulo + titulo);
				
		centralizarTexto(titulo, larguraTela);
		System.out.println("");
		
		imprimeCaracter(enredo, 5);
		
		System.out.println("");
		System.out.print("Digite a sua resposta: ");
		resposta = leitor.nextInt();
		System.out.println("");
		
		switch(resposta) {
		case 1:
			// recusar a bebida
			imprimeTexto(respostaRecusarBebida, false);
			
			continuaSaga = true;
			
			caminho.add(strResposta + "Você recusou a bebida dos Klingdons??? Ou você conhece o Mario ou é maluco!");
			
			break;
		case 2:
			// beber
			imprimeTexto(respostaBeber, false);

			statusAtual = StatusJogo.MORTO;
			
			caminho.add(strResposta + "Você aceitou a bebida dos Klingdons por educação??? Em épocas como essa é melhor revisar seus conceitos!");
			
			continuaSaga = false;
			break;

		default:
			// qualquer outra opção, derrubar a bebida
			imprimeTexto(respostaDerrubarBebida, false);

			continuaSaga = true;
			
			caminho.add(strResposta + "Ahh... o velho truque do cotovelo para derrubar a bebida batizada do inimigo!");
			break;
			
		}
		
		pausaEntreTelas();
		
		return continuaSaga;

	}
}
