package br.com.cognito.estatisticas;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ContadorDePalavras {

	private final Map<String, Integer> contagemDePalavras;

	public ContadorDePalavras() {
		this.contagemDePalavras = new TreeMap<>();
	}

	public void adicionarPalavra(String palavra) {
		Integer contagemDaPalavra = contagemDePalavras.get(palavra);
		if (contagemDaPalavra != null) {
			contagemDaPalavra++;
		} else {
			contagemDaPalavra = 1;
		}
		contagemDePalavras.put(palavra, contagemDaPalavra);
	}

	public Integer getContagemDaPalavra(String palavra) {
		return contagemDePalavras.getOrDefault(palavra, 0);
	}

	public Set<Map.Entry<String, Integer>> entrySet() {
		return contagemDePalavras.entrySet();
	}

}