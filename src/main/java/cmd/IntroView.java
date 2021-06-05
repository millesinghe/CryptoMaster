package cmd;

import helper.ChoiceRequestor;
import util.Constants;
import vender.binance.ops.BinanceHandler;

/**
 * @author Milinda
 */
public class IntroView implements CmdView{

    private  BinanceHandler bh;

    private void splashScreen() {
        System.out.println("------- Crypto Manager -------");
        bh = new BinanceHandler();
    }

    public void display() {
        this.splashScreen();

        String[] options = {"View My Coins","Sync Wallets","Calculate Profit Margins","Update Coin Portfolio","QUIT"};
        int choice = ChoiceRequestor.requestOption(">> Select a option to proceed",options);
        CmdView view = null;

        switch (choice) {
            case 1 :
                System.out.println(">> Coin Portfolio - Binance");
                String[] statsMenu = {"All Coin Status","Timeline Coin Status"};
                choice = ChoiceRequestor.requestOption(">> Select a choice",statsMenu);
                boolean status = false;
                switch (choice) {
                    case 1 :
                        bh.insertDefaultCoins();
                        bh.getUpdatedCoinsStats();
                        bh.getUsersWalletCoins();

                        System.out.println("\n\n===============================================");
                        System.out.println("---------------- Wallet Profit ----------------");
                        bh.getWalletProfit(true,null);

                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }

                        System.out.println("\n\n-------- Already Sold with Profit------");
                        bh.getWalletProfit(false,null);
                        break;
                    case 2 :
                        bh.requestTransactionHistory();
                        break;
                }
                break;
            case 2 :
                String[] syncMenu = {"Quick Sync","Advance Sync"};
                choice = ChoiceRequestor.requestOption(">> Select a choice",syncMenu);
                status = false;
                switch (choice) {
                    case 1 :
                        bh.insertDefaultCoins();
                        bh.getUpdatedCoinsStats();
                        bh.getUsersWalletCoins();
                        break;
                    case 2 :
                        bh.requestTransactionHistory();
                        break;
                    case 3 :
                        System.out.println("Unimplemented choice");
                        break;
                    case 4 :
                        status= true;
                        System.exit(1);
                        break;
                }
                break;
            case 3 :
                System.out.println("Unimplemented choice");
                break;
            case 4 :
                options = new String[]{Constants.BINANCE, Constants.COINBASE};
                choice = ChoiceRequestor.requestOption(">> Select the Crypto market to proceed",options);
                if(choice==1){
                    view = new MyMarketView(Constants.BINANCE);
                    view.display();
                }
                break;
            case 6 :
                System.exit(1);
                break;
        }
    }

}
