import java.io.IOException;
import java.util.ArrayList;
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
	private static final String strStatusJogo = " *--~~~=:>[XXXXXXXXX]> ";
	private static int contaTentativas;
	
	private static ArrayList<String> caminho = new ArrayList<String>();
	
	
	private static Scanner leitor = new Scanner(System.in);
	
	private static String[] capitulo = {
			"MARIO NUMA GALÁXIA NÃO TÃO TÃO DISTANTE COMBATENDO O VIRUS ZUMBI",
			"Capítulo 01 - Longe de casa, longe de tudo",
			"Capítulo 02 - Os 'amáveis Klingdons' e sua bebiba super batizada",
			"Capítulo 03 - Houston, we have a problem"
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
		
		limpaTela();
		rolarTela(7,5);
		imprimeTexto(aberturaJogo(), true);
		pausaMiliSegundos(1000);
		
		rolarTela(2,5);
		pausaEntreTelas();
		
		continuaSaga = apresentacao();
		
		if (continuaSaga) {
			limpaTela();
			abertura();
			
			while(continuaSaga) {
				statusAtual = StatusJogo.VIVO;
				contaTentativas ++;
				
				caminho.add(strEspaco);
				caminho.add(strTentativas + "Aventura # " + contaTentativas);
				
				switch(resposta.toUpperCase()) {
				case "1":
					limpaTela();	
					cenaKlingdonSentarJunto();
					
				case "2":
					limpaTela();
					cenaKlingdonNaMesa();
					
					if(statusAtual == StatusJogo.DOENTE) {
						limpaTela();
						cenaMarioUti();
					}
					
					
				default:
					System.out.println("Escolha uma opção válida!");
				}
				
	
				rolarTela(5,0);
				
				imprimeListaCaminho(caminho);
				
				System.out.println("Você gostaria de continuar e conhecer outras possibilidades?");
				System.out.println("");
				System.out.println("N - Não. Vou parar por aqui.");
				System.out.println("1 - " + capitulo[1]);
				System.out.println("2 - " + capitulo[2]);
				System.out.println("");
				System.out.print("Digite a sua opção: ");
				resposta = leitor.next();		
				
				if (resposta.equalsIgnoreCase("N")) {
					continuaSaga = false;
				} else {
					continuaSaga = true;
				}
			}
			
		} else {
			
			rolarTela(10,10);
			
			imprimeListaCaminho(caminho);
			
			
		}

		
		rolarTela(10, 10);
		imprimeTexto(gameOver(), true);
		
		leitor.close();

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
		
		System.out.println("Olá, sou Jarvis, seu assistente virtual. Antes de começarmos preciso de algumas informações pessoais.");
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

			System.out.println(aviso);
			System.out.println("Provavelmente véio vc achará esse jogo entendiante demais. Vai jogar PS2! Ops, PS3? PS4? PS5? Enfim...");
			
			continuar = false;
			
			pausaMiliSegundos(1000);

			
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
		
		String[] intro = {"Há muito tempo numa galáxia não tão tão distante ..."};
		
		String[] titulo = {"MARIO NUMA GALÁXIA NÃO TÃO TÃO DISTANTE COMBATENDO O VIRUS ZUMBI"};
		
		String [] resumo = {"Mario, o nosso herói, é um engenheiro encanador quântico que vive num planeta azul",
							"conhecido com Thera, onde mora com a família e pets num reino chamado Cattleland.",
							"",
							"Thera e suas 4 luas ficam a algumas centenas de parsecs de distância de Babooine. No reino de",
							"Cattleland, algumas pessoas ainda acreditam que o planeta é plano e em outras teorias",
							"conspiratórias, como a de que o novo vírus zumbi desconhecido ser criação do Império Klingdon.",
							"Well... convenhamos, uma coisa não tem nada a ver com a outra meus amigos!",
							"",
							"E também do fato ou fake que o rei mitológico Jezziahs é da linhagem sombria da força! Sera?",
							"Lord Jezziahs e seus fiéis súditos parecem viver em um outro mundo, onde tudo está em perfeita harmonia",
							"e que a ameaça do vírus está sob controle, afinal eles tem em mãos a cura para toda a galáxia:",
							"a poderosa placeb0K1na. Caso não tome você corre o risco de virar Dhanos, King G. Roll ou até o Browser!",
							"",
							"Mas, a poderosa Federação Galática da Saúde (FGS) tem fortes evidências científicas que esse suposto 'remédio' é", 
							"responsável em matar seletivamente alguns seres pois, é fabricado com nano robôs dotados de IA.",
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
							"Vamos começar! Prepare a sua pipoca. Você disse pipoca?",
							"",
							"Para uma melhor experiência gere o .jar e execute-o na linha de comando."
		};
		
		rolarTela(15, 0);
		imprimeTexto(intro, true);
		pausaMiliSegundos(3000);
		
		rolarTela(40, 0);
		
		imprimeTexto(titulo, true);
		System.out.println("");
		
		pausaMiliSegundos(1000);
		
		imprimeCaracter(resumo, 15);
		
		pausaMiliSegundos(3000);
		rolarTela(10, 300);
		
		imprimeTexto(thera(), true);
		rolarTela(15, 300);
		pausaMiliSegundos(1000);
		

	}
	
	// capitulo 01
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
				"Nosso herói está a bordo do intergalático cruzador MK70z_ fazendo reparos no hiper                           ",
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
		
		titulo = capitulo[1];
		caminho.add(strEspaco);
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
	
	// capitulo 02
	private static void cenaKlingdonNaMesa() {
		int resposta;
		String titulo;
		
		String[] respostaRecusarBebida = {
			"Ao recusar a bebida, os dois Klingdons partem para cima do Mario. E algo surpreendente acontece.",
			"Após ser encurralado, Mario subiu na mesa, deu vários pulos e com o punho cerrado socou o teto. Nesse momento,",
			"ele cresceu um pouco???!! Ficou metalizado e forte como adhamantium. E bastaram alguns golpes para derrubá-los.",
			"",
			"Uns escutaram um barulho de moedas caindo. Outros, como os Thoads, juram que o Mario disse algo como 'Pelos poderes", 
			"de GraceCool, EU TENHO A FORÇA!!!'. A verdade é que, nesses tempos de deepfake memes todo cuidado é pouco.",
			"E quem é GraceCool???",
			"",
			"No final os dois Klingdons é que foram na enfermaria! Depois desse fato bizarro todos passaram a respeitá-lo.",
			"Reza a lenda que Mario aprendeu alguns 'truques' quando atendeu a um chamado de uma nave que tinha afundado",
			"num pântano e viu 'coisas' que um velhinho baixinho e orelhudo ensinava ao seu jovem aprendiz.",
			"",
			"Após arrumar vários entupimentos no acelerador de partículas da aeronave, o bom velhinho disse ao",
			"Mario algo como: 'a força dentro de você eu sinto!'. 'Coitado mal fala português direito', pensou Mario",
			"sem entender do que se tratava. Não até hoje, após nocatear os dois.",
			""
		};
		
		String[] respostaBeber = {
				"Você aceitaria a bebida dos Klingdons por educação??? Em épocas como essa é melhor revisar seus conceitos!",
				"Como ele não recusou da primeira vez, todos os dias os Klingdons enche o caneco do nosso amigo.",
				"Porém, alguns dias depois, um dos Klingdons adoece e vira zumbi. O outro morre dias depois.",
				"",
				"O sistema de AI rastreou todos que tiveram contato, deixando-os em quarentena. Sim, eles vivem num mundo",
				"onde nanarobôs são implantados dentro de você. Te monitoram 24x7, como diriam em Thera.",
				"",
				"But now.. Houston, we have a problem."				
		};
		
		String[] respostaDerrubarBebida = {
				"Ahhhh o 'velho truque' do esbarrão do cotovelo na bebida batizada do inimigo! Elementar meu caro... Mario!",
				"Maaaasss, um deles fica furioso com o descuido e desperdício e só não partiu para briga por que na semana passada",
				"ele foi salvo pelo nosso engenheiro encanador. Afinal de contas, a adega mais próxima fica a anos-luz de distância!",
				"",
				"Salvar a pele deles é um dos poucos motivos para que eles não te matem. Afinal são guerreiros. E existe uma espécie",
				"de código de honra. Entretanto, não espere que isso dure para sempre. A sua próxima pisada na bola pode ser fatal.",
				"",
				"Dessa forma, não diria que Mario ganhou uma vida extra. Acredito mais em algumas barras de energia.",
				""
		};
		
		String[] enredo = {
				"Na verdade os Klingdons perguntaram por sarcasmo. Eles não tem modos e independentemente da",
				"resposta eles iriam sentar do mesmo jeito. O problema é que eles falam gritando, gesticulam",
				"muito, batem na mesa e salivam muito. E se deixar, pegam a sua comida e bebida ou oferece",
				"a bebida Abhysmum altamente alcoólica e perigosa para os humanos.",
				"Essa é pior do que a BuracoNegro dos piratas espacias do C@rybeean ou a darkSideSith Black Vhader",
				"Label Edition do Império. Não é da novela das 9 que estamos falando aqui...",
				"",
				"E claro, encheram o copo do nosso amigo. O último que se recusou ou reclamou foi parar na",
				"enfermaria e outros, como costumavam dizer, a sete palmos debaixo da terra. Recusá-la é altamente",
				"ofensivo para eles. Vai entender, né? O que não é ofensivo para esses animais?",
				"",
				"Na TV holográfica está passando notícias mais recentes dizendo que o planeta Klingdon a coisa",
				"está cada vez mais séria. A suspeita é que um animal doente encontrado morto é o vetor zero.",
				"Mas, o detalhe é que ele se alimenta de uma fruta que é a base dessa maldita bebida.",
				"",
				"Mario sabe disso por que no ano passado foi arrumar o computador quântico responsável pela colheita e",
				"produção da bebida. E lá não existe controle de qualidade. Frutas comidas e podres fazem parte da",
				"composição da bebida! Nosso amigo pensou que era um bug do IA.",
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

			statusAtual = StatusJogo.DOENTE;
			
			caminho.add(strResposta + "Você aceitou a bebida dos Klingdons por educação??? Em épocas como essa é melhor revisar seus conceitos!");
			
			
			break;

		default:
			// qualquer outra opção, derrubar a bebida
			imprimeTexto(respostaDerrubarBebida, false);

			continuaSaga = true;
			
			caminho.add(strResposta + "Ahh... o velho truque do cotovelo para derrubar a bebida batizada do inimigo!");
		
			
		}
		
		pausaEntreTelas();
		
		//return continuaSaga;

	}
	
	private static void cenaMarioUti() {
		int resposta;
		
		String titulo = capitulo[3];
		
		String[] respostaOrgao = {
				"Péssimas notícias. O plano do nosso humilde trabalhador não cobre cirurgias intergaláticas em distâncias maiores que 10 parsecs",
				"e está limitado ao transplante de apenas 1 órgão artificial. Ele precisaria de 2, pelo menos.",
				"Além disso, os órgãos estão em falta devido à um esquema de corrupção onde se está desviando uma grande quantidade de b1tM0edas,",
				"e ultimamente só pessoas ricas que conseguem. E esse, infelizmente não é o caso do nosso honesto engenheiro encanador.",
				"",
				"A esperança agora é que o diagnóstico seja o vírus. Ao chegar o resultado foi constado que a bebida tinha atacado o fígado, rins,",
				"coração e outros órgãos.",
				"",
				"Em poucas horas seu corpo se definha e não resiste. Nosso amigo vira mais uma estrelinha no universo. RIP my friend."
		};
		
		String[] respostaTratamentoPrecoce = {
				"Mario é contra ao tratamento precoce, cujo remédio tem como garoto propaganda o Lorde Jezziahs. Para Mario, é a mesma coisa que beber",
				"água artificial ou um suco de frutas do planeta de seu amigo Jabá da pizzaria The Hut. Sem efeito nenhum. Em Thera alguns são curados sem fazer",
				"nada. A maioria tem sintomas leves. Mas, os dois Klingdons foram tratados com placeb0K1na e não tiveram sorte.",
				"",
				"Como não é um remédio adotado pelos membros da Federação é necessário esperar alguns dias para ser entregue pela Amazonas. Até lá o paciente",
				"será mantido na UTI e que para sorte do nosso amigo, pelo menos o plano médico cobre.",
				"",
				"Por sorte o problema era o vírus. O remédio o curou? Na verdade a encomenda do Amazonas nunca chegou. A nave robô foi interceptada pelos piratas",
				"espacias do planeta C@rybeean"
		};
		
		String[] respostaNaoFazNada = {
				"Resposta corajosa. Na verdade os médicos sabem que provavelmente nosso amigo não sobreviverá por muito tempo. Que o plano de saúde",
				"do paciente não é top das galáxias. Fora o fato que nenhum dos médicos é de Thera. Dizem alguns que o importante para eles é desocupar",
				"o leito para dar vaga a outros com mais chance e planos melhores. Bu$ine$$ é a palavra.",
				"",
				"Mas a UTI é aceita e paga bem. Então, por enquanto os médicos irão mantê-lo sob observação e com soro a base de DNA do paciente.",
				"",
				"Por 'sorte' o problema era o vírus. Muitos em Thera têm sintomas leves. E outros, como no caso de Mario, é mais sério. Mas a chance de",
				"recuperação com a medicina avançada de agora é de cerca de 70%. Isso os médicos não sabiam."		
		};
		
		String[] enredo = {
				"Durante a quarentena, Mario ficou muito doente. Os Klingdons têm costume de beber na garrafa mesmo e dividir com os outros.",
				"E agora nosso amigo tenta resistir bravamente na UTI onde se encontra sedado e inconsciente. Esse procedimento é totalmente normal",
				"e visa preservar todos os sinais vitais do paciente. Seu corpo é totalmente escaneado para identificar o problema.",
				"",
				"Os médicos estão em dúvida se o estado do paciente é por conta da bebida Abhysmum, que é altamente tóxicas ao humanos se consumida",
				"por um algum tempo e acabam com o fígado e outros órgãos ou se é por causa do vírus. Como a doença é recente, os médicos ainda não",
				"conhecem todos os sintomas que afetam o povo de Thera. E nenhum deles é de lá.",
				"",
				"As próximas horas serão cruciais para a sobrevivência do nosso amigo.",
				"",
				"Sabendo do histórico anterior dos Klingdons e antes do diagnóstico estar pronto e o plano de saúde autorizar os próximos",
				"procedimentos, os médicos dão as seguintes opções de tratamento:",
				"",
				"1 - Se for considerar a bebida, transplantar órgãos artificiais antes que comecem a falhar.",
				"2 - Se for o vírus, como Mario é de Thera, os médicos devem seguir o protocolo de lá e aconselham o tratamento precoce a base de placeb0K1na",
				"3 - Não faz nada no momento. Apenas monitaram o paciente, dão alguns medicamentos e esperam pela sua recuperação.",
				"",
				
		};
		
		caminho.add(strEspaco);
		caminho.add(strCapitulo + titulo);
		
		centralizarTexto(titulo, larguraTela);
		System.out.println("");
		
		imprimeCaracter(enredo, 5);
		
		System.out.println("");
		System.out.print("Que decisão você aconselharia aos médicos?");
		resposta = leitor.nextInt();
		System.out.println("");
		
		switch(resposta) {
		case 1:
			imprimeTexto(respostaOrgao, false);

			continuaSaga = false;
			statusAtual = StatusJogo.MORTO;
			
			caminho.add(strResposta + "Oh no! A bebida Abhysmum mata nosso amigo. O plano médico não cobria transplantes e cirurgias intergalática.");
			break;
			
		case 2:
			imprimeTexto(respostaTratamentoPrecoce, false);

			continuaSaga = true;
			
			caminho.add(strResposta + "Nosso amigo foi curado sem placeb0K1na. A nave robô da Amazonas foi sequestrada pelos piratas espaciais do C@rybeean!");
			break;
			
		default:
			imprimeTexto(respostaNaoFazNada, false);

			continuaSaga = true;
			
			caminho.add(strResposta + "Esse é guerreiro! Não contavam com a astúcia dele. Resistiu bravamente.");
			break;
		}
		

		pausaEntreTelas();

	}

	
	
	
	/*
	 * *****************************************************************
	 * 
	 * Métodos auxiliares
	 * 
	 * *****************************************************************
	 */
	private static void mostraAsciiStatus(StatusJogo status) {
		
		switch(status) {
		case VIVO :
			imprimeTexto(statusVivoAscii(), true);
			break;
			
		case MORTO:
			imprimeTexto(statusMortoAscii(), true);
			break;
			
		case ZUMBI:
			imprimeTexto(statusZumbiAscii(), true);
			break;
		
		case SENSIVEL:
			imprimeTexto(statusNervosoAscii(), true);
			break;
			
		case BLOQUEADO:
			imprimeTexto(statusBloqueadoAscii(), true);
			break;
			
		default:
			imprimeTexto(statusAlertAscii(), true);
		}
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
		System.out.println(nomeGamer + " sua saga na aventura # " + contaTentativas + " chegou a um final. Seu status no jogo foi: ");
		mostraAsciiStatus(statusAtual);
		
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
	}


	
	/*
	 ******************************************************************************************
	 *
	 *   Início dos ASCII arts
	 * 
	 * 
	 ******************************************************************************************
	 */
	
	private static String[] statusVivoAscii() {
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
	
	
	private static String[] statusZumbiAscii() {
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
	
	
	private static String[] statusNervosoAscii() {
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
	
	private static String[] statusMortoAscii() {
		String[] statusMorto = {
				"",
				"O triste fim do nosso engenheiro encanador quântico",
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
	
	private static String[] statusBloqueadoAscii() {
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
	
	
	private static String[]	statusAlertAscii() {
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
	
	private static String[] thera() {
		String[] thera = {
				"Thera e suas 4 luas",
				"",
				"                .            (*)  .-'.  ':'-.      .         ",
				"       .                        .''::: .:    '.           (*)",
				"                :              /   :::::'      \\   .        ",
				"                   (*)   ;    ;.    ':' `       ;            ",
				"     .                        |       '..       |      :     ",
				"          .                   ; '      ::::.    ;            ",
				"     ,     .  .        .       \\       '::::   /         .  ",
				"                                '.      :::  .'              ",
				"jgs                                '-.___'_.-'      (*)      ",
				"         :          .       .                    .        .  "
		};
		
		return thera;
		
	}
	
	private static String[] gameOver() {
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
	
	private static String[] aberturaJogo() {
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
