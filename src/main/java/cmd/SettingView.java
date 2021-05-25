package cmd;

import helper.ChoiceRequestor;
import process.BOHelper;
import process.BOManager;
import vender.binance.ops.BinanceHandler;

/**
 * @author Milinda
 */
public class SettingView implements CmdView{

    public void display() {
        String[] options = {"Quick Sync","Advance Sync","Reprocess Analysis", "BACK", "QUIT"};
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
        BinanceHandler bh = new BinanceHandler();
        bh.insertDefaultCoins();
        bh.getUpdatedCoinsStats();
        bh.getUsersWalletCoins();
        return true;
    }

    private boolean option2() {
        BinanceHandler bh = new BinanceHandler();
        bh.requestTransactionHistory();
        return true;
    }

}
