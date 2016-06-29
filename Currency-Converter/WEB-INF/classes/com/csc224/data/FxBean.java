/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc224.data;

import java.io.Serializable;


/**
 *
 * @author Sean
 */
public class FxBean implements Serializable{
    
   private String srcCucy;
   private String srcAmt;
   private String dstCucy;
   private String dstAmt;
   public static final String FX_CURRENCIES_KEY = "fxCurrencies";
   public static final String DST_AMNT_KEY = "dst.amt";
   public static final String DST_CUCY_KEY = "dst.cucy";
   public static final String SRC_AMNT_KEY = "src.amt";
   public static final String SRC_CUCY_KEY = "src.cucy";
   
    public String getSrcCucy() {
        return srcCucy;
    }

    public void setSrcCucy(String srcCucy) {
        this.srcCucy = srcCucy;
    }

    public String getSrcAmt() {
        return srcAmt;
    }

    public void setSrcAmt(String srcAmnt) {
        this.srcAmt = srcAmnt;
    }

    public String getDstCucy() {
        return dstCucy;
    }

    public void setDstCucy(String dstCucy) {
        this.dstCucy = dstCucy;
    }

    public String getDstAmt() {
        return dstAmt;
    }

    public void setDstAmt(String dstAmnt) {
        this.dstAmt = dstAmnt;
    }
    
    public String getFxCurrenciesKey()
    {
        return FX_CURRENCIES_KEY;
    }


    
    public FxBean(){
        srcAmt = null;
        srcCucy = null;
        dstCucy = null;
        dstAmt = null;
    }
}
