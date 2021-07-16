package form;

import java.util.InputMismatchException;
import java.util.Scanner;


public class KeyBord{

    public static String inputKeyBordString(){

        try {
            // キーボード入力を受け付ける
            Scanner keyString = new Scanner(System.in);
            String taskString = keyString.nextLine();

            return taskString;

        }catch (Exception e) {

            System.out.println(e.getMessage());

            String errorMessage = "エラーが発生しました";

            return errorMessage;

        }
    }

    public static int inputKeyBordInt(){

        Scanner yourselect = null;
        int yoursTask = 0 ;

        try {
            // キーボード入力を受け付ける
            yourselect = new Scanner(System.in);
            yoursTask = yourselect.nextInt();
            return yoursTask;

        } catch (InputMismatchException e) {
            System.out.println("入力エラーを検知しました");
            System.out.println(e.getMessage());
            return yoursTask;
        }

    }
}
