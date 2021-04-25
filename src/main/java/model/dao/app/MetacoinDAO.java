package model.dao.app;

/**
 * @author Milinda
 */
public class MetacoinDAO {
    private double fee;
    private double equalUSDT;

    public MetacoinDAO() {
    }

    public MetacoinDAO(double fee, double equalUSDT) {
        this.fee = fee;
        this.equalUSDT = equalUSDT;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getEqualUSDT() {
        return equalUSDT;
    }

    public void setEqualUSDT(double equalUSDT) {
        this.equalUSDT = equalUSDT;
    }
}
