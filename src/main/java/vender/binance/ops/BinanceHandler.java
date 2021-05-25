package vender.binance.ops;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.dao.db.BinCoin;
import model.dao.db.Coin;
import model.dao.db.Tx;
import org.json.JSONObject;
import process.BOHelper;
import util.Constants;
import vender.binance.dao.BinanceCoin;
import vender.binance.dao.BinanceTx;
import vender.binance.dao.WalletCoins;
import vender.binance.util.BinanceRequestor;
import vender.core.RequestHeader;

import java.util.HashMap;
import java.util.List;

/**
 * @author Milinda
 */
public class BinanceHandler {

    private final String BALANCES = "balances";

    private BinanceAPI api;

    public BinanceHandler() {
        this.api = new BinanceAPI();
    }

    public boolean getUpdatedCoinsStats() {
        if (!api.checkServiceStatus())
            return false;

        RequestHeader headers = null;
        String result = api.requestUpdatedCoinsStats();

        ObjectMapper objectMapper = new ObjectMapper();
        BinanceCoin[] bcoins = null;
        try {
            bcoins = objectMapper.readValue(result, BinanceCoin[].class);
            if (this.upsertMarketCoins(bcoins)) {
                System.out.println("Successfully imported coin information");
            } else {
                System.err.println("Exception occurred while importing the coins information");
                ;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean upsertMarketCoins(BinanceCoin[] bcoins) {
        for (BinanceCoin bcoin : bcoins) {
            String id = bcoin.getSymbol();
            if (id.substring(id.length() - 4, id.length()).equalsIgnoreCase(Constants.USDT)) {
                if (id.equalsIgnoreCase(Constants.USDT)) {
                    id = Constants.USDT;
                } else {
                    id = id.replace(Constants.USDT, Constants.EMPTY_STR);
                }
                Coin coin = new BinCoin(id, Constants.EMPTY_STR, bcoin.getPrice(), "0", null);
                boolean isInserted = new BOHelper().upsertCoinDB(Constants.BINANCE, coin);
                if (!isInserted) {
                    System.err.println("Exception while inserting a new Coin");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean getUsersWalletCoins() {
        if (!api.checkServiceStatus())
            return false;

        HashMap<String, String> parameters = new HashMap<>();

        String result = api.requestUsersWalletCoins(parameters);
        JSONObject resultJSON = new JSONObject(result);

        ObjectMapper objectMapper = new ObjectMapper();
        WalletCoins[] walletCoins = null;
        try {
            walletCoins = objectMapper.readValue(resultJSON.get(BALANCES).toString(), WalletCoins[].class);
            if (upsertWalletBalance(walletCoins)) {
                System.out.println("Successfully imported User's Wallet information");
            } else {
                System.err.println("Exception occurred while importing the User's Wallet information");
                ;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean upsertWalletBalance(WalletCoins[] wcoins) {
        for (WalletCoins wcoin : wcoins) {
            if (Double.parseDouble(wcoin.getFree()) > 0 || Double.parseDouble(wcoin.getLocked()) > 0) {
                String id = wcoin.getAsset();
                Coin coin = new BinCoin(id, wcoin.getFree(), wcoin.getLocked());
                boolean isInserted = new BOHelper().upsertCoinDB(Constants.BINANCE, coin);
                if (!isInserted) {
                    System.err.println("Exception while inserting a new Coin");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean insertDefaultCoins() {
        // Forcefully adding USDT
        Coin coin = new BinCoin(Constants.USDT, Constants.TETHER, "1", "0", null);
        boolean isInserted = new BOHelper().upsertCoinDB(Constants.BINANCE, coin);
        if (!isInserted) {
            System.err.println("Exception while inserting a new Coin");
            return false;
        }
        return isInserted;
    }

    public boolean requestTransactionHistory() {

        List<Coin> walletCoins = new BOHelper().getWalletCoins(Constants.BINANCE);
        RequestHeader headers = new RequestHeader(BinanceRequestor.class);
        if (!api.checkServiceStatus())
            return false;

        // process USDT
        String sellCoin = Constants.USDT;

        for (int i = 0; i < walletCoins.size(); i++) {

            HashMap<String, String> parameters = new HashMap<>();
            String buyCoin = walletCoins.get(i).getId();
            if (!sellCoin.equals(buyCoin)) {
                parameters.put("symbol", buyCoin + sellCoin);
                String result = api.requestTransactionHistory(parameters);
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    BinanceTx[] txList = objectMapper.readValue(result, BinanceTx[].class);
                    this.upsertTx(txList);

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Successfully imported User's Transaction History information");

        return true;
    }

    private boolean upsertTx(BinanceTx[] txList) {
        if(txList.length > 0 )
            System.out.println("Collecting transactions of "+ txList[0].getSymbol());

        for (BinanceTx tx : txList) {
            String txName = tx.getSymbol();
            String dealCurrency = Constants.USDT;

            if (txName.contains(dealCurrency)) {
                if (!txName.equalsIgnoreCase(Constants.USDT) && this.checkSameCurrency(txName,dealCurrency)) {
                    txName = txName.replace(Constants.USDT, Constants.EMPTY_STR);
                }
                String uSDTEqual = null;
                if(dealCurrency.equals(Constants.USDT)){
                    uSDTEqual = tx.getPrice();
                }
                Tx binTx = new Tx(tx.getId(), tx.getOrderId(),Constants.BINANCE,tx.isIsBuyer(),txName,tx.getQty(),tx.getPrice(),
                        uSDTEqual, Constants.USDT,tx.getQuoteQty(),tx.getCommission(),tx.getTime());
                boolean isInserted = new BOHelper().upsertTxDB(Constants.BINANCE, binTx);
                if (!isInserted) {
                    System.err.println("Exception while inserting a new Transaction");
                    return false;
                }
            }

        }
        return true;


    }

    private boolean checkSameCurrency(String id, String dealCurrency) {
        for(int i = dealCurrency.length(); i > 0 ; i--){
            if(!(id.charAt(id.length()-i) == dealCurrency.charAt(dealCurrency.length()-i))){
                return false;
            }
        }
        return true;
    }
}