package com.melhorCaminho.util;

import java.util.LinkedHashSet;

import com.melhorCaminho.model.Mapa;
import com.melhorCaminho.model.Ponto;
import com.melhorCaminho.model.Rota;


public class CalculaCaminho {
	private LinkedHashSet<Rota> melhorCaminho;
	private Double menorDist = Double.MAX_VALUE;
	
	
	
	public void rota(Mapa mapa, LinkedHashSet<Rota> caminho, Ponto atual, Ponto destino, Double total){
		if(atual.equals(destino)){
			System.out.println("-> 1");
			if(menorDist > total){
				melhorCaminho = new LinkedHashSet<Rota>();
				melhorCaminho.addAll(caminho);
				menorDist = total;
				return;
			}
		}else{
			System.out.println("-> 1");
			for(Rota e : mapa.getDivisas(atual)){
				LinkedHashSet<Rota> caminhoVar = new LinkedHashSet<Rota>();
				caminhoVar.addAll(caminho);
				caminhoVar.add(e);
				Double distAtual = total;
				distAtual += e.getDistancia();
				
				rota(mapa, caminhoVar, e.getDestino(), destino, distAtual);
			}
		}
	}



	public LinkedHashSet<Rota> getMelhorCaminho() {
		return melhorCaminho;
	}



	public void setMelhorCaminho(LinkedHashSet<Rota> melhorCaminho) {
		this.melhorCaminho = melhorCaminho;
	}



	public Double getMenorDist() {
		return menorDist;
	}



	public void setMenorDist(Double menorDist) {
		this.menorDist = menorDist;
	}

}
