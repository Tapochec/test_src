package test.service;

import test.entity.ParserSeat;

import java.util.Map;
import java.util.regex.*;

public class ParserSeatService {

    private static final Pattern PATTERN = Pattern.compile("((?<sector>.*?) (?<sectorName>[\\p{Lu}\\d].*?)|(?<sectorWithoutName>.*?)) (?<row>Ряд) (?<rowName>.*?) (?<seat>Место) (?<seatName>.*)");

    private static final Map<String, String> MISPRINTS = Map.ofEntries(
            Map.entry("С", "C"),
            Map.entry("Е", "E"),
            Map.entry("Т", "T"),
            Map.entry("Н", "H"),
            Map.entry("У", "Y"),
            Map.entry("О", "O"),
            Map.entry("Р", "P"),
            Map.entry("Х", "X"),
            Map.entry("А", "A"),
            Map.entry("В", "B"),
            Map.entry("К", "K"),
            Map.entry("М", "M")
    );

    public ParserSeatService(){

    }

    public static ParserSeat parser(String seatName, long id){
        Matcher m = PATTERN.matcher(seatName);

        if (!m.find()) {
            return null;
        }

        ParserSeat result = new ParserSeat();
        result.setId(id);

        String sectorWithoutName = m.group("sectorWithoutName");
        if (sectorWithoutName != null) {
            result.setSector(sectorWithoutName);
            result.setSectorName("");
        } else {
            result.setSector(m.group("sector"));
            result.setSectorName(m.group("sectorName"));
        }

        result.setRow(m.group("row"));
        result.setRowName(m.group("rowName"));
        result.setSeat(m.group("seat"));
        result.setSeatName(m.group("seatName"));


        if (result.getSector() == null || result.getRow() == null || result.getRowName() == null || result.getSeat() == null || result.getSeatName() == null) {
            return null;
        }

        String sectorName = result.getSectorName();
        if (sectorName != null) {
            for (Map.Entry<String, String> entry : MISPRINTS.entrySet()) {
                sectorName = sectorName.replace(entry.getKey(), entry.getValue());
            }
            result.setSectorName(sectorName);
        }

        return result;
    }

}
