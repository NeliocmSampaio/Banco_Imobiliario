package tabuleiro;

public class Imovel implements Comparable<Imovel>{
	private Integer posicao;
	private int tipoPosicao;
	private int tipoImovel;
	private int valorImovel;
	private int taxaAluguel;
	private int proprietario;
	
	//construtor
	public Imovel()
	{
		this.posicao = 0;
		this.tipoPosicao = 0;
		this.tipoImovel = 0;
		this.valorImovel = 0;
		this.taxaAluguel = 0;
		this.proprietario = 0;
	}
	
	public int getTipoImovel() {
		return tipoImovel;
	}
	
	public void setTipoImovel(int tipoImovel) {
		this.tipoImovel = tipoImovel;
	}
	
	public int getPosicao() {
		return posicao;
	}
	
	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
	
	public Integer getTipoPosicao() {
		return tipoPosicao;
	}
	
	public void setTipoPosicao(int tipoPosicao) {
		this.tipoPosicao = tipoPosicao;
	}
	
	public int getValorImovel() {
		return valorImovel;
	}
	
	public void setValorImovel(int valorImovel) {
		this.valorImovel = valorImovel;
	}
	
	public int getTaxaAluguel() {
		return taxaAluguel;
	}
	
	public void setTaxaAluguel(int taxaAluguel) {
		this.taxaAluguel = taxaAluguel;
	}
		
	@Override
	public int compareTo( Imovel imovel )
	{
		return imovel.posicao.compareTo(this.posicao);
	}
	
	public String toString()
	{
		return "Imovel{posicao: "+posicao+", tPosicao: "+tipoPosicao+
				", tImovel: "+tipoImovel+", vImovel: "+valorImovel+
				", tAluguel: "+taxaAluguel+", prop: "+proprietario+"}\n";
	}
	
	public int getProprietario() {
		return proprietario;
	}
	
	public void setProprietario(int proprietario) {
		this.proprietario = proprietario;
	}
	
	public int getAluguel()
	{
		return this.taxaAluguel*this.valorImovel/100;
	}
	
}
