package br.com.cognito.estatisticas;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.posgeracao.PosGeracaoEbook;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;

public class CalculadoraEstatisticas implements PosGeracaoEbook {

	@Override
	public void executarAposGeracao(Ebook ebook) {

		ContadorDePalavras contadorDePalavras = new ContadorDePalavras();

		for (Capitulo capitulo : ebook.getCapitulos()) {
			final String html = capitulo.getConteudoHtml();

			final Document document = Jsoup.parse(html);

			String textoDoCapitulo = document.body().text();
			textoDoCapitulo = textoDoCapitulo.replaceAll("\\p{Punct}", StringUtils.EMPTY);
			textoDoCapitulo = Normalizer.normalize(textoDoCapitulo, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", StringUtils.EMPTY);

			final String[] palavras = textoDoCapitulo.split("\\s+");

			for (String palavra : palavras) {
				contadorDePalavras.adicionarPalavra(palavra);
			}
		}

		for (ContadorDePalavras.Contagem contagem : contadorDePalavras) {
			System.out.println(StringUtils.join(contagem.getPalavra(), ": ", contagem.getQuantidade()));
		}
	}

}