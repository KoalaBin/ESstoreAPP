package com.koalabee.esstore;

/**
 * Created by Koala Bee on 2018/11/23.
 */

public class SalerClass {
    private String sale_class;
    private int sale_count;

    public SalerClass(String sale_class,int sale_count){
        this.sale_class=sale_class;
        this.sale_count=sale_count;
    }
    public String getSale_class() {
        return sale_class;
    }


    public int getSale_count() {
        return sale_count;
    }

}
