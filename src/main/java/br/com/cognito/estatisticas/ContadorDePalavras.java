package br.com.cognito.estatisticas;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ContadorDePalavras implements Iterable<ContadorDePalavras.Contagem> {

	private final Map<String, Integer> contagemDePalavras;

	public ContadorDePalavras() {
		this.contagemDePalavras = new TreeMap<>();
	}

	public static final class Contagem {

		private final String palavra;
		private final int quantidade;

		private Contagem(String palavra, int quantidade) {
			this.palavra = palavra;
			this.quantidade = quantidade;
		}

		public String getPalavra() {
			return palavra;
		}

		public int getQuantidade() {
			return quantidade;
		}

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

	@Override
	public Iterator<Contagem> iterator() {
		final Iterator<Map.Entry<String, Integer>> iterator = this.contagemDePalavras.entrySet().iterator();

		return new Iterator<Contagem>() {

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public Contagem next() {
				final Map.Entry<String, Integer> entry = iterator.next();
				return new Contagem(entry.getKey(), entry.getValue());
			}

		};
	}

}