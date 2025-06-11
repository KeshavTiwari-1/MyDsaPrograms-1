package Trie.autocompletesearch;

public class AutoComleteMain {
    public static void main(String[] args){
        AutoCompleteSystem autoCompleteSystem = new AutoCompleteSystem(
                new String[]{"i love you", "island", "iroman", "i love leetcode"},
                new int[]{5, 3, 2, 2}
        );
        String[] input = new String[]{
                "i" , " ", "a", "#"
        };
        for(String i : input){
            System.out.println(autoCompleteSystem.input(i.charAt(0)).toString());
        }
    }
}
