package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Execucao.Jogo;
import jogador.Jogador;
import tabuleiro.Tabuleiro;

public class Main {
	
	public static void main(String[] args) throws IOException {
		String arquivoTabuleiro = "tabuleiro.txt";
		
		Tabuleiro t = new Tabuleiro();
		t.leTabuleiro(arquivoTabuleiro);
		
		Jogo j = new Jogo();
		
		j.leTabuleiro("tabuleiro.txt");
		j.startGame("jogadas.txt");
		
	}//main()

}
