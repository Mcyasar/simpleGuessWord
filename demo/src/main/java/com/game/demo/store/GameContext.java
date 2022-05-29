package com.game.demo.store;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.HashSet;

public class GameContext {

    private final String word;
    private final int initialGuessCount;
    private int guessCount;
    private int correctlyGuessedWordLength = 0;

    public GameContext(int guessCount, String word){
        this.initialGuessCount = guessCount;
        this.guessCount = guessCount;
        this.word = word;
    }

    public GameContext(){
        this(20, "sharpener");
    }

    public String getWord() {
        return word;
    }

    public int getGuessCount() {
        return guessCount;
    }
    public int getCorrectlyGuessedWordLength() {
        return correctlyGuessedWordLength;
    }
    
    public void checkGuessWord() throws Exception {

        if(guessCount < initialGuessCount) 
          System.out.println("You have " + guessCount + " attempts left.");

        System.out.print("Enter guess word (" + guessCount + "): ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF8"));
        String guess = br.readLine();

        HashSet<String> wordParsedWithIndex = new HashSet<>();
        HashMap<Character, Integer> charCounter = new HashMap<>();
        CharacterIterator it = new StringCharacterIterator(word);
        while (it.current() != CharacterIterator.DONE) {
            wordParsedWithIndex.add(it.current() + "_" + it.getIndex());
            if (!charCounter.containsKey(it.current())) {
                charCounter.put(it.current(), 1);
            } else {
                charCounter.put(it.current(), (charCounter.get(it.current()) + 1));
            }

            it.next();
        }

        it = new StringCharacterIterator(guess);
        StringBuilder correctlyGuessedLetters = new StringBuilder();
        StringBuilder wrongIndexedGuessedLetters = new StringBuilder();
        while (it.current() != CharacterIterator.DONE) {
            if (wordParsedWithIndex.contains(it.current() + "_" + it.getIndex())) {
                correctlyGuessedLetters.append(it.current());

                if (charCounter.containsKey(it.current())) {
                    charCounter.put(it.current(), (charCounter.get(it.current()) - 1));
                }
            } else if (charCounter.containsKey(it.current()) && charCounter.get(it.current()) > 0) {
                charCounter.put(it.current(), (charCounter.get(it.current()) - 1));
                wrongIndexedGuessedLetters.append(it.current());
            }

            it.next();
        }
        if(correctlyGuessedLetters.length() < word.length()){
            guessCount--;
        }

        correctlyGuessedWordLength = correctlyGuessedLetters.length();
        System.out.println("correctly guessed letters = " + correctlyGuessedWordLength);
        System.out.println("letter in the right place = " + correctlyGuessedWordLength + " / " + correctlyGuessedLetters.toString());

        if(wrongIndexedGuessedLetters.length() > 0){
            System.out.println("correctly guessed letters in the wrong place = " + wrongIndexedGuessedLetters.length()  + " / " + wrongIndexedGuessedLetters.toString());
        }
        
    }
    
}
