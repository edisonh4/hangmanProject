import java.sql.SQLOutput;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        String[] testArray = {"things","stuff","objects","tre"};
        String word1 = getRandomWord(testArray);
        System.out.println(word1);
    }
    private static String getRandomWord(String[] array) {
        Scanner scanner = new Scanner(System.in);
        int lengthOfWord = -1;
        while (lengthOfWord<1 || lengthOfWord>17){
            System.out.println("Input the number of characters you want your word to have from 1 - 20:");
            lengthOfWord = scanner.nextInt();
        }
//        int min = 1;
//        int max = array.length;
//        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);
        int random = (int) (Math.random()*array.length);
        String word = array[random];
        while (word.length() !=lengthOfWord){
            random++;
            if(random == array.length){
                random = 0;
            }
            word = array[random];
        }
//        System.out.println(word);
        return word;
    }
}
