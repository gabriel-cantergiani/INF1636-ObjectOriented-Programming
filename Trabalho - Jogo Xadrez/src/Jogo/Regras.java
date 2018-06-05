package Jogo;

import Peça.Peça;
import Peça.tipoPeça;

public class Regras {
	
	private Tabuleiro tab;
	private int[][] casas;
	private Peça[][] posicoes;
	private int selecao = 0; // selecao = 0 -> nada selecionado,  selecao = 1 -> peça selecionada
	private Peça peça;
	private int iOrigem, jOrigem;	// indices de origem da peça a ser movimentada
	private int eMovRei = 0;   // indica se esta verificando a movimentacao do rei   (0 -> não, 1-> sim)
	private int vez = 0;  // 0 -> brancos, 1-> pretos  (brancos começam)
	
	// auxiliares para realizar os roques
	// 0 -> não foi movimentado nenhuma vez, 1 -> ja foi movimentado
	
	private int movReiBranco = 0;
	private int movReiPreto = 0;
	private int movTorreBrancaDir = 0;
	private int movTorreBrancaEsq = 0;
	private int movTorrePretaDir = 0;
	private int movTorrePretaEsq = 0;
	
	
	public Regras(int[][] c, Peça[][] p, Tabuleiro t) {
		casas = c;
		posicoes = p;
		tab = t;
	}
	
	protected void Peça_Selecionada(int i, int j) {
		
		if (posicoes[i][j].getCor() != vez && selecao == 0) return;
		
		if(selecao == 0 || peça.getCor() == posicoes[i][j].getCor()) { // primeira seleção ou outra peça da mesma cor foi selecionada -> Reinicia a jogada com a nova peça
			tab.zeraCasas();
			iOrigem = i;
			jOrigem = j;
			peça = posicoes[i][j];
			selecao = 1;
			novaMovimentacao(i,j);
		}
		else {								// Peça da outra cor foi selecionada. Verifica se a captura é viável...
			if(casas[i][j] == 1) {
				posicoes[iOrigem][jOrigem] = null;
				posicoes[i][j] = peça;
				
				if (peça.getTipo() == tipoPeça.Torre || peça.getTipo() == tipoPeça.Rei) 
					verificaMovReiTorre();
				
				if (vez == 0) vez = 1;
				else if (vez == 1) vez = 0;
			}
			tab.zeraCasas();
			selecao = 0;
		}
	}

	protected void Casa_Selecionada(int i, int j) {
		
		if(selecao == 0)
			return;
		
		if(casas[i][j] == 1) {	// casa é válida -> efetua a movimentação
			posicoes[iOrigem][jOrigem] = null;
			posicoes[i][j] = peça;
			
			if (peça.getTipo() == tipoPeça.Torre || peça.getTipo() == tipoPeça.Rei) 
				verificaMovReiTorre();
			
			if (vez == 0) vez = 1;
			else if (vez == 1) vez = 0;
		}
		tab.zeraCasas();
		selecao = 0;
	}
	
	private void novaMovimentacao (int i, int j) {
		
		if (eMovRei == 1) {
			if (posicoes[i][j].getTipo() == tipoPeça.Peao) movimentaPeaoCaptura(i,j);
		}
		
		else {
			if (posicoes[i][j].getTipo() == tipoPeça.Peao) movimentaPeao(i,j);
			if (posicoes[i][j].getTipo() == tipoPeça.Rei) movimentaRei(i,j);
		}
		
		if (posicoes[i][j].getTipo() == tipoPeça.Torre) movimentaTorre(i,j);
		if (posicoes[i][j].getTipo() == tipoPeça.Bispo) movimentaBispo(i,j);
		if (posicoes[i][j].getTipo() == tipoPeça.Cavalo) movimentaCavalo(i,j);
		if (posicoes[i][j].getTipo() == tipoPeça.Rainha) movimentaRainha(i,j);
		
	
	}
	
	private void verificaMovReiTorre () {
		if (posicoes[7][7].getTipo() != tipoPeça.Torre || posicoes[7][7].getCor() != 0) movTorreBrancaDir = 1;
		if (posicoes[7][0].getTipo() != tipoPeça.Torre || posicoes[7][0].getCor() != 0) movTorreBrancaEsq = 1;
		if (posicoes[0][7].getTipo() != tipoPeça.Torre || posicoes[0][7].getCor() != 1) movTorrePretaDir = 1;
		if (posicoes[0][0].getTipo() != tipoPeça.Torre || posicoes[0][0].getCor() != 1) movTorrePretaEsq = 1;
		if (posicoes[7][4].getTipo() != tipoPeça.Rei || posicoes[7][4].getCor() != 0) movReiBranco = 1;
		if (posicoes[0][4].getTipo() != tipoPeça.Rei || posicoes[0][4].getCor() != 1) movReiPreto = 1;
	}

	private void movimentaTorre (int i, int j){
		
		int auxi, auxj;
		
		for (auxi = i+1; auxi < 8; auxi++) {
			if (posicoes[auxi][j]==null) {
				casas[auxi][j] = 1;   // é um movimento possível
				continue;
			}
			if (posicoes[i][j].getCor() != posicoes[auxi][j].getCor()) {
				casas[auxi][j] = 1;   // é um movimento possível
				break;
			}
			else {
				if (eMovRei == 1) casas[auxi][j] = 1;
				break;
			}
		}
		
		for (auxi = i-1; auxi >= 0; auxi--) {
			if (posicoes[auxi][j]==null) {
				casas[auxi][j] = 1;
				continue;
			}
			if (posicoes[i][j].getCor() != posicoes[auxi][j].getCor()) {
				casas[auxi][j] = 1;   // é um movimento possível
				break;
			}
			else {
				if (eMovRei == 1) casas[auxi][j] = 1;
				break;  // não é um movimento possível
			}
		}
		
		for (auxj = j+1; auxj < 8; auxj++) {
			if(posicoes[i][auxj]==null) {
				casas[i][auxj] = 1;
				continue;
			}
			if (posicoes[i][j].getCor() != posicoes[i][auxj].getCor()) {
				casas[i][auxj] = 1;  // é um movimento possível
				break;
			}
			else {
				if (eMovRei == 1) casas[i][auxj] = 1;
				break;  // não é um movimento possível
			}
		}
		
		for (auxj = j-1; auxj >= 0; auxj--) {
			if(posicoes[i][auxj]==null) {
				casas[i][auxj] = 1;
				continue;
			}
			if (posicoes[i][j].getCor() != posicoes[i][auxj].getCor()) {
				casas[i][auxj] = 1;  // é um movimento possível
				break;
			}
			else {
				if (eMovRei == 1) casas[i][auxj] = 1;
				break;  // não é um movimento possível
			}
		}
	}
	
	private void movimentaBispo (int i, int j) {
		
		int auxi, auxj;
		
		for (auxi = i+1, auxj = j+1; auxi < 8 && auxj < 8; auxi++, auxj++) {
			if (posicoes[auxi][auxj] == null) {
				casas[auxi][auxj] = 1;  // é um movimento possível
				continue;
			}
			if (posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
				casas[auxi][auxj] = 1;  // é um movimento possível
				break;
			}
			else {
				if (eMovRei == 1) casas[auxi][auxj] = 1;
				break;  // não é um movimento possível
			}
		}
		
		for (auxi = i-1, auxj = j-1; auxi >= 0 && auxj >= 0; auxi--, auxj--) {
			if (posicoes[auxi][auxj] == null) {
				casas[auxi][auxj] = 1;  // é um movimento possível
				continue;
			}
			if (posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
				casas[auxi][auxj] = 1;  // é um movimento possível
				break;
			}
			else {
				if (eMovRei == 1) casas[auxi][auxj] = 1;
				break;  // não é um movimento possível
			}
		}
		
		for (auxi = i-1, auxj = j+1; auxi >= 0 && auxj < 8; auxi--, auxj++) {
			if (posicoes[auxi][auxj] == null) {
				casas[auxi][auxj] = 1;  // é um movimento possível
				continue;
			}
			if (posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
				casas[auxi][auxj] = 1;  // é um movimento possível
				break;
			}
			else {
				if (eMovRei == 1) casas[auxi][auxj] = 1;
				break;  // não é um movimento possível
			}
		}
		
		for (auxi = i+1, auxj = j-1; auxi < 8 && auxj >= 0; auxi++, auxj--) {
			if (posicoes[auxi][auxj] == null) {
				casas[auxi][auxj] = 1;  // é um movimento possível
				continue;
			}
			if (posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
				casas[auxi][auxj] = 1;  // é um movimento possível
				break;
			}
			else {
				if (eMovRei == 1) casas[auxi][auxj] = 1;
				break;  // não é um movimento possível
			}
		}
	}
	
	private void movimentaCavalo (int i, int j) {
		
		int auxi, auxj;
		
		if ((auxi = i - 1) >= 0) {
			if ((auxj = j - 2) >= 0) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
				if (eMovRei == 1) casas[auxi][auxj] = 1;
			}
			if ((auxj = j + 2) < 8) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
				if (eMovRei == 1) casas[auxi][auxj] = 1;
			}
		}
		
		if ((auxi = i - 2) >= 0) {
			if ((auxj = j - 1) >= 0) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
				if (eMovRei == 1) casas[auxi][auxj] = 1;
			}
			if ((auxj = j + 1) < 8) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
				if (eMovRei == 1) casas[auxi][auxj] = 1;
			}
		}
		
		if ((auxi = i + 1) < 8) {
			if ((auxj = j - 2) >= 0) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
				if (eMovRei == 1) casas[auxi][auxj] = 1;
			}
			if ((auxj = j + 2) < 8) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
				if (eMovRei == 1) casas[auxi][auxj] = 1;
			}
		}
		
		if ((auxi = i + 2) < 8) {
			if ((auxj = j - 1) >= 0) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
				if (eMovRei == 1) casas[auxi][auxj] = 1;
			}
			if ((auxj = j + 1) < 8) {
				if (posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					casas[auxi][auxj] = 1;  // é um movimento possível
				}
				if (eMovRei == 1) casas[auxi][auxj] = 1;
			}
		}
		
	}

	private void movimentaRainha (int i, int j) {
		movimentaTorre(i,j);
		movimentaBispo(i,j);
	}
	
	private void movimentaPeao (int i, int j) {
		
		if (i == 6 && posicoes[i][j].getCor() == 0 && posicoes[i-2][j] == null && posicoes[i-1][j] == null) casas[i-2][j] = 1;
		if (i == 1 && posicoes[i][j].getCor() == 1 && posicoes[i+2][j] == null && posicoes[i+1][j] == null) casas[i+2][j] = 1;
		if (posicoes[i][j].getCor() == 0 && posicoes[i-1][j] == null && (i - 1) >= 0) casas[i-1][j] = 1;
		if (posicoes[i][j].getCor() == 1 && posicoes[i+1][j] == null && (i + 1) < 8) casas[i+1][j] = 1;
		if (posicoes[i][j].getCor() == 0 && j>0 && posicoes[i-1][j-1]!=null && posicoes[i-1][j-1].getCor() == 1) casas[i-1][j-1] = 1;
		if (posicoes[i][j].getCor() == 0 && j<7 && posicoes[i-1][j+1]!=null && posicoes[i-1][j+1].getCor() == 1) casas[i-1][j+1] = 1;
		if (posicoes[i][j].getCor() == 1 && j>0 && posicoes[i+1][j-1]!=null && posicoes[i+1][j-1].getCor() == 0) casas[i+1][j-1] = 1;
		if (posicoes[i][j].getCor() == 1 && j<7 && posicoes[i+1][j+1]!=null && posicoes[i+1][j+1].getCor() == 0) casas[i+1][j+1] = 1;
		
	}
	
	private void movimentaPeaoCaptura (int i, int j) {
		if (posicoes[i][j].getCor() == 0 && j>0) casas[i-1][j-1] = 1;
		if (posicoes[i][j].getCor() == 0 && j<7) casas[i-1][j+1] = 1;
		if (posicoes[i][j].getCor() == 1 && j>0) casas[i+1][j-1] = 1;
		if (posicoes[i][j].getCor() == 1 && j<7) casas[i+1][j+1] = 1;
	}
	
	private void movimentaRei (int i, int j) {
		
		int auxi, auxj;
		int[][] auxRei = new int[8][8];
		
		eMovRei = 1;
		
		for (auxi = 0; auxi < 8; auxi++) {
			for (auxj = 0; auxj < 8; auxj++) {
				if (posicoes[auxi][auxj] != null && posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) {
					novaMovimentacao(auxi,auxj);
				}
			}
		}
		
		eMovRei = 0;
		
		for (auxi = 0; auxi < 8; auxi++) {
			for (auxj = 0; auxj < 8; auxj++) {
				if (casas[auxi][auxj] == 1) {
					auxRei[auxi][auxj] = 1;
				}
			}
		}
		
		tab.zeraCasas();
		
		if ((auxi = i + 1) < 8) {
			if ((posicoes[auxi][j] == null || posicoes[i][j].getCor() != posicoes[auxi][j].getCor()) && auxRei[auxi][j] != 1) {
				casas[auxi][j] = 1;
			}
			if ((auxj = j + 1) < 8) {
				if ((posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) && auxRei[auxi][auxj] != 1) {
					casas[auxi][auxj] = 1;
				}
			}
			if ((auxj = j - 1) >= 0) {
				if ((posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) && auxRei[auxi][auxj] != 1) {
					casas[auxi][auxj] = 1;
				}
			}
		}
		
		if ((auxi = i - 1) >= 0) {
			if ((posicoes[auxi][j] == null || posicoes[i][j].getCor() != posicoes[auxi][j].getCor()) && auxRei[auxi][j] != 1) {
				casas[auxi][j] = 1;
			}
			if ((auxj = j + 1) < 8) {
				if ((posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) && auxRei[auxi][auxj] != 1) {
					casas[auxi][auxj] = 1;
				}
			}
			if ((auxj = j - 1) >= 0) {
				if ((posicoes[auxi][auxj] == null || posicoes[i][j].getCor() != posicoes[auxi][auxj].getCor()) && auxRei[auxi][auxj] != 1) {
					casas[auxi][auxj] = 1;
				}
			}
		}
		
		if ((auxj = j - 1) >= 0) {
			if ((posicoes[i][auxj] == null || posicoes[i][j].getCor() != posicoes[i][auxj].getCor()) && auxRei[i][auxj] != 1 ) {
				casas[i][auxj] = 1;
			}
		}
		
		if ((auxj = j + 1) < 8) {
			if ((posicoes[i][auxj] == null || posicoes[i][j].getCor() != posicoes[i][auxj].getCor()) && auxRei[i][auxj] != 1) {
				casas[i][auxj] = 1;
			}
		}
	}
	
}