package ru.netology.graphics.image;

public class TextColorShemaImpl implements TextColorSchema {
    private final char[] symbols = {'#', '$', '@', '%', '*', '+', '-', '\''};

    @Override
    public char convert(int color) {
        return symbols[color / 32];

    }
}
