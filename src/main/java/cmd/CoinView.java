package cmd;

import operation.ChoiceRequestor;
import process.BOHelper;
import process.BOManager;

/**
 * @author Milinda
 */
public class CoinView implements CmdView{

    public void display() {
        String[] options = {"View Coin Stats","Re-process","SETTING", "BACK", "QUIT"};
        int choice = ChoiceRequestor.requestOption(">> Select a choice",options);
        boolean status = false;
        switch (choice) {
            case 1 :
                status= this.option1();
                break;
            case 2 :
                status= this.option2();
                break;
            case 4 :
                System.out.println("Unimplemented choice");
                break;
            case 5 :
                status= true;
                System.exit(1);
                break;
        }

        if (status) {
            System.out.println("Operation Success ");
        } else {
            System.err.println("Operation Failed");
        }


    }

    private boolean option1() {
        System.out.println(">> Add the bought coin details");
        String id = ChoiceRequestor.requestAnswer("Coin Id \t\t\t - ",false);
        String name = new BOHelper().getCoinName(id);

        if (name == null) {
            System.err.println("No coin detail matched with feed data");
            return false;
        }

        System.out.println("Coin Name \t\t\t - "+ name);
        double amount = ChoiceRequestor.requestDouble("Bought Amount * \t - ",false);
        double price = ChoiceRequestor.requestDouble("Unit Price ("+id+")* \t - ",false);
        String date = ChoiceRequestor.requestAnswer("Bought Date * \t\t - ",false);
        double fee = ChoiceRequestor.requestDouble("Transaction Fee \t - ",false);
        double equalUSDT = ChoiceRequestor.requestDouble("Unit price ( USDT ) \t - ",false);

        new BOManager().addNewCoin(true, id,name,date,amount,price,fee,equalUSDT);
        return true;
    }

    private boolean option2() {

        return false;
    }

}
