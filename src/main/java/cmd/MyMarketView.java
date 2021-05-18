package cmd;

import model.dao.xml.CoinDAO;
import model.dao.xml.CoinsDAO;
import model.dao.xml.TransactionDAO;
import helper.ChoiceRequestor;
import process.BOHelper;
import process.BOManager;
import vender.binance.ops.BinanceAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Milinda
 */
public class MyMarketView implements CmdView{

    private String marketName;

    public MyMarketView(String marketName) {
        this.marketName = marketName;
    }

    public void display() {
        String[] options = {"Bought new coins","Sold existing coins","Sync with Market", "BACK", "QUIT"};
        int choice = ChoiceRequestor.requestOption(">> Select a choice on Update Coin Portfolio",options);
        boolean status = false;
        switch (choice) {
            case 1 :
                status= this.option1();
                break;
            case 2 :
                status= this.option2();
                break;
            case 3 :
                BinanceAPI ba = new BinanceAPI();
                ba.insertDefaultCoins();
                ba.requestUpdatedCoinsStats();
                ba.requestUsersWalletCoins();
                status = true;
                break;
            case 4 :
                this.option4();
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

    private boolean option1(){
        System.out.println(">> Add the bought coin details");
        String id = ChoiceRequestor.requestAnswer("Coin Id \t\t\t - ",false);
        String name = new BOHelper().getCoinName(id);

        if (name == null) {
            System.err.println("No coin detail matched with feed data");
            return false;
        }

        System.out.println("Coin Name \t\t\t - "+ name);
        String amount = ChoiceRequestor.requestAnswer("Bought Amount * \t - ",false);
        String price = ChoiceRequestor.requestAnswer("Unit Price ("+id+")* \t - ",false);
        String date = ChoiceRequestor.requestAnswer("Bought Date * \t\t - ",false);
        String fee = ChoiceRequestor.requestAnswer("Transaction Fee \t - ",false);

        new BOManager().addNewCoinTx(true, id,name,date,amount,price,fee);
        return true;
    }

    private boolean option2(){
        System.out.println(">> Add the Sold coin details");
        String id = ChoiceRequestor.requestAnswer("Coin Id \t\t\t - ",false);
        String name = new BOHelper().getCoinName(id);

        if (name == null) {
            System.err.println("No coin detail matched with feed data");
            return false;
        }

        System.out.println("Coin Name \t\t\t - "+ name);
        String amount = ChoiceRequestor.requestAnswer("Sold Amount \t - ",false);
        String price = ChoiceRequestor.requestAnswer("Unit Price ("+id+")\t - ",false);
        String date = ChoiceRequestor.requestAnswer("Sold Date \t\t - ",false);
        String fee = ChoiceRequestor.requestAnswer("Transaction Fee \t - ",false);

        new BOManager().addNewCoinTx(false, id,name,date,amount,price,fee);
        return true;
    }


    private boolean option4(){
        boolean isBuy = true;
        String id = "btc";
        String name = "bitcoin";
        String date = "asdfgh";
        String amount = "100";
        String price = "215.1";
        String fee = "0.1";
        new BOManager().addNewCoinTx(isBuy,id,name,date,amount,price,fee);
        return true;
    }


    private CoinsDAO sampleCoin() {
        TransactionDAO tx1 = new TransactionDAO("21/01/21",10.00,100.00, true);
        TransactionDAO tx2 = new TransactionDAO("21/01/22",20.00,200.00,0.01,1, true);
        List txs = new ArrayList<TransactionDAO>();
        txs.add(tx1);

        CoinDAO c = new CoinDAO("BTC", "Bitcoin");
        c.setTx(txs);

        txs.add(tx2);
        CoinDAO c1 = new CoinDAO("ETH", "Etherium");
        c1.setTx(txs);

        List clist = new ArrayList<TransactionDAO>();
        CoinsDAO coinsdao = new CoinsDAO();
        clist.add(c);
        clist.add(c1);
        coinsdao.setCoin(clist);
        return coinsdao;
    }

}
