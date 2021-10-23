package com.sprite.cloud.automation.framework.base.utilities;

public class Random {

    public static int randomIntInRange(int max, int min){
        return (int) ((Math.random() * (max - min)) + min);
    }


}
