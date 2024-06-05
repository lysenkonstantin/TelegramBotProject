package utils;

import commands.AppBotCommand;
import functions.FilterOperation;
import functions.ImageOperation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class ImageUtils {
    static public BufferedImage getImage(String path) throws IOException {
        return ImageIO.read(new File(path));
    }

    static public void saveImage(BufferedImage image, String path) throws IOException {
        ImageIO.write(image, "jpg", new File(path));
    }

    static float[] rgbIntToArray(int pixel) {
        Color color = new Color(pixel);
        return color.getRGBComponents(null);
    }

    static int arrayToRgbInt(float[] pixel) throws RuntimeException {
        Color color = null;
        if (pixel.length == 3) {
            color = new Color(pixel[0], pixel[1], pixel[2]);
        } else if (pixel.length == 4) {
            color = new Color(pixel[0], pixel[1], pixel[2], pixel[3]);
        }
        if (color != null) {
            return color.getRGB();
        }
        throw new RuntimeException("Внимание, ошибка!");
    }

    public static ImageOperation getOperation(String operationName) {
        FilterOperation filterOperations = new FilterOperation();
        Method[] classMethods = filterOperations.getClass().getDeclaredMethods();
        for (Method method : classMethods) {
            if (method.isAnnotationPresent(AppBotCommand.class)) {
                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
                if (command.name().equals(operationName)) {
                    return (f) -> (float[]) method.invoke(filterOperations, f);
                }
            }
        }
        return null;
    }
}
