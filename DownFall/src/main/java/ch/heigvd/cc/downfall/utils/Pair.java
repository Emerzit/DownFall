package ch.heigvd.cc.downfall.utils;

public class Pair<T,U> {
    private T firstValue;
    private U secondValue;

    public Pair(T firstValue, U secondValue){
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public T first(){
        return firstValue;
    }

    public U second(){
        return secondValue;
    }

    public void setFirstValue(T firstValue){
        this.firstValue = firstValue;
    }

    public void setSecondValue(U secondValue){
        this.secondValue = secondValue;
    }
}