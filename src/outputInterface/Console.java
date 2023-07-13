//package outputInterface;
//
//import Interface.*;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//
//public class Console {
//    public String input() {
//
//    }
//
//    public void display(String message) {
//        /*  */
//        String currentBot = "PERSA";
//        String text = "";
//        switch (message) {
//            case "@wiki", "@wikibot":
//                currentBot = "Wikibot";
//                text = "I am the wiki bot\n";
//                break;
//            case "@translator", "@translatorbot":
//                currentBot = "Translatebot";
//                text = "I am the translator bot\n";
//                break;
//            case "@weather", "@weatherbot":
//                currentBot = "Weatherbot";
//                text = "I am the weather bot\n";
//                break;
//            default:
//                text = "This bot does not exist\n";
//                break;
//        }
//        System.out.print(String.format("%s: ", currentBot) + text);
//    }
//}
