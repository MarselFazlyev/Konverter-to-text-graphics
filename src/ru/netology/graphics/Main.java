package ru.netology.graphics;

import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextColorShemaImpl;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.image.TextGraphicsConverterImpl;
import ru.netology.graphics.server.GServer;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new TextGraphicsConverterImpl();
        TextColorSchema schema = new TextColorShemaImpl();
        converter.setTextColorSchema(schema);
        converter.setMaxRatio(2);
        converter.setMaxHeight(150);
        converter.setMaxWidth(255);
//        converter.convert("https://i.ibb.co/6DYM05G/edu0.jpg");

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

//         Или то же, но с выводом на экран:
//        String url = "https://i.ibb.co/6DYM05G/edu0.jpg";
//        String imgTxt = converter.convert(url);
//        System.out.println(imgTxt);
    }
}
