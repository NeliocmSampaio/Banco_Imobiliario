package jogador;

import java.util.ArrayList;
import java.util.Collections;

import tabuleiro.Imovel;

public class Jogador implements Comparable<Jogador>{
	private Integer id;
	private int saldo;
	private int posicao;
	private ArrayList<Integer> imoveis;
	private int numImoveis;
	private int numVoltas;
	private int aluguelRecebido;
	private int AluguelPago;
	private int gastoImoveis;
	private int passaVez;
	
	//construtor
	public Jogador()
	{
		this.id = 0;
		this.saldo = 0;
		this.posicao = 0;
		this.imoveis = new ArrayList<Integer>();
		this.numImoveis = 0;
		this.numVoltas = 0;
		this.aluguelRecebido = 0;
		this.AluguelPago = 0;
		this.gastoImoveis = 0;
		this.passaVez = 0;
	}
	
	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public void somaPosicao(int deslocamento)
	{
		this.posicao += deslocamento;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	
	@Override
	public int compareTo( Jogador j )
	{
		return j.id.compareTo(this.id);
	}//compareTo
	
	@Override
	public String toString()
	{
		return "Jogador={Id: "+id+", saldo: "+saldo+", posicao: "+posicao+
				", imoveis: "+imoveis.toString()+"}\n";
	}
	
	public int fazPagamento(int valor)
	{
		if(valor >= saldo)
			return 0;
		else{
			this.setSaldo(this.getSaldo()-valor);
			this.gastoImoveis += valor;
			return 1;
		}//else
	}

	public int pagaAluguel(int valor)
	{
		if(valor >= saldo)
			return 0;
		else{
			this.setSaldo(this.getSaldo()-valor);
			this.AluguelPago+=valor;
			return 1;
		}//else
	}
	
	public void deposita(int valor)
	{
		this.setSaldo(this.getSaldo()+valor);
	}
	
	public void recebeAluguel(int valor)
	{
		this.setSaldo(this.getSaldo()+valor);
		this.aluguelRecebido+=valor;
	}
	
	public void insertImovel(int imovel)
	{
		this.imoveis.add(imovel);
		Collections.sort(this.imoveis);
		this.numImoveis++;
	}
	
	public void retiraImovel(int imovel)
	{
		this.imoveis.remove(imovel);
	}

	public void removeImoveis()
	{
		for(int i=0; i<this.imoveis.size(); i++)
		{
			this.imoveis.remove(i);
			this.numImoveis--;
		}
	}
	
	public ArrayList<Integer> getImoveis()
	{
		return this.imoveis;
	}
	
	public int getNumImoveis()
	{
		return this.numImoveis;
	}
	
	public void icrementaNumVoltas()
	{
		this.numVoltas++;
	}
	
	public int getNumVoltas()
	{
		return this.numVoltas;
	}
	
	public int getAluguelRecebido()
	{
		return this.aluguelRecebido;
	}
	
	public int getAluguelPago()
	{
		return this.AluguelPago;
	}
	
	public int getGastoImoveis()
	{
		return this.gastoImoveis;
	}
	
	public void incrementaPassaVez()
	{
		this.passaVez++;
	}
	
	public int getPassaVez()
	{
		return this.passaVez;
	}
	
}//Jogador
