package org.sfabrics.orders.components;

import java.util.regex.Pattern;

public class HtmlHighlighter {
    private static final String HighLightTemplate = "<span style='background:yellow;'>$1</span>";

    public static String highlightText(String text, String textToHighlight) {
        if(textToHighlight.length()==0){
            return text;
        }

        try {
            text = text.replaceAll("(?i)(" + Pattern.quote(textToHighlight) + ")", HighLightTemplate);
            System.out.println("highlightText:"+text);
        } catch (Exception e) {
            return text;
        }
        return "<html>" + text + "</html>";
        //return text;
    }
}