package sean.oEmbed.controller;


import sean.oEmbed.service.OembedServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


@Controller
public class OembedController {
    private final OembedServiceImpl oembedserviceimpl;

    @Autowired
    public OembedController(OembedServiceImpl oembedserviceimpl) {
        this.oembedserviceimpl = oembedserviceimpl;
    }

    @GetMapping("/")
    public String controller() {
        return "home";
    }

    @GetMapping("/search")
    @ResponseBody
    public String search(@RequestParam("url") String url) throws IOException {
        return oembedserviceimpl.urlConnector(url);
    }

    @GetMapping("/oembedResponse")
    @ResponseBody
    public String oembedResponse(@RequestParam("userUrlData") String userUrlData) throws Exception {
        return oembedserviceimpl.urlConnector(userUrlData);
    }
}
