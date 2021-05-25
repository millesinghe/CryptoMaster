package cmd;

import helper.ChoiceRequestor;
import util.Constants;
import vender.binance.ops.BinanceHandler;

/**
 * @author Milinda
 */
public class IntroView implements CmdView{

    private void splashScreen() {
        System.out.println("------- Crypto Manager -------");
    }

    public void display() {
        this.splashScreen();

        String[] options = {"View My Coins","Update Coin Portfolio","Calculate Profit Margins","Settings","QUIT"};
        int choice = ChoiceRequestor.requestOption(">> Select a option to proceed",options);
        CmdView view = null;


        switch (choice) {
            case 1 :
                System.out.println(">> Coin Portfolio - Binance");
                String reply = ChoiceRequestor.requestAnswer("Go back <Y> ",false);
            case 2 :
                options = new String[]{Constants.BINANCE, Constants.COINBASE};
                choice = ChoiceRequestor.requestOption(">> Select the Crypto market to proceed",options);
                if(choice==1){
                    view = new MyMarketView(Constants.BINANCE);
                    view.display();
                }
                break;
            case 3 :
                System.out.println("Unimplemented choice");
                break;
            case 4 :
                view = new SettingView();
                view.display();
                break;
            case 6 :
                System.exit(1);
                break;
        }
    }

}
