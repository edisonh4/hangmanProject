// Edison Huang
// 12/1/2023
// Assignment #
// This program selects a word from a word list, and the user inputs letters in an attempt to complete the word

import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //takes word from file
        String array[] = reader("HangManWordList");
        Scanner scanner = new Scanner(System.in);
        List<Character> guess = new ArrayList<>();
        int livesLeft = 7;
        boolean whileLoop = true;
        System.out.println(ANSI_BLUE+"Welcome to Hangman!"+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"If you enter a word, the first character would be taken as your guess. Enter a question mark if you want to end."+ANSI_RESET);
        String word = getRandomWord(array);
        while (whileLoop) {
            if (!guessing(scanner, word, guess)) {
                if (guess.get(guess.size() - 1) == '?') {
                    System.out.println(ANSI_RED+"game ended!"+ANSI_RESET);
                    System.out.println("The word was: " + word);
                    System.out.println("  +---+");
                    System.out.println("  |   |");
                    System.out.println("  0   |");
                    System.out.println(" /|\\  |");
                    System.out.println(" / \\  |");
                    System.out.println("      |");
                    System.out.println("=========");
                    break;
                }
                else livesLeft--;
                if (livesLeft == 6){
                    System.out.println("  +---+");
                    System.out.println("  |   |");
                    System.out.println("      |");
                    System.out.println("      |");
                    System.out.println("      |");
                    System.out.println("      |");
                    System.out.println("=========");
                    System.out.println(ANSI_RED+"Wrong guess, your life count is now " + (livesLeft)+ANSI_RESET);
                } else if (livesLeft == 5) {
                    System.out.println("  +---+");
                    System.out.println("  |   |");
                    System.out.println("  0   |");
                    System.out.println("      |");
                    System.out.println("      |");
                    System.out.println("      |");
                    System.out.println("=========");
                    System.out.println(ANSI_RED+"Wrong guess, your life count is now " + (livesLeft)+ANSI_RESET);
                } else if (livesLeft == 4) {
                    System.out.println("  +---+");
                    System.out.println("  |   |");
                    System.out.println("  0   |");
                    System.out.println("  |   |");
                    System.out.println("      |");
                    System.out.println("      |");
                    System.out.println("=========");
                    System.out.println(ANSI_RED+"Wrong guess, your life count is now " + (livesLeft)+ANSI_RESET);
                } else if (livesLeft == 3) {
                    System.out.println("  +---+");
                    System.out.println("  |   |");
                    System.out.println("  0   |");
                    System.out.println(" /|   |");
                    System.out.println("      |");
                    System.out.println("      |");
                    System.out.println("=========");
                    System.out.println(ANSI_RED+"Wrong guess, your life count is now " + (livesLeft)+ANSI_RESET);
                } else if (livesLeft == 2) {
                    System.out.println("  +---+");
                    System.out.println("  |   |");
                    System.out.println("  0   |");
                    System.out.println(" /|\\  |");
                    System.out.println("      |");
                    System.out.println("      |");
                    System.out.println("=========");
                    System.out.println(ANSI_RED+"Wrong guess, your life count is now " + (livesLeft)+ANSI_RESET);
                } else if (livesLeft == 1){
                    System.out.println("  +---+");
                    System.out.println("  |   |");
                    System.out.println("  0   |");
                    System.out.println(" /|\\  |");
                    System.out.println(" /    |");
                    System.out.println("      |");
                    System.out.println("=========");
                    System.out.println(ANSI_RED+"Wrong guess, your life count is now " + (livesLeft)+ANSI_RESET);
                }
            }
            if (printWord(word, guess) == true) {
                System.out.println(ANSI_GREEN+"You win!"+ANSI_RESET);
                break;
            }
            if (livesLeft < 1) {
                System.out.println(ANSI_RED+"You lost! Try again"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"The word was: " + word+ANSI_RESET);
                System.out.println(ANSI_RED+"  +---+");
                System.out.println("  |   |");
                System.out.println("  0   |");
                System.out.println(" /|\\  |");
                System.out.println(" / \\  |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            }
            System.out.println(ANSI_YELLOW+"guesses so far: " + guess+ANSI_RESET);
        }
    }
    public static int lineCount(String textFileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileReader(textFileName));
        int count = 0;
        while (scan.hasNextLine()) {
            scan.nextLine();
            count++;
        }
        return count;
    }
    private static String[] reader(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileReader(fileName));
        String[] arr = new String[lineCount(fileName)];
        int i = 0;
        while (scan.hasNext()) {
            arr[i++] = scan.nextLine();
        }
        return arr;
    }
    private static boolean guessing(Scanner scanner, String
            word, List<Character> guess) {
//takes user input and inputs into programme
        System.out.println(ANSI_BLUE+"guess a letter!"+ANSI_RESET);
        String userGuess = scanner.nextLine();
        userGuess = userGuess.toUpperCase();
        while (userGuess.equals("")){
            System.out.println("Guess again");
            userGuess = scanner.nextLine();
            userGuess = userGuess.toUpperCase();
        }
        guess.add(userGuess.charAt(0));
        return word.contains(userGuess);
    }

    private static boolean printWord(String word, List<Character> guess) {
//Prints out the word with all the guessed letters
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (guess.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {
                System.out.print("_");
            }
        }
        System.out.println();
        return (word.length() == correctCount);
    }

    private static String getRandomWord(String[] array) {
        Scanner scanner = new Scanner(System.in);
        int lengthOfWord = -1;
        while (lengthOfWord<1 || lengthOfWord>20){
            System.out.println(ANSI_YELLOW+"Input the number of characters you want your word to have from 1 - 20:"+ANSI_RESET);
            try {
                lengthOfWord = Integer.parseInt(scanner.next());
            }
            catch(NumberFormatException e) {
            }
        }
        int random = (int) (Math.random()*array.length);
        String word = array[random];
        while (word.length() !=lengthOfWord){
            random++;
            if(random == array.length){
                random = 0;
            }
            word = array[random];
        }
        return word;
    }
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
}
