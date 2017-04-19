package tabuleiro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Tabuleiro {
	private ArrayList<Imovel> imoveis;
	private int start;
	
	//Construtor
	public Tabuleiro(){
		imoveis = new ArrayList<Imovel>();
	}//Construtor()
		
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
	public int getSize()
	{
		return this.imoveis.size();
	}
	
	public Imovel getPosicao(int posicao)
	{
		return this.imoveis.get(posicao-1);
	}
	
	public void adicionaImovel(Imovel imovel){
		this.imoveis.add(imovel);
	}//adicionaImovel()
	
	public void leTabuleiro(String arquivoTabuleiro)
	{
		String [] token;	//quebra as informações lidas do arquivo
		Imovel imovel;
		//Tenta ler o arquivo
		try {
			FileReader fTabuleiro = new FileReader(arquivoTabuleiro);
			BufferedReader lerArqTabuleiro = new BufferedReader(fTabuleiro);

			String linha = lerArqTabuleiro.readLine();
			linha = lerArqTabuleiro.readLine();

			while (linha != null) {
				token = linha.split(";");
				imovel = new Imovel();
				imovel.setPosicao(Integer.parseInt(token[1]));
				imovel.setTipoPosicao(Integer.parseInt(token[2]));
				if(imovel.getTipoPosicao()==1 || imovel.getTipoPosicao()==2)
				{
					if (imovel.getTipoPosicao()==1) this.setStart(imovel.getPosicao());
				}else
				{
					imovel.setTipoImovel(Integer.parseInt(token[3]));
					imovel.setValorImovel(Integer.parseInt(token[4]));
					imovel.setTaxaAluguel(Integer.parseInt(token[5]));
				}//else
				this.imoveis.add(imovel);
				linha = lerArqTabuleiro.readLine();
			}//while

			fTabuleiro.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
					e.getMessage());
		}//catch
	}

	public void printTabuleiro()
	{
		for(int i=0; i<this.imoveis.size(); i++)
		{
			System.out.println(this.imoveis.get(i).toString());
		}
	}
	
	public void sort()
	{
		Collections.sort(this.imoveis, Collections.reverseOrder());
	}
	
}//Tabuleiro
