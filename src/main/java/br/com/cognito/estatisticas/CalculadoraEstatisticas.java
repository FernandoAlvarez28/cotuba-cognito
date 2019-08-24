package br.com.cognito.estatisticas;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.PosGeracaoEbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CalculadoraEstatisticas implements PosGeracaoEbook {

	@Override
	public void executarAposGeracao(Ebook ebook) {
		for (Capitulo capitulo : ebook.getCapitulos()) {
			final String html = capitulo.getConteudoHtml();

			final Document document = Jsoup.parse(html);

			final String textoDoCapitulo = document.body().text();

			final String[] palavras = textoDoCapitulo.split("\\s+");

			for (String palavra : palavras) {
				System.out.println(palavra);
			}
		}
	}

}