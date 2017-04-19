package Execucao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import jogador.Jogador;
import tabuleiro.Imovel;
//import tabuleiro.Imovel;
import tabuleiro.Tabuleiro;

public class Jogo {
	private ArrayList<Jogador> jogadores;
	private Tabuleiro tabuleiro;
	private int valorInicial;
	
	private int numRodadas;
	
	//Construtor
	public Jogo()
	{
		jogadores = new ArrayList<Jogador>();
		tabuleiro = new Tabuleiro();
		this.numRodadas = 0;
	}//Jogo()
	
	//Construtor
	public Jogo(Tabuleiro t)
	{
		this.jogadores = new ArrayList<Jogador>();
		this.tabuleiro = t;
	}
	
	public void insereJogador(Jogador j)
	{
		this.jogadores.add(j);
	}

	public void sortJogadores()
	{
		Collections.sort(jogadores, Collections.reverseOrder());
	}

	public void printJogadores()
	{
		for(int i=0; i<this.jogadores.size(); i++)
		{
			System.out.println(this.jogadores.get(i).toString());
		}//for
	}//printJogadores()
	
	public void leTabuleiro(String arquivo)
	{
		this.tabuleiro.leTabuleiro(arquivo);
		this.tabuleiro.sort();
	}
	
	private int jogadoresAtivos(int desativados[])
	{
		int cont = 0;
		for(int i=0; i<desativados.length; i++)
		{
			if(desativados[i]==0)
				cont++;
		}
		return cont;
	}
	
	public void imprimeEstatisticas() throws IOException
	{
		FileWriter fEstatisticas = new FileWriter(new File("estatisticas.txt"));
	    PrintWriter escreverArqEstatisticas = new PrintWriter(fEstatisticas);
	    
	    //Numero de rodadas
	    escreverArqEstatisticas.printf("1:%d\n", this.numRodadas);
	    
	    //Numero de voltas de cada Jogador
	    escreverArqEstatisticas.printf("2:");
	    for(int i=0; i<this.jogadores.size(); i++)
	    {
	    	if(i>0)
	    	{
	    		escreverArqEstatisticas.printf(";");
	    	}
	    	escreverArqEstatisticas.printf("%d-%d;", this.jogadores.get(i).getId(), 
	    			this.jogadores.get(i).getNumVoltas());
	    }//for
	    escreverArqEstatisticas.printf("\n");
	    
	    //Saldo de cada jogador
	    escreverArqEstatisticas.printf("3:");
	    for(int i=0; i<this.jogadores.size(); i++)
	    {
	    	if(i>0)
	    	{
	    		escreverArqEstatisticas.printf(";");
	    	}
	    	escreverArqEstatisticas.printf("%d-%d", this.jogadores.get(i).getId(), 
	    			this.jogadores.get(i).getSaldo());
	    }//for
	    escreverArqEstatisticas.printf("\n");
	    
	    //Aluguel Recebido por cada jogador
	    escreverArqEstatisticas.printf("4:");
	    for(int i=0; i<this.jogadores.size(); i++)
	    {
	    	if(i>0)
	    	{
	    		escreverArqEstatisticas.printf(";");
	    	}
	    	escreverArqEstatisticas.printf("%d-%d", this.jogadores.get(i).getId(), 
	    			this.jogadores.get(i).getAluguelRecebido());
	    }//for
	    escreverArqEstatisticas.printf("\n");
	 
	    //Aluguel pago por cada jogador
	    escreverArqEstatisticas.printf("5:");
	    for(int i=0; i<this.jogadores.size(); i++)
	    {
	    	if(i>0)
	    	{
	    		escreverArqEstatisticas.printf(";");
	    	}
	    	escreverArqEstatisticas.printf("%d-%d", this.jogadores.get(i).getId(), 
	    			this.jogadores.get(i).getAluguelPago());
	    }//for
	    escreverArqEstatisticas.printf("\n");
	    
	    //Valor gasto na compra de imoveis
	    escreverArqEstatisticas.printf("6:");
	    for(int i=0; i<this.jogadores.size(); i++)
	    {
	    	if(i>0)
	    	{
	    		escreverArqEstatisticas.printf(";");
	    	}
	    	escreverArqEstatisticas.printf("%d-%d", this.jogadores.get(i).getId(), 
	    			this.jogadores.get(i).getGastoImoveis());
	    }//for
	    escreverArqEstatisticas.printf("\n");
	    
	    //Numero de passa a vez
	    escreverArqEstatisticas.printf("7:");
	    for(int i=0; i<this.jogadores.size(); i++)
	    {
	    	if(i>0)
	    	{
	    		escreverArqEstatisticas.printf(";");
	    	}
	    	escreverArqEstatisticas.printf("%d-%d", this.jogadores.get(i).getId(), 
	    			this.jogadores.get(i).getPassaVez());
	    }//for
	    escreverArqEstatisticas.printf("\n");
	    
	    fEstatisticas.close();
	}
	
	public void startGame(String arquivoJogadas) throws IOException
	{
		String [] token;	//quebra as informações lidas do arquivo
		Imovel imovel;
		Jogador j;
		int nJogadas, nJogadores, saldoInicial, jogador, idJogada, dado, auxRodadas=0;
		int posicao=0;
		int desativado[];
		int passaAVez[];
		Integer auxImoveis[];
		
		//Tenta ler o arquivo
		try {
			FileReader fJogadas = new FileReader(arquivoJogadas);
			BufferedReader lerArqJogadas = new BufferedReader(fJogadas);

			String linha = lerArqJogadas.readLine();
			token = linha.split("%");
			nJogadas = Integer.parseInt(token[0]);
			nJogadores = Integer.parseInt(token[1]);
			saldoInicial = Integer.parseInt(token[2]);
			this.valorInicial = saldoInicial;
			desativado = new int[nJogadores+1];
			passaAVez = new int[nJogadores+1];
			
			//Cria todos os jogadores
			for (int i=1; i<=nJogadores; i++)
			{
				j = new Jogador();
				j.setId(i);
				j.setPosicao(1);
				j.setSaldo(saldoInicial);
				this.jogadores.add(j);
				desativado[i] = 0;
				passaAVez[i] = 0;
			}//for
			
			//Loop principal da execução
			linha = lerArqJogadas.readLine();
			while (linha != null) {
				if(linha.equals("DUMP"))
					break;
				
				//Para calcular o numero de rodadas
				auxRodadas++;
				if(auxRodadas==nJogadores)
				{
					this.numRodadas++;
					auxRodadas=0;
				}//para calcular o numero de rodadas
				
				token = linha.split(";");
				idJogada = Integer.parseInt(token[0]);
				jogador = Integer.parseInt(token[1]);
				dado = Integer.parseInt(token[2]);
				
				if(desativado[jogador]!=1)
				{
					//Calcula a nova posicao do jogador
					posicao = (jogadores.get(jogador-1).getPosicao() + dado);
					//Faz deposito de 500 qnd o jogador passa pelo inicio
					if(posicao>this.tabuleiro.getSize())
					{
						this.jogadores.get(jogador-1).deposita(500);
						this.jogadores.get(jogador-1).icrementaNumVoltas();
					}
					posicao = posicao % this.tabuleiro.getSize();
					jogadores.get(jogador-1).setPosicao(posicao);	//atualiza a posicao
																	//do jogador
					
					//Tipo da posicao
					switch(this.tabuleiro.getPosicao(posicao).getTipoPosicao())
					{
					case(1):
						//System.out.println("Start");
						break;
					case(2):
						//System.out.println("Passe a vez");
						this.jogadores.get(jogador-1).incrementaPassaVez();
						break;
					case(3):
						//System.out.println("Imovel");
					
						//compra
						if(this.tabuleiro.getPosicao(posicao).getProprietario() == 0)
						{
							if(this.jogadores.get(jogador-1).fazPagamento(
									 this.tabuleiro.getPosicao(posicao).getValorImovel()) == 0 )
							{
								//retira jogador
								desativado[jogador] = 1;
								auxImoveis = new Integer[this.tabuleiro.getSize()];
								this.jogadores.get(jogador-1).getImoveis().toArray(auxImoveis);
								
								for(int i=0; i<auxImoveis.length; i++)
								{
									this.tabuleiro.getPosicao(auxImoveis[i]).setProprietario(0);
								}
								this.jogadores.get(jogador-1).removeImoveis();
							}else
							{
								this.tabuleiro.getPosicao(posicao).setProprietario(jogador);
								this.jogadores.get(jogador-1).insertImovel(posicao);
							}
						}else if(this.tabuleiro.getPosicao(posicao).getProprietario() != jogador)
						{
							//cobra
							if(this.jogadores.get(jogador-1).pagaAluguel(
									 this.tabuleiro.getPosicao(posicao).getAluguel()) == 0 )
							{
								//retira jogador
								desativado[jogador] = 1;
								auxImoveis = new Integer[this.tabuleiro.getSize()];
								this.jogadores.get(jogador-1).getImoveis().toArray(auxImoveis);
								
								for(int i=0; i<this.jogadores.get(jogador-1).getNumImoveis(); i++)
								{
									this.tabuleiro.getPosicao(auxImoveis[i]).setProprietario(0);
								}
								this.jogadores.get(jogador-1).removeImoveis();
							}else //paga
							{
								this.jogadores.get(this.tabuleiro.getPosicao(posicao).
										getProprietario()-1).recebeAluguel(this.tabuleiro.
												getPosicao(posicao).getAluguel());
							}//else
						}//if
						break;
					default:
						break;
					}//switch
					
				}//if
				
				if(this.jogadoresAtivos(desativado)==1)
				{
					break;
				}
				
				linha = lerArqJogadas.readLine();
			}//while

			fJogadas.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
					e.getMessage());
		}//catch
		this.imprimeEstatisticas();
	}//startGame()
}
