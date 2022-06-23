package dictionary.entity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class EntityProducer {



    public static Entity getEntity(String word) {
        String[] content = getTranslateAndComment(word);

        return new Entity(word,content[0],content[1]);
    }

    private static List<String> collectList(String lines){
        return lines.lines().filter(s -> s.startsWith(" -")).collect(Collectors.toList());
    }

    private static String listToString(List<String> list){
        StringBuilder sb = new StringBuilder();
        list.forEach(s -> {
            sb.append(s.replaceAll("-","").replaceAll(";",",").trim()).append(",");
        });
        return  sb.toString();
    }

    private static String cleanFromTags(String string){
        return  string.replaceAll("<[^><]*>","");
    }
    private static String[] getTranslateAndComment(String word){
        String[] context = new String[2];
        Document doc = null;   //представляет собой html код страницы
        try {
            doc = Jsoup.connect("https://wooordhunt.ru/word/"+word).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Elements element1 = doc.select("#content_in_russian > div.tr");  //вычленяет елемент с селектором указанным в кавчестве параметра
        Elements element2 = doc.select("#word_forms");
        if(element1.isEmpty()) {
            context[0] ="TRANSLATION NOT FOUND";
        } else {
            context[0] = listToString(collectList(cleanFromTags(element1.toString())));
        }

        if (element2.isEmpty()){
            context[1] = "NO COMMENT";
        } else {
            context[1] =element2.toString().replaceAll("<[^><]*>","").replaceAll("\n"," ").strip();
        }

        return context;
    }
}
