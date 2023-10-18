package sean.oEmbed.service;


import sean.oEmbed.handler.DataHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class OembedServiceImpl implements OembedService {

    private final DataHandlerImpl dataHandler;

    @Autowired
    public OembedServiceImpl(DataHandlerImpl dataHandler) {
        this.dataHandler = dataHandler;
    }

    @Override
    public String urlConnector(String url) throws IOException {
        String oembedEndpoint = createAddr(url);
        if(oembedEndpoint.contains("error")) return oembedEndpoint;
        else return dataHandler.getData(oembedEndpoint);
    }

    /**
     * oembedEndpoint를 반환
     */
    private String createAddr(String url) {
        String youtubeA = "youtube.com";
        String youtubeB = "youtu.be";
        String vimeo = "vimeo.com";
        String twitter = "twitter.com";
        String twitterX = "x.com";
        String instagram = "instagram.com";

        String encodeUrl = URLEncoder.encode(url, StandardCharsets.UTF_8);
        String oembedPoint;

        if (url.contains(youtubeA) || url.contains(youtubeB)) oembedPoint = "https://www.youtube.com/oembed?format=json&url=";
        else if (url.contains(vimeo)) oembedPoint = "https://vimeo.com/api/oembed.json?url=";
        else if (url.contains(twitter)) oembedPoint = "https://publish.twitter.com/oembed?url=";
        else if (url.contains(instagram)) return "{\"error\" : \"죄송합니다. Instagram은 2021년 4월 이후로 oEmbed 엔드포인트를 비공개로 전환했기 때문에 Instagram의 oEmbed 기능을 사용할 수 없습니다.\"}";
        else if (url.contains(twitterX)) return "{\"error\" : \"죄송합니다. x.com은 twitter.com으로 수정해서 검색해주세요.\"}";
        else return "{\"error\" : \"죄송합니다. 아직 준비가 안된 서비스 url 입니다. 유튜브, vimeo, twitter 가능합니다.\"}";
        return oembedPoint + encodeUrl;
    }
}
