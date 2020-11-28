package fr.entasia.factools.utils;

public class Other {

    public int getRandom(int min, int max) {
        return (int) ((Math.random() * (max-min)) + min);
    }
}
