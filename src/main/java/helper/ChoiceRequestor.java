package helper;

import java.util.Scanner;

/**
 * @author Milinda
 */
public class ChoiceRequestor {

    public static int requestOption(String question, String[] options){

        int anz = 0;
        do{
            System.out.println(String.valueOf(question));
            for (int i = 0 ; i < options.length; i++){
                System.out.println(String.valueOf(i+1) + " - " + options[i]);
            }
            try{
                Scanner in = new Scanner(System.in);
                anz = in.nextInt();
                if (anz > options.length){
                    anz = 0;
                    throw new Exception();
                }
            }catch (Exception e){
                System.err.println("INVALID INPUT <please input a valid choice>");
            }
        }while(anz == 0);
        return anz;
    }

    public static String requestAnswer(String question){
        return ChoiceRequestor.requestAnswer(question,true);
    }

    public static String requestAnswer(String question, boolean answerNextline){
        if(answerNextline)
            System.out.println(String.valueOf(question));
        else
            System.out.print(String.valueOf(question));
        Scanner in = new Scanner(System.in);
        return in.next();
    }

    public static int requestInt(String question){
        return ChoiceRequestor.requestInt(question,true);
    }

    public static int requestInt(String question, boolean answerNextline){
        int anz = 0;
        do{
            try{
                Scanner in = new Scanner(System.in);
                if(answerNextline)
                    System.out.println(String.valueOf(question));
                else
                    System.out.print(String.valueOf(question));
                anz = in.nextInt();
            }catch (Exception e){
                System.err.println("INVALID INPUT <please input a valid choice>");
            }
        }while(anz == 0);
        return anz;
    }

    public static double requestDouble(String question){
        return ChoiceRequestor.requestDouble(question,true);
    }

    public static float requestDouble(String question, boolean answerNextline){
        float anz = 0.00f;
        do{
            try{
                Scanner in = new Scanner(System.in);
                if (answerNextline)
                    System.out.println(String.valueOf(question));
                else
                    System.out.print(String.valueOf(question));

                anz = in.nextFloat();
            }catch (Exception e){
                System.err.println("INVALID INPUT <please input a valid choice>");
            }
        }while(anz == 0.00);
        return anz;
    }
}
