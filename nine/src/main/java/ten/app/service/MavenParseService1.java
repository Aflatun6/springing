package ten.app.service;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ten.app.entity.Artifact;
import ten.app.entity.Tag;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Log4j2
@Service
public class MavenParseService1 {

    private final RestTemplate restTemplate;
    private final String siteAddress = "https://mvnrepository.com";

    public MavenParseService1(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private HttpHeaders pretend_browser() {
        return new HttpHeaders() {{
            add("user-agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        }};
    }

    private String rootPageFromWeb(String url) {
        HttpEntity<Object> rqEntity = new HttpEntity<>(pretend_browser());
        return restTemplate.exchange(url, HttpMethod.GET, rqEntity, String.class).getBody();
    }

    @SneakyThrows
    private String rootPageFromHere(String url) {
        String fname = "response.html";
        String file = this.getClass().getClassLoader().getResource(fname).getFile();
        String content = String.join("\n", Files.readAllLines(Paths.get(file)));
        log.info(content);
        throw new RuntimeException("will be implemented");
    }

    public Collection<Artifact> artifacts() {
        String mavenRootPage = rootPageFromWeb(siteAddress);
        Document doc = Jsoup.parse(mavenRootPage);
        Elements posts = doc.select("body #page #maincontent .posts .im");
        return posts.stream()
                .map((Element e) -> {
                    Elements els = e.select(".im > a");
                    String href = els.attr("href");
                    String url = els.select("picture > img").attr("src");
                    return new Artifact(href, url);
                })
                .collect(Collectors.toList());
    }

    public Collection<Tag> tags() {
        String mavenRootPage = rootPageFromWeb(siteAddress);
        Document doc = Jsoup.parse(mavenRootPage);
        String[] elements = doc.select("body #right > div > script").html().split("\n");
        List<String> tags = Arrays.stream(elements)
                .filter(s -> s.startsWith("var tags"))
                .findFirst()
                .map(this::betweenBrackets)
                .map(s -> s.split(","))
                .map(Arrays::stream)
                .orElseThrow(RuntimeException::new)
                .map(this::betweenQuotes)
                .collect(Collectors.toList());

        List<Integer> scores = Arrays.stream(elements)
                .filter(s -> s.startsWith("var sizes"))
                .findFirst()
                .map(this::betweenBrackets)
                .map(s -> s.split(","))
                .map(Arrays::stream)
                .orElseThrow(RuntimeException::new)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return IntStream.range(0, Math.min(tags.size(), scores.size())).mapToObj(i ->
                new Tag(tags.get(i), scores.get(i)))
                .sorted((t1, t2) -> t1.getScore() - t2.getScore())
                .collect(Collectors.toList());

    }

    private String betweenQuotes(String s) {
        return s.substring(s.indexOf("'") + 1, s.lastIndexOf("'"));
    }

    private String betweenBrackets(String s) {
        return s.substring(s.indexOf("[") + 1, s.indexOf("]"));
    }
}
