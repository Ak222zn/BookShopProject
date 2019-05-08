public enum BuyStatus {
    OK(0),
    NOT_IN_STOCK(1),
    DOES_NOT_EXIST(2);

    private int buyStatus;

    BuyStatus(int buyStatus) {
        this.buyStatus = buyStatus;
    }

    public int getBuyStatus() {
        return buyStatus;
    }
}
