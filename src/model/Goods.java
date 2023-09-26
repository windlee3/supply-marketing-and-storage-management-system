package model;

public class Goods {
    private String barcode;
    private String goodsname;
    private String goodstype;
    private String inprice;
    private String outprice;
    private String discount;
    private String number;
    private String time;

    public Goods() {

    }

    public Goods(String barcode, String goodsname, String goodstype, String inprice, String outprice, String discount, String number, String time) {
        this.barcode = barcode;
        this.goodsname = goodsname;
        this.goodstype = goodstype;
        this.inprice = inprice;
        this.outprice = outprice;
        this.discount = discount;
        this.number = number;
        this.time = time;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(String goodstype) {
        this.goodstype = goodstype;
    }

    public String getInprice() {
        return inprice;
    }

    public void setInprice(String inprice) {
        this.inprice = inprice;
    }

    public String getOutprice() {
        return outprice;
    }

    public void setOutprice(String outprice) {
        this.outprice = outprice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}