package sk.upjs.ics.GameBase.Other;

import sk.upjs.ics.GameBase.Entity.Game;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class YouTubeVideo extends JPanel {

    JWebBrowser webBrowser;

    public YouTubeVideo() {

        setLayout(new BorderLayout());
        webBrowser = new JWebBrowser();
        add(webBrowser, BorderLayout.CENTER);
        webBrowser.setBarsVisible(false);

    }

    public void changeVideo(Game game) {
        try {
            webBrowser.navigate(getVideoUrl(getVideoCode(getUrlSource(game.getTitle()))));
        } catch (IOException ex) {
            Logger.getLogger(YouTubeVideo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Na zaklade nazvu hry vytvori link ako keby bola hra vyhladavana na YouTube, a vrati zdrojovy kod tej stranky
    private String getUrlSource(String gameTitle) throws IOException {
        Scanner s = new Scanner(gameTitle);
        s.useDelimiter(" ");
        StringBuilder urlBuilder = new StringBuilder("https://www.youtube.com/results?search_query=");
        while (s.hasNext()) {
            urlBuilder.append(s.next());
            urlBuilder.append("+");
        }
        urlBuilder.append("Trailer");

        URL youtubepage = new URL(urlBuilder.toString());
        URLConnection yc = youtubepage.openConnection();
        BufferedReader in;
        in = new BufferedReader(new InputStreamReader(
                yc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            a.append(inputLine);
        }
        in.close();

        return a.toString();
    }

    //V zdrojovom kode najde kod identifikujuci prve video
    private String getVideoCode(String pageSourceCode) {
        int index = pageSourceCode.indexOf("v=") + 2;
        pageSourceCode = pageSourceCode.substring(index, index + 11);

        return pageSourceCode;
    }

    //Vytvori url link
    private String getVideoUrl(String videoCode) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://www.youtube.com/v/");
        sb.append(videoCode);
        sb.append("?fs=1");
        return sb.toString();
    }

}
