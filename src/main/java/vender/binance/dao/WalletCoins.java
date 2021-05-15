package vender.binance.dao;

/**
 * @author Milinda
 */
public class WalletCoins {

     private String asset;
     private String free;
     private String locked;

    public WalletCoins() {}

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }
}
