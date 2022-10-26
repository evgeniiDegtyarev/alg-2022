package ru.tasks.map;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

        public static void main(String[] args) {
            JFileChooser fileChooser = new JFileChooser();
            int fileIsOpen = fileChooser.showDialog(null, "Open");
            if (fileIsOpen == JFileChooser.APPROVE_OPTION) {
                System.out.println("Выбранный файл: " + fileChooser.getSelectedFile());
                System.out.println("Обработка...");
                try {
                    File openedFile = fileChooser.getSelectedFile();
                    Reader reader = new FileReader(openedFile);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    HashMap<String, Long> hashMap = new HashMap<>();
                    LogParser logParser;
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        logParser = new LogParser(line);
                        if (!hashMap.containsKey(logParser.getClientIP())) {
                            hashMap.put(logParser.getClientIP(), logParser.getBytes());
                        } else {
                            hashMap.replace(logParser.getClientIP(),
                                    hashMap.get(logParser.getClientIP()) + logParser.getBytes());
                        }
                    }
                    bufferedReader.close();
                    HashMap<Long, String> topIP = new HashMap<>();
                    String[] keys = hashMap.keySet().toArray(new String[1]);
                    for (int i = 1; i < hashMap.size(); i++) {
                        topIP.put(hashMap.get(keys[i]), keys[i]);
                    }
                    Long[] topIPKeys = topIP.keySet().toArray(new Long[1]);
                    Arrays.sort(topIPKeys);
                    System.out.println("Топ 10 по трафику:");
                    for (int i = topIPKeys.length - 1; i >= topIPKeys.length - 15; i--) {
                        System.out.println("IP: " + topIP.get(topIPKeys[i]));
                        System.out.println("Трафик (Байт): " + topIPKeys[i]);
                        System.out.println();
                    }
                    System.out.println("Выберите путь для сохранения файла:");
                    fileIsOpen = fileChooser.showDialog(null, "Save");
                    if (fileIsOpen == JFileChooser.APPROVE_OPTION) {
                        System.out.println("Выбранный путь: " + fileChooser.getSelectedFile());
                        File savedFile = fileChooser.getSelectedFile();
                        Writer writer = new FileWriter(savedFile);
                        BufferedWriter bufferedWriter = new BufferedWriter(writer);
                        for (int i = topIPKeys.length - 1; i >= topIPKeys.length - 15; i--) {
                            bufferedWriter.write("IP: " + topIP.get(topIPKeys[i]) + "\n");
                            bufferedWriter.write("Трафик (Байт): " + topIPKeys[i] + "\n");
                            bufferedWriter.newLine();
                        }
                        bufferedWriter.close();
                    } else {
                        System.out.println("Путь не выбран");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Файл не выбран");
            }
        }
    }
}