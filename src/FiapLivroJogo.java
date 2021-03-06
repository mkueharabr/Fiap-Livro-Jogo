import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class FiapLivroJogo {
/*
 * 	Autor: Marcelo Kenji Uehara (@Kenji)
 * 
 *  Nome do Jogo: Mario numa galáxia distante combatendo o virus zumbi
 *  
 *  Atividade realizada no módulo de Lógica de Programação de imersão Java Xpert
 *  
 *  Obs.: O código está limitado aos recursos aprendidos no módulo. Ele será refatorado posteriormente.
 *  
 */

	private static int idadeGamer;
	private static String nomeGamer;
	private static final int idadeMinimaGamer = 16;
	private static boolean continuaSaga = true;
	private static final int larguraTela = 120;
	
	private static final String strEspaco     = " | ";
	private static final String strTentativas = " + ";
	private static final String strCapitulo   = " |-> ";
	private static final String strResposta   = " |   |-> ";
	private static final String strStatusJogo = " *--~~~=:>[XXXXXXXXX]> ";
	private static int contaTentativas = 0;
	private static boolean ativarCenaNanoFarm = false;
	
	private static ArrayList<String> caminho = new ArrayList<String>();
	
	
	private static Scanner leitor = new Scanner(System.in);
	
	private static String[] capitulo = {
			"MARIO NUMA GALÁXIA NÃO TÃO TÃO DISTANTE COMBATENDO O VIRUS ZUMBI",
			"Capítulo 01 - Longe de casa a mais de uma semana solar, parsecs e parsecs distante",
			"Capítulo 02 - Os 'amáveis Klingdons' e sua bebiba super batizada",
			"Capítulo 03 - Houston, we have a problem!",
			"Capítulo 04 - O vírus Zumbi",
			"Capítulo 05 - Placeb0K1na, placeb0K1na... placeb0k1na de Jezziahs",
			"Capítulo 06 - Brilha, brilha estrelinha... da morte",
			"Bônus - Nanobots Farm"
	};
 	
	public enum StatusJogo {
		VIVO ("vivo"), 
		MORTO("morto"), 
		DOENTE("doente"), 
		ZUMBI("zumbi"), 
		SENSIVEL("pessoa sensível ou nervosa"), 
		BLOQUEADO("bloqueado: não aconselhável a jogar"),
		WAIT("Em espera");
		
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
		String resposta = "1";
		
		limparTela();
		rolarTela(7,5);
		imprimirTexto(mostrarAberturaJogo(), true);
		pausarMiliSegundos(1000);
		
		rolarTela(2,5);
		pausarEntreTelas();
		
		continuaSaga = mostrarIntroducao();
		
		if (continuaSaga) {
			limparTela();
			mostrarAbertura();
			pausarEntreTelas();
			
			while(continuaSaga) {
				contaTentativas ++;
				
				caminho.add(strEspaco);
				caminho.add(strTentativas + "Aventura # " + contaTentativas);
				
				statusAtual = StatusJogo.VIVO;
				
				switch(resposta.toUpperCase()) {
				case "1":
					limparTela();	
					mostrarCenaKlingdonSentarJunto();
					
				case "2":
					limparTela();
					mostrarCenaKlingdonNaMesa();
					
					if(statusAtual == StatusJogo.DOENTE) {
						limparTela();
						mostrarCenaMarioUti();
					}
				
				case "4":
					if(statusAtual != StatusJogo.MORTO && statusAtual != StatusJogo.ZUMBI) {
						
						limparTela();
						mostrarCenaVirusZumbi();
						
					} else {
						break;
					}
					
				case "5":
					limparTela();
					mostrarCenaPlaceboKina();
					
					limparTela();
					mostrarCenaDeathStar();
					
					break;
				default:
					System.out.println("Escolha uma opção válida!");
					statusAtual = StatusJogo.WAIT;
				}
				
				rolarTela(5,0);

				imprimirListaCaminho(caminho);
				pausarEntreTelas();

				resposta = retornarRespostaMenu();		

				if (resposta.equalsIgnoreCase("N")) {
					continuaSaga = false;
				} else {
					continuaSaga = true;
				}

			}
			
		} else {
			
			rolarTela(10,10);
			
			imprimirListaCaminho(caminho);
			pausarEntreTelas();
			
		}

		
		rolarTela(10, 10);
		imprimirTexto(mostrarGameOverAscii(), true);
		
		// Bonus... cenas do próximo filme...
		if(ativarCenaNanoFarm) {
			limparTela();
			mostrarCenaRevolucaoNano();
		}
		
		leitor.close();

	}

	
	private static String retornarRespostaMenu() {
		String resposta;
		System.out.println("Você gostaria de continuar e conhecer outras possibilidades?");
		System.out.println("");
		System.out.println("N - Não. Vou parar por aqui.");
		System.out.println("1 - " + capitulo[1]);
		System.out.println("2 - " + capitulo[2]);
		System.out.println("4 - " + capitulo[4]);
		System.out.println("5 - " + capitulo[5]);
		System.out.println("");
		System.out.print("Digite a sua opção: ");
		resposta = leitor.next();
		
		return resposta;
	}
	
	/*
	 ******************************************************************************************
	 *
	 *   Início dos enredos do Livro Jogo 
	 * 
	 * 
	 ******************************************************************************************
	 */
	
	private static boolean mostrarIntroducao() throws IOException, InterruptedException {
		
		String resposta, aviso = "";
		boolean	continuar = true;
		
		caminho.add("Apresentação");
		
		limparTela();
		
		System.out.println("Olá, sou Jharvis, seu assistente virtual da era D/O/S. Antes de começarmos preciso de algumas informações pessoais.");
		System.out.println("");
		
		System.out.print("Por favor, digite o seu nome: ");
		nomeGamer = leitor.next();
		
		System.out.print("E agora a sua idade: ");
		idadeGamer = leitor.nextInt();
		
		if(idadeGamer < idadeMinimaGamer) {
			limparTela();
			
			aviso = "Olá " + nomeGamer + ". Infelizmente esse jogo não é recomendado para menores de " + 
					idadeMinimaGamer + " anos terrestres.";
			
			caminho.add(strCapitulo + aviso);
			statusAtual = StatusJogo.BLOQUEADO;

			System.out.println(aviso);
			System.out.println("Provavelmente véio vc achará esse jogo entendiante demais. Vai jogar PS2! Ops, PS3? PS4? PS5? Enfim...");
			
			continuar = false;
			
			pausarMiliSegundos(1000);

			
		} else {
			limparTela();
			
			System.out.println(nomeGamer + ", esse jogo não é recomendado para pessoas sensíveis aos efeitos estroboscópicos, nervosas ou "
					+ "alérgicas ao modo texto. ¯\\_(oO)_/¯");
			System.out.println("");
			System.out.println("Se esse for o seu caso temos as seguintes opções:");
			System.out.println("S - Para sair");
			System.out.println("C - para continuar");
			System.out.println("");
			System.out.print("Digite a sua decisão: ");
			resposta = leitor.next();
			
			if(resposta.toUpperCase().startsWith("S")) {
				limparTela();
				System.out.println("Obrigado por sua sábia decisão.");
				continuar = false;
				
				caminho.add(strCapitulo + "Pessoa sensível, nervosa ou alérgica ao modo texto.");
				statusAtual = StatusJogo.SENSIVEL;
				pausarMiliSegundos(4000);

			}
		}

		return continuar;
	}

	private static void mostrarAbertura() {
		
		String[] intro = {"Há muito tempo numa galáxia não tão tão distante ..."};
		
		String[] titulo = {"MARIO NUMA GALÁXIA NÃO TÃO TÃO DISTANTE COMBATENDO O VIRUS ZUMBI"};
		
		String [] resumo = {"Mario, o nosso herói, é um engenheiro encanador quântico que vive num planeta azul conhecido com Thera, onde "
				          + "mora com a família e pets num reino chamado Cattleland.",
							"",
							"Thera e suas 4 luas ficam a algumas centenas de parsecs de distância de Babooine. No reino de Cattleland, "
						  + "algumas pessoas ainda acreditam que o planeta é plano e em outras teorias conspiratórias, como a de que o novo"
						  + " vírus zumbi desconhecido ser criação do Império Klingdon.",
							"Well... convenhamos, uma coisa não tem nada a ver com a outra meus amigos!",
							"",
							"E também do fato ou fake que o rei mitológico Jezziahs é da linhagem sombria da força! Sera? Lord Jezziahs e "
						  + "seus fiéis súditos parecem viver em um outro mundo, onde tudo está em perfeita harmonia e que a ameaça do "
						  + "vírus está sob controle, afinal eles tem em mãos a cura para toda a galáxia: a poderosa placeb0K1na. Caso não "
						  + "tome você corre o risco de virar Dhanos, King G. Roll ou até o Browser!",
							"",
							"Mas, a poderosa Federação Galática da Saúde (FGS) tem fortes evidências científicas que esse suposto 'remédio'"
						  + "é responsável em matar seletivamente alguns seres pois, é fabricado com nano robôs dotados de IA.",
							"",
							"Chega de spoilers... Para falar a verdade, poucos irão ler até aqui. Se você chegou até esse ponto meus since"
						  + "ros agradecimentos ;). Você é um guerreiro. Enfim, esse é o cenário atual que vive nosso herói.",
							"",
							nomeGamer + ", essa missão caso aceite-a, você decidirá o que é fato ou fake, qual caminho deve ser tomado"
						 + "além do destino dele e de todos os habitantes da galáxia e desse reino não tão tão distante!",
							"",
							nomeGamer + ", se vc for capturado, morto ou virar zumbi, negaremos tudo perante à nação.",
							"Boa sorte! Bom jogo e que a força... da decisão esteja com vc!",
							"",
							"Alerta: Essa é uma obra de ficção. E qualquer personagem, lugar, acontecimentos citados aqui não foram baseados "
						  + "em fatos reais! Esse jogo NÃO faz referências a NENHUM filme. É pura ficção... científica!",
							"Podemos garantir que durante os seus " + idadeGamer + " anos terrestres de vida você não viu nada igual.",
							"",
							"Importante: não existe decisão certa ou errada.",
							"",
							"Vamos começar! Prepare a sua pipoca. Você disse pipoca?",
							"",
							"Para uma melhor experiência gere o .jar e execute-o na linha de comando."
		};
		
		rolarTela(15, 0);
		imprimirTexto(intro, true);
		pausarMiliSegundos(3000);
		
		rolarTela(40, 0);
		
		imprimirTexto(titulo, true);
		System.out.println("");
		
		pausarMiliSegundos(1000);
		
		imprimirQuebra(resumo, 15);
		
		pausarMiliSegundos(3000);
		rolarTela(10, 300);
		
		imprimirTexto(mostrarTheraAscii(), true);
		rolarTela(20, 300);
		pausarMiliSegundos(1000);
		

	}
	
	// capitulo 01
	private static void mostrarCenaKlingdonSentarJunto() {

		int resposta;
		String titulo;
		
		String[] respostaOcupado = {
				"Mário pensou dessa maneira por que em breve retornará pra casa. Sua preocupação é com sua familia. Ele não agiu de "
			  + "maneira grosseira, apenas se preveniu. Mesmo porquê os Klingdons não considera esse nobre gesto de guardar lugar "
			  + "para um amigo como uma gentileza ou companherismo.",
				"",
				"A única coisa que ele pensa agora é em evitar confusão e chegar inteiro em casa."				
		};
		
		String[] respostaLivre = {
				"Para Mario isso não seria nenhum problema visto que não há o que se preocupar. É só uma gripezinha. ",
				"As últimas notícias mostram que a doença está apenas no início e em alguns planetas específicos.",
				"Para ele e todos nessa espaçonave isso pode levar anos estelares até chegar nesse quadrante."
				
		};
		
		String[] enredo = {

				"Nosso herói está a bordo do intergalático cruzador MK70z_ fazendo reparos no hiper computador quântico PulsarVx,"
			  + " quando pela janelinha avista o 'planetinha azul' e imagina quando retornará para casa.",
				"",
				"Mario está há 3 meses no espaço indo de planeta em planeta e de nave a nave para fazer a manutenção de uma lista"
			  + " enorme de equipamentos e conta na sua equipe com seres de vários locais distantes da galáxia, além de alguns "
			  + "droides.",
				"",
				"Na pausa do almoço, dois colegas Klingdons se aproximam da mesa e pergunta se os lugares estão vagos. Dizem as "
			  + "más linguas que a doença zumbi se originou no planeta deles e foi fabricado por algum cientista local.",
				"",
				"Porém até o momento poucas pessoas ficaram doentes. O que você aconselharia?",
				"",
				"1 - Os lugares estão ocupados. ",
				"2 - Sim, está livre.  "
		};
		
		titulo = capitulo[1];
		caminho.add(strEspaco);
		caminho.add(strCapitulo + titulo);
		
		centralizarTexto(titulo, larguraTela);
		System.out.println("");
		
		imprimirQuebra(enredo, 5);
		System.out.println("");
		
		System.out.print("Digite a sua resposta: ");
		resposta = leitor.nextInt();

		System.out.println("");
		
		switch(resposta) {
		case 1:
			// lugar ocupado
			imprimirQuebra(respostaOcupado, 0);
			caminho.add(strResposta + "(Op 1) Lugar ocupado na mesa pelo meu amigo imaginário ou invisível");
			
			break;
			
		default:
			// qualquer outra opção, o lugar está livre
			imprimirQuebra(respostaLivre, 0);
			caminho.add(strResposta + "(Op 2) Lugar vago na mesa");
			
		}
		
		pausarEntreTelas();	
		
	}
	
	
	// capitulo 02
	private static void mostrarCenaKlingdonNaMesa() {
		int resposta;
		String titulo;
		
		String[] respostaRecusarBebida = {
			"Ao recusar a bebida, os dois Klingdons partem para cima do Mario. E algo surpreendente acontece. Após ser encurralado, Mario "
		  + "subiu na mesa, deu vários pulos e com o punho cerrado socou o teto. Nesse momento, ele cresceu "
		  + "um pouco???!! Ficou metalizado e forte como adhamantium. E bastaram alguns golpes para derrubá-los.",
			"",
			"Uns escutaram um barulho de moedas caindo. Outros, como os Thoads, juram que o Mario disse algo como 'Pelos poderes de GraceCool, "
		  + "EU TENHO A FORÇA!!!'. A verdade é que, nesses tempos de deepfake memes todo cuidado é pouco. Não dá pra acreditar em tudo o que se "
		  + "vê ou ouve. E quem é GraceCool???",
			"",
			"No final os dois Klingdons é que foram na enfermaria! Depois desse fato bizarro todos passaram a respeitá-lo. Reza a lenda que "
		  + "Mario aprendeu alguns 'truques' quando atendeu a um chamado de uma nave que tinha afundado num pântano e  viu 'coisas' que um "
		  + "velhinho baixinho e orelhudo ensinava ao seu jovem aprendiz.",
			"",
			"Após arrumar vários entupimentos no acelerador de partículas da aeronave, o bom velhinho disse ao Mario algo como: 'a força "
		  + "dentro de você eu sinto!'. 'Coitado mal fala português direito', pensou Mario sem entender do que se tratava. Não até hoje, "
		  + "após nocatear os dois.",
			""
		};
		
		String[] respostaBeber = {
				"Você aceitaria a bebida dos Klingdons por educação??? Em épocas como essa é melhor revisar seus conceitos! Como ele não recusou"
			  + " da primeira vez, todos os dias os Klingdons enche o caneco do nosso amigo.",
				"Porém, alguns dias depois, um dos Klingdons adoece e vira zumbi. O outro morre dias depois.",
				"",
				"O sistema de AI rastreou todos que tiveram contato, deixando-os em quarentena. Sim, eles vivem num mundo onde nanarobôs "
			  + "são implantados dentro de você. Te monitoram 24x7, como diriam em Thera.",
				"",
				"But now.. Houston, we have a problem!"				
		};
		
		String[] respostaDerrubarBebida = {
				"Ahhhh o 'velho truque' do esbarrão do cotovelo na bebida batizada do inimigo! Elementar meu caro... Mario!",
				"Maaaasss, um deles fica furioso com o descuido e desperdício e só não partiu para briga por que na semana passada ele foi salvo "
			  + "pelo nosso engenheiro encanador. Afinal de contas, a adega mais próxima fica a anos-luz de distância!",
				"",
				"Salvar a pele deles é um dos poucos motivos para que eles não te matem. Afinal são guerreiros. E existe uma espécie de código de "
			  + "honra. Entretanto, não espere que isso dure para sempre. A sua próxima pisada na bola pode ser fatal.",
				"",
				"Dessa forma, não diria que Mario ganhou uma vida extra. Acredito mais em algumas barras de energia.",
				""
		};
		
		String[] enredo = {
				"Na verdade os Klingdons perguntaram por sarcasmo. Eles não tem modos e independentemente da resposta eles iriam sentar"
			  + " do mesmo jeito. O problema é que eles falam gritando, gesticulam muito, batem na mesa e salivam muito. E se deixar, "
			  + "pegam a sua comida e bebida ou oferece a bebida Abhysmum altamente alcoólica e perigosa para os humanos.",
				"Essa é pior do que a BuracoNegro dos piratas espacias do C@rybeean ou a darkSideSith Black Vhader Label Edition do "
			  + "Império. Não, não é da novela das 9 que estamos falando aqui...",
				"",
				"E claro, encheram o copo do nosso amigo. O último que se recusou ou reclamou foi parar na enfermaria e outros, como "
			  + "costumavam dizer, a sete palmos debaixo da terra. Recusá-la é altamente ofensivo para eles. Vai entender, né? O que "
			  + "não é ofensivo para esses animais?",
				"",
				"Na TV holográfica está passando notícias mais recentes dizendo que o planeta Klingdon a coisa está cada vez mais séria. "
			  + "A suspeita é que um animal doente encontrado morto é o vetor zero.",
				"Mas, o detalhe é que ele se alimenta de uma fruta que é a base dessa maldita bebida.",
				"",
				"Mario sabe disso por que no ano estelar passado foi arrumar o computador quântico responsável pela colheita e produção da bebida. "
			  + "E lá não existe controle de qualidade. Frutas comidas e podres fazem parte da composição da bebida! Nosso amigo pensou "
			  + "que era um bug do IA.",
				"",
				"E muito menos rastreabilidade. Sim, da colheita ao produto final nada é registrado no ClusterChain Book, como em outros planetas da"
			  + " Federação. Ou você acha que eles cumprem qualquer lei?",
				"",
				"Um dos Klingdons propõe um brinde. Sabendo disso o que Mario deveria fazer?",
				"",
				"1 - Se recusa a beber. Se for parar na enfermaria pode voltar mais cedo pra casa.",
				"2 - Bebe, achando que é a única alternativa em sair vivo.",
				"3 - Dá uma de distraído, bate 'sem querer' o cotovelo no copo e o deixa cair."
		};
		
		titulo = capitulo[2];
		caminho.add(strEspaco);
		caminho.add(strCapitulo + titulo);
				
		centralizarTexto(titulo, larguraTela);
		System.out.println("");
		
		imprimirQuebra(enredo, 5);
		
		System.out.println("");
		System.out.print("Digite a sua resposta: ");
		resposta = leitor.nextInt();
		System.out.println("");
		
		switch(resposta) {
		case 1:
			// recusar a bebida
			imprimirQuebra(respostaRecusarBebida, 0);
			
			continuaSaga = true;
			
			caminho.add(strResposta + "(Op 1) Você recusou a bebida dos Klingdons??? Ou você conhece o Mario ou é maluco!");
			
			break;
		case 2:
			// beber
			imprimirQuebra(respostaBeber, 0);

			statusAtual = StatusJogo.DOENTE;
			
			caminho.add(strResposta + "(Op 2) Você aceitou a bebida dos Klingdons por educação??? Em épocas como essa é melhor revisar seus conceitos!");
			
			
			break;

		default:
			// qualquer outra opção, derrubar a bebida
			imprimirQuebra(respostaDerrubarBebida, 0);

			continuaSaga = true;
			
			caminho.add(strResposta + "(Op 3) Ahh... o velho truque do cotovelo para derrubar a bebida batizada do inimigo!");
		
			
		}
		
		pausarEntreTelas();
		
		//return continuaSaga;

	}
	
	private static void mostrarCenaMarioUti() {
		int resposta;
		
		String titulo = capitulo[3];
		
		String[] respostaOrgao = {
				"Péssimas notícias. O plano do nosso humilde trabalhador não cobre cirurgias intergaláticas em distâncias maiores que 10 parsecs e "
			  + "está limitado ao transplante de apenas 1 órgão artificial. Ele precisaria de 2, pelo menos.",
				"Além disso, os órgãos estão em falta devido à um esquema de corrupção onde se está desviando uma grande quantidade de b1tM0edas, e "
			  + "ultimamente só pessoas ricas que conseguem graças ao plano top das galáxias. E esse, infelizmente não é o caso do nosso honesto "
			  + "engenheiro encanador.",
				"",
				"A esperança agora é que o diagnóstico seja o vírus. Ao chegar o resultado foi constado que a bebida tinha atacado o fígado, rins, "
			  + "coração e outros órgãos.",
				"",
				"Em poucas horas seu corpo se definha e não resiste. Nosso amigo vira mais uma estrelinha no universo. RIP my friend."
		};
		
		String[] respostaTratamentoPrecoce = {
				"Mario é contra ao tratamento precoce, cujo remédio tem como garoto propaganda o Lorde Jezziahs. Para Mario, é a mesma coisa que "
			  + "beber água artificial ou um suco de frutas do planeta de seu amigo Jabá da pizzaria The Hut. Sem efeito nenhum. Em Thera alguns "
			  + "são curados sem fazer nada. A maioria tem sintomas leves. Mas, os dois Klingdons foram tratados com placeb0K1na e não tiveram sorte.",
				"",
				"Como não é um remédio adotado pelos membros da Federação é necessário esperar alguns dias para ser entregue pela Amazonas. Até lá "
			  + "o paciente será mantido na UTI e que para sorte do nosso amigo, pelo menos o plano médico cobre.",
				"",
				"Por sorte o problema era o vírus. O remédio o curou? Na verdade a encomenda do Amazonas nunca chegou. A nave robô foi interceptada "
			  + "pelos piratas espacias do planeta C@rybeean."
		};
		
		String[] respostaNaoFazNada = {
				"Resposta corajosa. Na verdade os médicos sabem que provavelmente nosso amigo não sobreviverá por muito tempo. Que o plano de saúde "
			  + "do paciente não é top das galáxias. Fora o fato que nenhum dos médicos é de Thera. Dizem alguns que o importante para eles é desocupar "
			  + "o leito para dar vaga a outros com mais chance e planos melhores. Bu$ine$$ é a palavra. A ganância é a mesma em qualquer parte do Universo.",
				"",
				"Mas a UTI é aceita e paga bem. Então, por enquanto os médicos irão mantê-lo sob observação e com soro a base de DNA do paciente e vitaminado "
			  + "com anticorpos de pacientes.",
				"",
				"Por 'sorte' o problema era o vírus. Muitos em Thera têm sintomas leves. E outros, como no caso de Mario, é mais sério. Mas a chance de "
			  + "recuperação com a medicina avançada de agora é de cerca de 70%. Isso os médicos não sabiam por não lidar com muitos pacientes de Thera.",
				"",
				"Você pagou para ver! Sorte que Mario é um verdadeiro guerreiro e resistiu bravamente. Porém a sua recuperação será demorada. Esse foi"
			  + " o passaporte de volta pra casa! Com essas condições físicas, sem chance de se aventurar."
				
		};
		
		String[] enredo = {
				"Durante a quarentena, Mario ficou muito doente. Os Klingdons têm costume de beber na garrafa mesmo e dividir com os outros.",
				"E agora nosso amigo tenta resistir bravamente na UTI onde se encontra sedado e inconsciente. Esse procedimento é totalmente "
			  + "normal e visa preservar todos os sinais vitais do paciente. Seu corpo é totalmente escaneado para identificar o problema.",
				"",
				"Os médicos estão em dúvida se o estado do paciente é por conta da bebida Abhysmum, que é altamente tóxicas ao humanos se consumida "
			  + "por algum tempo e acabam com o fígado e outros órgãos ou se é por causa do vírus. Como a doença é recente, os médicos ainda não "
			  + "conhecem todos os sintomas que afetam o povo de Thera. E nenhum deles é de lá.",
				"",
				"O clusterChainHealth, um livro único no Universo contendo todo o histórico de saúde do paciente, foi bloqueado pelos governos de "
			  + "Cattleland e outros reinados para esconder a verdade do que está acontecendo em algumas partes de Thera.",
			    "",
				"Eles estão no escuro. Por isso, as próximas horas serão cruciais para a sobrevivência do nosso amigo.",
				"",
				"Sabendo do histórico anterior dos Klingdons e antes do diagnóstico estar pronto e o plano de saúde autorizar os próximos procedimentos, "
			  + "os médicos dão as seguintes opções de tratamento:",
				"",
				"1 - Se for considerar a bebida, transplantar o paciente com órgãos artificiais antes que comecem a falhar.",
				"2 - Se for o vírus, como Mario é de Thera, os médicos devem seguir o protocolo de lá e aconselham o tratamento precoce a base de placeb0K1na",
				"3 - Não faz nada no momento. Apenas monitaram o paciente, dão alguns medicamentos e esperam pela sua recuperação.",
				"",
				
		};
		
		caminho.add(strEspaco);
		caminho.add(strCapitulo + titulo);
		
		centralizarTexto(titulo, larguraTela);
		System.out.println("");
		
		imprimirQuebra(enredo, 5);
		
		System.out.println("");
		System.out.print("Que decisão você aconselharia aos médicos?");
		resposta = leitor.nextInt();
		System.out.println("");
		
		switch(resposta) {
		case 1:
			imprimirQuebra(respostaOrgao, 0);

			continuaSaga = false;
			statusAtual = StatusJogo.MORTO;
			
			caminho.add(strResposta + "(Op 1) Oh no! A bebida Abhysmum matou nosso amigo. O plano médico não cobria transplantes e cirurgias intergalática.");
			break;
			
		case 2:
			imprimirQuebra(respostaTratamentoPrecoce, 0);

			continuaSaga = true;
			
			caminho.add(strResposta + "(Op 2) Nosso amigo foi curado sem placeb0K1na. A nave robô da Amazonas foi sequestrada pelos piratas espaciais do C@rybeean!");
			break;
			
		default:
			imprimirQuebra(respostaNaoFazNada, 0);

			continuaSaga = false;
			statusAtual = StatusJogo.ZUMBI;
			
			caminho.add(strResposta + "(Op 3) Você pagou pra ver. All in. Ao sair o resultado foi constatado que era o vírus. Mas, Mario é guerreiro!");
			break;
		}
		

		pausarEntreTelas();

	}
	

	private static void mostrarCenaVirusZumbi() {
		int resposta;
		String titulo = capitulo[4];
		String valorFormatado;
		
		Random random = new Random();
		long geraTotal = random.nextInt(1000000000);
		
		
		String[] enredo = {
				"Com o avanço da ciência e tecnologia durante séculos, muitas áreas foram beneficiadas: entre elas a medicina. Da mesma forma, os seres invisíveis aos"
			  + " olhos de grande parte do Universo. Com os remédios cada vez mais poderosos, os vírus sobreviventes se tornaram cada vez mais mortais. E"
			  + " alguns alteram até as estruturas moleculares do hospedeiro, ou DNA no caso dos habitantes de Thera.",
			  	"",
			  	"Foram espalhadas fake news que esse novo vírus foi criado num laboratório por cientistas que querem dominar o mundo e inclusive já teriam "
			  + "a cura pronta! Não importa aonde você mora ou que século estamos. Teorias conspiratórias sem provas sempre existirão.",
			    "",
			    "A principal linha de investigação é que a origem é o planeta Klingdon. Mas nada indica que foi fabricada artificialmente, apesar de"
			  + " ser possivel hoje em dia. Os primeiros caso aconteceram lá e além de se espalhar rapidamente e matar milhares no planeta, alguns casos estão"
			  + " surgindo nos mais longínquos quadrantes do universo. Resultado das extensas rotas intergaláticas de comércio e exploração de recursos.",
			    "",
			    "O que mais assusta é o fato dessa doença se espalhar e infectar as mais distintas raças intergaláticas. Isso nunca tinha acontecido antes. Afinal, cada"
			    + " raça tem uma composição molecular distinta entre si.",
			    "",
			    "Cada um tem uma uma reação diferente. O vírus algumas vezes é mortal. Nos casos mais graves, deixam sequelas onde a pessoa perde a parte motora e cognitiva. E muitas vezes "
			  + "parece dominada por um hospedeiro. Daí o apelido de zumbi. E nem trocando por um cérebro artificial dotado de AI e ML funcionaria.",
			     "",
			     "E o que você acha disso tudo?",
			     "",
			     "1 - O vírus foi criado em laboratórios pelos Klingdons.",
			     "2 - Não acredito em teorias da conspiração e fake news. Acredito nos fatos!",
			     "3 - Onde há fusão há plasma. Se alguém espalha algo é por que pode ter algum fundo de verdade.",
			     "4 - Outros. Justifique."
			  
		};
		
		String[] respostaKlingdonCulpado = {
			 "Acusar sem provas é um jogo perigoso. Povos inimigos e não simpatizantes com os Klingdons abraçaram essa fake news. Altos membros do Império aproveitaram "
		   + "a oportunidade para eliminar o problema pela raiz. Primeiro porque os Klingdons traíram o Império e em segundo lugar para aproveitar que o inimigo estava fraco e doente "
		   + "e provocar uma guerra para dizimá-los.",
		     "",
		     "10 dias solares depois a guerra foi deflagrada. Vários representantes enviaram frotas estelares e armamentos de destruição em massa. O planeta foi quase "
		   + "totalmente destruído.",
		     "",
		     "Infelizmente isso não resolveu nada pois, a doença já estava se alastrando para todo o Universo, inclusive no Império. O problema foram os seres que "
		   + "não se cuidaram e passaram para outros. Com as várias mutações entre seres distintos, o vírus foi ficando mais mortal.",
		     ""
		};
		
		
		String[] respostaFatos = {
				"Você e a grande maioria evitou um massacre. Ao acreditar nos fatos, na ciência e não espalhar fake news o Império não teve justificativa para declarar uma guerra. Se isso acontecesse "
			  + "seria um pretexto para o Império atacar outros planetas com a justificativa de evitar que o vírus se espalhasse. Evitando que mais seres morressem."
		};
		
		String[] respostaEmCimaDoMuro = {
				"Nada contra. Desconfiar é bom. Afinal não precisamos acreditar em tudo. Mas tem que esperar por provas concretas e evitar espalhar fake news. Afinal, vidas importam. Mesmo "
			  + "que sejam os rebeldes e despresíveis Klingdons."
		};
		
		String[] respostaOutros = {
				"Sua opinião é muito importante! Mas no momento não podemos estar te atendendo senhor."
		};
		
		
		caminho.add(strEspaco);
		caminho.add(strCapitulo + titulo);
		
		centralizarTexto(titulo, larguraTela);
		System.out.println("");
		
		imprimirQuebra(enredo, 5);
		
		System.out.println("");
		
		valorFormatado = new DecimalFormat("###,###").format(geraTotal);
		
		centralizarTexto("+==================================================================================+", larguraTela);
		centralizarTexto("Total de mortos até o momento na galáxia: " + valorFormatado,larguraTela);
		centralizarTexto("+==================================================================================+", larguraTela);
		
		System.out.println("");
		System.out.print("Indique a sua opinião: ");
		resposta = leitor.nextInt();
		System.out.println("");
		
		switch(resposta) {
		case 1:
			imprimirQuebra(respostaKlingdonCulpado, 0);

			caminho.add(strResposta + "(Op 1) Acusar sem provas é um jogo perigoso. Os Klingdons quase foram dizimados por conta do fake news.");
			break;
			
		case 2:
			imprimirQuebra(respostaFatos, 0);

			caminho.add(strResposta + "(Op 2) Você e a grande maioria evitou um massacre. Confiar na ciência ");
			break;

		case 3:
			imprimirQuebra(respostaEmCimaDoMuro, 0);

			caminho.add(strResposta + "(Op 3) Nada contra. Desconfiar é bom. Afinal não precisamos acreditar em tudo.");
			break;
		
		default:
			
			imprimirQuebra(respostaOutros, 0);
			
			System.out.println("");
			System.out.print("Tempo de espera para atendimento: " + random.nextInt(100) + " horas solares.");


			caminho.add(strResposta + "(Op 4) Sua opinião é muito importante.");
		}
		
		pausarEntreTelas();
		
	}
	
	
	private static void mostrarCenaPlaceboKina() {
		int resposta;
		String titulo = capitulo[5];;
		
		String[] enredo = {
				"CattleLand é governado pelo Lorde Jezziahs que tinha como sonho ser almirante da nave XPTO Enterprise. Mas, como nem tudo na"
			  + " vida são flores (florestas não existem mais em Cattleland), ele não foi qualificado ao tão almejado posto. Ao entrar na política, "
			  + "depois de anos sem sucesso, se aventurou ao tentar chegar ao posto mais alto: governar um país inteiro. E conseguiu...",
			    "",
			    "Com a doença fora de controle e sem cura definitiva, o governante criou um remédio milagroso para conter a ansiedade do povo: a placeb0K1na. "
			  + "Na verdade aproveitou de um outro remédio existente e trocou o nome. O grande problema é que são utilizados nanarobôs que foram programados "
			  + "para combater outra doença. Resultado: a intenção, como não tinha vacina, era funcionar como placebo e enganar o povo. Só o governo sabe oficialmente "
			  + "que cerca de 60% das pessoas tem sintomas leves. Porém é fatal para 10%. Dessa forma, com as pessoas sendo 'curadas' ele posaria como herói.",
			    "",
			    "E os 10%? ... ah, morte natural. Todos morrem um dia. Coincidentemente a hora H chegou agora para todos. Teria dito o Lorde.",
			    "",
			    "Para alguns outros seres é mais fatal: mortalidade acima de 20%. Sem falar que exo-biohackers conseguem invadir e controlar os nanorôbos para atacar órgãos "
			  + "vitais ou até torná-los assassinos controlando transplantados de cérebros, deixando-os desorientados como um zumbi. E isso não é fake. Mas o governo não "
			  + "divulga esses problemas.",
			    "",
			    "E para variar Jezziahs queria mitar. Durante um passeio teve um insight daqueles e propôs um plano infalível: utilizar a nave-satélite-do-tempo, conhecida como Global "
			  + "Weather Forecast Star. Para quem não a conhece, além de prever o tempo localmente em questão de minutos é capaz de gerar chuvas, dias ensolarados e até evitar furacões. "
			  + "A atmosfera é bombardeada com nano cápsulas contendo uma solução química para cada situação. O Star no nome foi o apelido dado pelo povo por ele brilha no céu.",
			    "",
			    "A ideia seria bombardear a atmosfera com a placeb0K1na com os nano robôs dentro das nano cápsulas. Isso faria com que todos ficassem 'imunizados' de uma vez só. Mas antes "
			  + "de seguir com o plano é necessário enviar um equipe até a nave-satélite-do-tempo. Seria o primeiro remédio na nuvem.",
			    "",
			    "O que poderia dar de errado nesse plano 'genial' e 'infalível'??",
			    "",
			    "1 - Exo-bio hackers do Império poderiam invadir o satélite, reprogramar os nano robôs e controlar todos em Thera.",
			    "2 - Nada. O plano é perfeito.",
			    "3 - A revolução dos nano robôs inteligentes. Dotados de deep thinking e milhares reunidos pela primeira vez não é uma boa idéia.",
			    ""
		};
		
		String[] respostaBioHacker = {
				"Existem vários protocolos de segurança na nave satélite. A hiper rede neural de IA utiliza uma chave kriptoriana impossível de ser quebrada. Nem se utilizar computadores quânticos "
			  + "de 10 Gqubits. A título de comparação ele é mais forte que um adhamantium. O medo da população é um fato que chocou o mundo tempos atrás. Vamos voltar no tempo. Um hacker invadiu o poderoso sistema militar "
			  + "conhecido como GoW (God of War), baseado em galaxy computing, e quase provoca um guerra intergalática que poderia destruir totalmente o planeta. Nem é preciso dizer o nível de segurança desse "
			  + "lugar que oficialmente não existe de tão secreto que é. Deep fake memes mostram que ficam dentro do buraco negro Xt!2*na3400.er.milkw.",
			    "",
			    "Como esse gênio ou sortudo conseguiu? Reza a lenda, que um administrador de sistema neural de drones militares, postou uma selfie ao lado do poderoso GoW (God of War) e enviou para uma pretendente "
			  + "que conheceu no site de encontros galáticos Kindher. Só para impressioná-la, claro. Afinal ela confessou ser uma sapiossexual. E ele era o cara. Bem, era.",
			    "",
			    "A foto foi ampliada 1000x e pelo reflexo na íris de seu olho foi possível ver um post-it digital com a senha anotada! Todos drones estavam prontos para atacar quando de repente foi abortado. No final, descobriram "
			  + "que era uma neuro bot de reconhecimento de imagem por trás do perfil e que só queria chamar a atenção do que seria capaz. É, no Kindher cada perfil é uma surpresa atrás da outra.",
			    "",
			    "Voltando desse flashback, o fato é que o Império não tem interesse em Thera. E a nossa tecnologia não é tão avançada para eles.",
			    "",
			    "Dizem que ele nunca mais conseguiu arranjar emprego. E desapareceu por uns tempos. Recentemente passou num concurso público e voltou como administrador de um sistema neural que controla... a Weather Star!"
		};
		
		
		String[] respostaPlanoPerfeito =  {
				"No universo nada é perfeito no que se refere aos seres vivos. De acordo com a mitologia, Xebolus tinha planos infalíveis para derrotar "
			  + "Mokina e se tornar o rei do Universo. Elaborava e arquitetava vários cenários mas sem lógica nenhuma, sem dados concretos e ao botá-los "
			  + "em prática, sempre falhava. Faltava a Xebolus conhecimentos científicos primários. Para essas pessoas que insistem e falham sempre com "
			  + "seu planos mirbolantes e infalíveis chamamos de complexo de Xebolus. E foi o que aconteceu.",
			    "",
			    "No primeiro teste, mostrado ao vivo (o que não é ao vivo hoje em dia, nossa privacidade é zero) foram disparados milhares de placeb0K1na "
			  + "nas nuvens. Claro, o comando foi dado pelo Jezziahs, diretamente do Weather Star. O resultado foi um verdadeiro fiasco. Grande parte "
			  + "da população estava utilizando a máscara facial superaderente de UV para evitar o contágio. Além de eliminar os vírus ela destrói "
			  + "também os nano robôs. Ela foi adotada por mais de 60% da população. Mas para o governo, como pouca gente foi afetada, isso mostra o "
			  + "sucesso da operação. Estatística aqui não serve para nada. O negócio é distorcer e manipular os fatos.",
			    "",
			    "E o problema continua. Devido à pressa em colocar o plano no ar, todo o processo não foi testado anteriormente e agora o sistema parece "
			  + "estar em loop infinito. Era para ser executado uma vez. O risco é superaquecer todo o sistema e até a destruição da nave toda."
		};
		
		String[] respostaRevolucaoNano = {
				"Essa é um possibilidade plausível e que não deveria ser descartada. Não sabemos com seria o comportamento caso um nano robô dotado de deep "
			 + "thinking de última geração pensasse fora da caixa e conseguisse reunir e convencer outros milhares de nanos a seguí-lo. Se você pensasse "
			 + "assim décadas atrás, seria aconselhado a parar de ver filmes de sci-fi."
		};
		
		caminho.add(strEspaco);
		caminho.add(strCapitulo + titulo);
		
		centralizarTexto(titulo, larguraTela);
		System.out.println("");
		
		imprimirQuebra(enredo, 5);
		
		System.out.println("");
		System.out.print("Digite a sua resposta: ");
		resposta = leitor.nextInt();
		System.out.println("");
		
		switch(resposta) {
		case 1:
			imprimirQuebra(respostaBioHacker, 0);

			caminho.add(strResposta + "(Op 1) Nenhum sistema é 100% seguro.");
			break;
			
		case 2:
			imprimirQuebra(respostaPlanoPerfeito, 0);

			caminho.add(strResposta + "(Op 2) Complexo de Xebolus: o plano infalível.");
			break;
		default:
			
			imprimirQuebra(respostaRevolucaoNano, 0);
	
			
			caminho.add(strResposta + "(Op 3) Revolução Nano Robôs.");
			break;
		}
		
		ativarCenaNanoFarm = true;
		
		pausarEntreTelas();
	}
	
	

	// próximas melhorias: trazer 3 finais distintas
	private static void mostrarCenaDeathStar() {
		//int resposta;
		String titulo = capitulo[6];
		
		String[] enredo = {
				"Com o Weather Star descontrolado, seria necessário abortar o processo. Isso pode até ser feito de forma remota a partir da Thera. "
			  + "Entretanto, com o superaquecimento, o acelerador de partículas responsável pela fusão nuclear da nave corre o risco de explodir também. "
			  + "Se isso acontecer, todos os satélites podem ser afetados e ocasionaria uma chuva de detritos perigosos em Thera. Teria que parar o acelador "
			  + "mas devido a um bug na parte neural da IA, ele não consegue se auto desligar. O processo tem que ser manual.",
			    "",
			    "Quando a notícia se espalhou o pânico foi geral. Afinal de contas, uma explosão de fusão nuclear no seu quintal praticamente poderia "
			  + "ser catastrófica. Não bastasse isso, com o Weather Star descontrolado, furacões e chuvas aumentaram em frequência e intensidade. "
			  + "Devastando e inundando cidades inteiras. Além de secas em outras regiões. Tudo isso não acontecia há decadas. ",
			  	"",
			  	"O remédio também se tornou um problema: o povo que não se protegia acabou inalando altas doses do remédio, causando sérios problemas. Muitos,"
			  + "tiveram que ser atendidos emergencialmente nos hospitais, deixando-os lotados. Um verdadeiro caos. Falta tudo, de remédios a oxigênio.",
			    "",
			  	"O povo agora apelidou a nave de Death Star. ",
			    "",
			    "Os cientistas em Thera descobriram quem foi o responsável por tudo isso: o nosso amigo do Khinder. Sim, de novo ele. Ele foi o responsável "
			  + "pelo programa do placeb0K1na nas nuvens e sua programação neural alterou o fluxo de energia no acelerador de partículas. Ele tinha marcado "
			  + "um encontro holográfico de realidade aumentada com uma ficante e para terminar logo o programa, não com a ficante, e sim codando, ele utilizou "
			  + "e alterou alguns macrosserviços e sem testar liberou tudo na produção! Nem é necessário dizer o estrago disso.",
			    "",
			    "Alguns tablóides dizem que esse foi o real motivo do Império nem se preocupar em nos hackear. O nosso jogador já joga para o inimigo. "
			  + "E de repente, a Weather Star entra em processo de shut down. O Lorde Jezziahs já ia anunciar que foi graças à tropa enviada para sanar "
			  + "o problema. Os telejornais anunciaram que a tropa de elite nem tinha chegado à nave. Será que foi o nosso amigo do Khinder tentando se "
			  + "redimir pelas suas mancadas galáticas?",
			    "",
			    "Quando a tropa chegou à nave, eis que depararam com o nosso salvador: Mario! Sim, ele estava regressando a Thera quando soube da notícia e decidiu "
			  + "ir na nave para tentar arrumá-la. E como ele entrou lá se é cercada de protocolos de segurança? Simples, nosso engenheiro conheceu o cara do Khinder "
			  + "semanas atrás para ajustar o sistema de fluxo de energia e tirou uma holo selfie 3D com ele na sala de administração do computador quântico neural e... sim "
			  + "as senhas todas estavam anotadas no post-it digital!",
			  	"",
			  	"Mario não estava sozinho. Para arrumar o acelerador ele teve ajuda de dois droides. Afinal um ser humano não iria resistir a radiação extrema. E o mais "
			  + "surpreendente: um Klingdon de confiança que corrigiu todas as falhas da programação neural. Sim, nem todos Klingdons são iguais. Mario aprendeu a não "
			  + "julgar os seres intergaláticos depois que passou a conviver com alguns deles.",
			  	"",
			  	"Ao regressar a Thera, todos foram condecorados pela Federeção Intergalática e pela Organização das Nações de Thera pelo reconhecimento a sua "
			  + "bravura e ato de heroísmo. Se a Weather ou Death Star explodisse poderia gerar uma reação em cadeia afetando todos os outros planetas. Aliás em outros "
			  + "tempos o universo era um imenso vazio. Hoje está inundado de lixo espacial de naves, destroços de planetas, material radioativo que geram explosões de tempos em"
			  + "tempos e planetas em órbitas errantes após serem ejetados pela explosão de sua estrela. Nesse tenebroso cenário, basta uma faísca para desencadear o caos.",
			  	"",
			  	"Agora o povo chama o nosso engenheiro encanador de Super Mario e a sua equipe de Guardiões... do Universo."
			  
		};
		
		
		caminho.add(strEspaco);
		caminho.add(strCapitulo + titulo);
		
		centralizarTexto(titulo, larguraTela);
		System.out.println("");
		
		imprimirQuebra(enredo, 5);
		
		System.out.println("");
		
		statusAtual = StatusJogo.VIVO;
		caminho.add(strResposta + "FIm do jogo.");
		
		pausarEntreTelas();
		
	}

	
	private static void mostrarCenaRevolucaoNano() {
		String titulo = capitulo[7];
		
		String[] enredo = {
				"Fortes furacões fizeram com que os nanorobôs se deslocassem para um outra região do planeta, num deserto onde "
			  + "no passado era uma densa e enorme floresta rica em biodiversidade e cortada por um imenso rio.",
			  	"",
			  	"Décadas atrás, homens e máquinas destruíram a floresta afetando o clima em todo o planeta. O enorme rio, virou lagoa "
			  + "em alguns pontos. No desespero para reverter a situação, uma versão anterior do Weather Star, foi desenvolvida para "
			  + "dar vida ao lugar.",
			  	"",
			  	"Foram despejados nanorobôs em toda área com a missão de adubar a terra e também provocar mais chuvas. O começo foi promissor com "
			  + "bons resultados mostrando o potencial da nova tecnologia.",
			  	"",
			  	"Os nanorobôs foram aprendendo a lidar com o ambiente. A grande falha foi não prender os poucos resposáveis que estavam "
			  + "explorando a terra atrás de minérios, contaminando rios. Além daqueles que estavam queimando e desmatando no que sobrou. Essa versão de "
			  + "nanorobôs eram baseados em deep learning. E, com o tempo aprenderam que o correto era destruir tudo e foram replicando as instruções "
			  + "para os outros.",
			  	"",
			  	"A situação piorou e o local virou um imenso deserto, rios secaram e o oceano baixou um pouco o nível. A falta de água fez surgir a indústria "
			  + "da água artificial. E os nanarobôs da primeira geração ficaram por lá mesmo, totalmente sem monitoramento por anos. Alguns ainda ativos e solitários.",
			  	"",
			  	"Não até agora com a chegada de milhares dotados de deep thinking e alta capacidade de se auto conectarem..."
			  	
		};
		
		
		pausarMiliSegundos(3000);
		rolarTela(15, 1);
		
		centralizarTexto("Espere... a estória não acabou ainda...   " , larguraTela);
		pausarMiliSegundos(3000);
		
		rolarTela(5, 10);
		pausarEntreTelas();
		rolarTela(25, 0);
		
		centralizarTexto(titulo, larguraTela);
		System.out.println("");
		
		imprimirQuebra(enredo, 5);
		
		rolarTela(5, 10);
		
		centralizarTexto("To be continued...    I'll be back!  " , larguraTela);
		
	}
	
	/*
	 * *****************************************************************
	 * 
	 * Métodos auxiliares
	 * 
	 * *****************************************************************
	 */
	private static void mostrarAsciiStatus(StatusJogo status) {
		
		switch(status) {
		case VIVO :
			imprimirTexto(mostrarStatusVivoAscii(), true);
			break;
			
		case MORTO:
			imprimirTexto(mostrarStatusMortoAscii(), true);
			break;
			
		case ZUMBI:
			imprimirTexto(mostrarStatusZumbiAscii(), true);
			break;
		
		case SENSIVEL:
			imprimirTexto(mostrarStatusNervosoAscii(), true);
			break;
			
		case BLOQUEADO:
			imprimirTexto(mostrarStatusBloqueadoAscii(), true);
			break;
			
		default:
			imprimirTexto(mostrarStatusAlertAscii(), true);
		}
	}
	

	private static void pausarEntreTelas() {
		
		System.out.println("");
		System.out.print("Digite 1 para continuar a saga... ");
		String resposta = leitor.next();
	}

	private static void pausarMiliSegundos(int miliSeg) {
		try {
			TimeUnit.MILLISECONDS.sleep(miliSeg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	private static void limparTela() throws IOException, InterruptedException {
		if (System.getProperty("os.name").contains("Windows")) {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} else {
			Runtime.getRuntime().exec("clear");
		}
            
	}
	
	private static void rolarTela(int linhas, int tempoMiliSeg) {
		for(int i = 0; i <= linhas; i++) {
			System.out.println("");
			pausarMiliSegundos(tempoMiliSeg);
		}
	}
	
	private static void imprimirTexto(String[] texto, boolean centralizado) {
		for (String linha : texto) {
			if (linha == "") {
				pausarMiliSegundos(1000);
			}
			if(centralizado) {
				centralizarTexto(linha, larguraTela);
			} else {
				System.out.println(linha);
			}
				
		}
	}
	
	
	private static void imprimirQuebra(String[] srtTexto, int velocidade) {
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
				
				while((linha.length() - posicaoInicial ) > larguraTela) {
					while(((posicaoAtual - posicaoInicial) < larguraTela) && (posicaoAtual >= 0)) { 
						ultimaOcorrencia = posicaoAtual; 
						posicaoAtual = linha.indexOf(charBusca, posicaoAtual + 1); 
					}
					
					texto = linha.substring(posicaoInicial, ultimaOcorrencia);
					imprimirCaracter(texto, velocidade);

					posicaoAtual = ultimaOcorrencia + 1; 
					posicaoInicial = ultimaOcorrencia + 1;

				}
				texto = linha.substring(posicaoInicial);
				imprimirCaracter(texto, velocidade);
			} else {
				System.out.println("");
			}
			
		}

	}

	
	private static void imprimirCaracter(String linha, int velocidade) {
		int contaCaracteres = 0;
		

		if (linha == "") {
			System.out.println("");
			pausarMiliSegundos(velocidade * 20);
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

				pausarMiliSegundos(velocidade);
			}
			System.out.println("");
		}

		
	}
	

	
	private static void centralizarTexto(String texto, int tamTela) {
		int inicioCol = (tamTela - texto.length())/2;
		String novoTexto;
		
		novoTexto = " ".repeat(inicioCol) + texto;
		System.out.println(novoTexto);
		
	}
	
	
	private static void imprimirListaCaminho(ArrayList<String> historico) {
		// acrescenta o status no jogo
		System.out.println(nomeGamer + " sua saga na aventura # " + contaTentativas + " chegou a um final. Seu status no jogo foi: " + statusAtual.getDescricao());
		mostrarAsciiStatus(statusAtual);
		pausarMiliSegundos(3000);
		
		// mostra o histórico de escolhas
		if(statusAtual == StatusJogo.BLOQUEADO || statusAtual == StatusJogo.SENSIVEL) {
			System.out.println("Hasta la vista! Esse foi o seu breve caminho nessa jornada estelar. Espero que você volte, baby!");
		} else {
			System.out.println("Esse é o histórico das suas escolhas nessa jornada estelar.");
		}
			
		historico.add(strEspaco);
		historico.add(strStatusJogo + "Seu status final nessa aventura: " + statusAtual.getDescricao());
		
		System.out.println("");
		
		for(String texto : historico) {
			System.out.println(texto);
		}
		
		System.out.println("");
		pausarMiliSegundos(3000);
	}


	
	/*
	 ******************************************************************************************
	 *
	 *   Início dos ASCII arts
	 * 
	 * 
	 ******************************************************************************************
	 */
	
	private static String[] mostrarStatusVivoAscii() {
		String[] statusVivo = {
				"",
				"It's me... alive!",
				"",
				"Yabba dabba doo! Mario sobreviveu!",
				"",
				"──────────────████████──██████──",
				"──────────████▓▓▓▓▓▓████░░░░░░██",
				"────────██▓▓▓▓▓▓▓▓▓▓▓▓██░░░░░░██",
				"──────██▓▓▓▓▓▓████████████░░░░██",
				"────██▓▓▓▓▓▓████████████████░░██",
				"────██▓▓████░░░░░░░░░░░░██████──",
				"──████████░░░░░░██░░██░░██▓▓▓▓██",
				"──██░░████░░░░░░██░░██░░██▓▓▓▓██",
				"██░░░░██████░░░░░░░░░░░░░░██▓▓██",
				"██░░░░░░██░░░░██░░░░░░░░░░██▓▓██",
				"──██░░░░░░░░████████░░░░██████──",
				"────████░░░░░░░░██████████▓▓██──",
				"──────██████░░░░░░░░░░██▓▓▓▓██──",
				"──░░██▓▓▓▓██████████████▓▓██────",
				"──██▓▓▓▓▓▓▓▓████░░░░░░████──────",
				"████▓▓▓▓▓▓▓▓██░░░░░░░░░░██──────",
				"████▓▓▓▓▓▓▓▓██░░░░░░░░░░██──────",
				"██████▓▓▓▓▓▓▓▓██░░░░░░████████──",
				"──██████▓▓▓▓▓▓████████████████──",
				"────██████████████████████▓▓▓▓██",
				"──██▓▓▓▓████████████████▓▓▓▓▓▓██",
				"████▓▓██████████████████▓▓▓▓▓▓██",
				"██▓▓▓▓██████████████████▓▓▓▓▓▓██",
				"██▓▓▓▓██████████──────██▓▓▓▓████",
				"██▓▓▓▓████──────────────██████──",
				"──████──────────────────────────",
				"",
				"Selfie térmico aural. Arquivo pessoal.",
				""
		};
		
		return statusVivo;
	}
	
	
	private static String[] mostrarStatusZumbiAscii() {
		String[] statusZumbi = {
				"",
				"Oh no! Você virou um zumbi frankstein!",
				"",
				"E agora, quem poderá nos salvar??",
				"",
				"                    .....            ",
				"                   C C  /            ",
				"                  /<   /             ",
				"   ___ __________/_#__=o             ",
				"  /(- /(\\_\\________   \\              ",
				"  \\ ) \\ )_      \\o     \\             ",
				"  /|\\ /|\\       |'     |             ",
				"                |     _|             ",
				"                /o   __\\             ",
				"               / '     |             ",
				"              / /      |             ",
				"             /_/\\______|             ",
				"            (   _(    <              ",
				"             \\    \\    \\             ",
				"              \\    \\    |            ",
				"               \\____\\____\\           ",
				"               ____\\_\\__\\_\\          ",
				"             /`   /`     o\\          ",
				"             |___ |_______|.. . b'ger",
		};
		
		return statusZumbi;
	}
	
	
	private static String[] mostrarStatusNervosoAscii() {
		String[] nervoso = {
				"",
				"Você é desprezível!",
				"",
				"Ops, desculpe velhinho. Essa não é a nossa opinião!",
				"",
				"                            ___                                ",
				"                    __  _/:::>__                               ",
				"                   /:/_/::/ _/::>                              ",
				"                 _/:(/:::\\_/::/                               ",
				"                _):::::::::::::\\                              ",
				"              _/::::::::::::::::\\____                         ",
				"             /      \\:::::::::/      \\                       ",
				"            |  ::/\\  ::::::::  / \\::   |                     ",
				"            / ::/  \\  ::::::  /   |:::/                       ",
				"           /:::|    \\::::::::/    |:::\\                      ",
				"          /::::|     \\::::::/     |::::\\                     ",
				"        ,------:      \\::::/      :------,                    ",
				"       /   ___  \\0    /    \\ 0   / ___  \\                   ",
				"      : ,-' ) `  `---'      `---'   ( `-,  :                   ",
				"      \\_    \\         '     `        \\_  _/                 ",
				"        \\____\\                         \\/                   ",
				"              \\                  _______\\________            ",
				"               \\              ,-'                )            ",
				"                \\           ,-    ,----------- _/             ",
				"                 \\             ,-'      \\\\ ) _/             ",
				"                  (___________/__________\\\\ /                ",
				"                   :;;;\\___________________)                  ",
				"            ______,:;;;;;;;;:______                            ",
				"          ,;;;;;;;;;;;;;;;;;;;;;;;;\\_                         ",
				"         /;;;;;;;;;;;;;;;;;;;;;;;;;;;\\_                       ",
				"        /;;;;;;__;;;; ;;;;;; ;;;;;;;;;;\\  Targon              ",
				"                                                               ",
				" ------------------------------------------------------------- ",
				" Thank you for visiting https://asciiart.website/",
				" This ASCII pic can be found at",
				" https://asciiart.website/index.php?art=cartoons/warner%20bros",
				""
		};
		
		return nervoso;
	}
	
	private static String[] mostrarStatusMortoAscii() {
		String[] statusMorto = {
				"",
				"O triste fim do... engenheiro encanador quântico",
				"",
				"        _.---,._,'                                            ",
				"       /' _.--.<                                              ",
				"         /'     `'                                            ",
				"       /' _.---._____                                         ",
				"       \\.'   ___, .-'`                                        ",
				"           /'    \\             .                             ",
				"         /'       `-.          -|-                            ",
				"        |                       |                             ",
				"        |                   .-'~~~`-.                         ",
				"        |                 .'         `.                       ",
				"        |                 |  R  I  P  |                       ",
				"  jgs   |                 |           |                       ",
				"        |                 | M A R I O |                       ",
				"         \\               \\|           |//                     ",
				"   ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ ",
				""
		};
		
		return statusMorto;
	}
	
	private static String[] mostrarStatusBloqueadoAscii() {
		String[] stop = {
				"",
				"Stop in the name of... Federação de Games!",
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
	
	
	private static String[]	mostrarStatusAlertAscii() {
		String[] alert = {
				"",       
				"                           .i;;;;i.                           ",       
				"                         iYcviii;vXY:                         ",       
				"                       .YXi       .i1c.                       ",       
				"                      .YC.     .    in7.                      ",       
				"                     .vc.   ......   ;1c.                     ",       
				"                     i7,   ..        .;1;                     ",       
				"                    i7,   .. ...      .Y1i                    ",       
				"                   ,7v     .6MMM@;     .YX,                   ",       
				"                  .7;.   ..IMMMMMM1     :t7.                  ",       
				"                 .;Y.     ;$MMMMMM9.     :tc.                 ",       
				"                 vY.   .. .nMMM@MMU.      ;1v.                ",       
				"                i7i   ...  .#MM@M@C. .....:71i                ",       
				"               it:   ....   $MMM@9;.......,;tti               ",       
				"              :t7.  .....   0MMMWv.........,;St.              ",       
				"             .nC.   .....   IMMMQ...........,czX.             ",       
				"            .ct:   ....... .ZMMMI............:76Y.            ",       
				"            c2:   ..........Y$M@t.............inZY            ",       
				"           vov   ...........c$MBc..............iI9i           ",       
				"          i9Y   ............7@MA,...............;AA:          ",       
				"         iIS.  .............;@MI.................;Ez.         ",       
				"        .I9.  ...............8M1..................C0z.        ",       
				"       .z9;  ............... .i:...................zWX.       ",       
				"       vbv  ...............      ................. :AQY       ",       
				"      c6Y.  ..............:t0@@QY. ................ :8bi      ",       
				"     :6S. ................EMMMMMMI. ............... .;bZ,     ",       
				"    :6o,  ...............i#MMMMMM#v.................  YW2.    ",       
				"   .n8i ................. tMMMMM@C:.................. .1Wn    ",       
				"   7Uc. .:...............   i1t;,..................... .UEi   ",       
				"   7C...::::::::::::,,,,..        ....................  vSi.  ",       
				"   ;1;...,,::::::,.........       ..................    Yz:   ",       
				"    v97,.........                                     .voC.   ",       
				"     izAotX7777777777777777777777777777777777777777Y7n92:     ",       
				"       .;CoIIIIIUAA666666699999ZZZZZZZZZZZZZZZZZZZZ6ov.       ",   
				""
				
		};
		
		return alert;
	}
	
	private static String[] mostrarTheraAscii() {
		String[] thera = {
				"Thera e suas 4 luas",
				"",
				"                .            (*)  .-'.  ':'-.      .         ",
				"       .                        .''::: .:    '.           (*)",
				"                :             /   :::::'      \\   .        ",
				"                   (*)   ;    ;.    ':' `       ;            ",
				"     .                        |       '..       |      :     ",
				"          .                   ; '      ::::.    ;            ",
				"     ,     .  .        .      \\       '::::   /         .  ",
				"                                '.      :::  .'              ",
				"jgs                                '-.___'_.-'      (*)      ",
				"         :          .       .                    .        .  "
		};
		
		return thera;
		
	}
	
	private static String[] mostrarGameOverAscii() {
		String[] gameOver = {
				"   _______      ___      .___  ___.  _______ ",
				"  /  _____|    /   \\     |   \\/   | |   ____|",
				" |  |  __     /  ^  \\    |  \\  /  | |  |__   ",
				" |  | |_ |   /  /_\\  \\   |  |\\/|  | |   __|  ",
				" |  |__| |  /  _____  \\  |  |  |  | |  |____ ",
				"  \\______| /__/     \\__\\ |__|  |__| |_______|",
				"                                             ",
				"  ______   ____    ____  _______ .______      ",
				" /  __  \\  \\   \\  /   / |   ____||   _  \\     ",
				"|  |  |  |  \\   \\/   /  |  |__   |  |_)  |    ",
				"|  |  |  |   \\      /   |   __|  |      /     ",
				"|  `--'  |    \\    /    |  |____ |  |\\  \\----.",
				" \\______/      \\__/     |_______|| _| `._____|" ,
				"ASCII Art by www.network-science.de",
				"",
				"MARIO CONTRA O VÍRUS ZUMBI BY MARCELO KENJI UEHARA",
				""
		};
		
		return gameOver;		    
	}
	
	private static String[] mostrarAberturaJogo() {
		String[] aberturaGame = {
				"",
				"    ,aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa, ",
				"   ,d'      :                .          .          ;                   ,d^. ",
				"   8'    .         :                                         ,         8' 8 ",
				"   8,                      .      :                  *            `    8aP´ ",
				"   `b,   .     .  +           .          *   :                 .       `8,  ",
				"    `b,     8888b     d888   :   d88888888888b. 8888888 .d88888b.       `b, ",
				"     `8 .   88888b   d8888      d88888888   Y88b  888  d88P8 8Y88b   *   `8 ",
				"      8     888888b.d88888 *   d88P888888 +  888  888  888     888        8 ",
				"     ,8 .   8888Y88888P888    d88P 888888   d88P  888  888  .  888 .     ,8 ",
				"    ,d'     8888 Y888P 888   d88P  8888888888P8   888  888     888      ,d' ",
				"    8'      8888  Y8P  888  d88P   888888 T88b  * 888  888     888      8'  ",
				"    8,      8888   8   888 d8888888888888  T88b   888  Y88b. .d88P      8,  ",
				"    `b,     8888     . 888d88P     888888   T88b8888888 8Y88888P8       `b, ",
				"     `8            ;           :                 *   .      .         *  `8 ",
				"      8     ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        8 ",
				"     ,8  .    NUMA GALÁXIA NÃO TÃO DISTANTE CONTRA O VÍRUS ZUMBI       *  8 ",
				"    ,d'         .            ,                *                 .        ,8 ",
				"  aadbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  ,d' ",
				" d'                                                                d'',d'   ",
				" 8,                                                                8, ,d'   ",
				" `YaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaadbP'     ",
				""
		};
		
		return aberturaGame;
	}
	
	
}
