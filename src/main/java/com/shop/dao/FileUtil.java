package com.shop.dao;

import com.shop.exception.ShopFileException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

class FileUtil<T> {

    public List<T> readEntities(String filename, Function<String[], T> function) throws ShopFileException {
        List<T> entityList = new ArrayList<>();
        try {
            File productFile = new File("./src/main/resources/" + filename);
            Scanner productScanner = new Scanner(productFile);
            while (productScanner.hasNextLine()) {
                String line = productScanner.nextLine();
                if (line.length() != 0) {
                    T entity = function.apply(line.split("\\|"));
                    entityList.add(entity);
                }
            }
        } catch (IOException e) {
            throw new ShopFileException("File not found", e);
        }

        return entityList;
    }
}
