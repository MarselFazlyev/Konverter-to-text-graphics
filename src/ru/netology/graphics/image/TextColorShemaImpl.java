package ru.netology.graphics.image;

public class TextColorShemaImpl implements TextColorSchema {
    @Override
    public char convert(int color) {
        char[] symbols = {'#', '$', '@', '%', '*', '+', '-', '\''};
        return symbols[color / 32];

    }
}
