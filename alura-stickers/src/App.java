import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

//        fazer uma conexão HTTP e buscar os top 250 filmes
//
//        String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
//        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
//        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
//
//        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);
            String conteudoModificado = conteudo
                    .getTitulo()
                    .replaceAll(" ", "-");
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String path = "/home/raphael/Spring-Boot/imersao-java/alura-stickers/saida/";
            String nomeArquivo = path + conteudoModificado + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(nomeArquivo);
            System.out.println();
        }
    }
}

