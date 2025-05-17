package test;

import org.junit.jupiter.api.Test;
import test.entity.ParserSeat;
import test.entity.ResultParser;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testParserSeatValidSeatNameReturnsResultParser() {
        String seatName = "Сектор A Ряд 1 Место 5";
        long id = 123;
        ResultParser result = ParserSeat.parser(seatName, id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Сектор", result.getSector());
        assertEquals("A", result.getSectorName());
        assertEquals("Ряд", result.getRow());
        assertEquals("1", result.getRowName());
        assertEquals("Место", result.getSeat());
        assertEquals("5", result.getSeatName());
        assertEquals("A", result.getFullSector());
        assertEquals("Ряд 1", result.getFullRow());
        assertEquals("Место 5", result.getFullSeat());

    }

    @Test
    void testParserSeatSectorWithoutNameReturnsResultParser() {
        String seatName = "VIP Ряд 2 Место 10";
        long id = 456;
        ResultParser result = ParserSeat.parser(seatName, id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("VIP", result.getSector());
        assertEquals("", result.getSectorName());
        assertEquals("Ряд", result.getRow());
        assertEquals("2", result.getRowName());
        assertEquals("Место", result.getSeat());
        assertEquals("10", result.getSeatName());
    }

    @Test
    void testParserSeatCyrillicSectorNameReturnsResultParserWithLatinSectorName() {
        String seatName = "Сектор M Ряд 3 Место 15";
        long id = 789;
        ResultParser result = ParserSeat.parser(seatName, id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Сектор", result.getSector());
        assertEquals("M", result.getSectorName());
        assertEquals("Ряд", result.getRow());
        assertEquals("3", result.getRowName());
        assertEquals("Место", result.getSeat());
        assertEquals("15", result.getSeatName());
    }

    @Test
    void testParserSeatInvalidSeatNameReturnsNull() {
        String seatName = "Invalid Seat Name";
        long id = 101112;
        ResultParser result = ParserSeat.parser(seatName, id);

        assertNull(result);
    }

    @Test
    void testResultParserGetFullSectorSectorAndSectorNameReturnsCombinedString() {
        ResultParser result = new ResultParser();
        result.setSector("A");
        result.setSectorName("VIP");
        assertEquals("A VIP", result.getFullSector());
    }

    @Test
    void testResultParserGetFullSectorSectorIsSectorReturnsSectorName() {
        ResultParser result = new ResultParser();
        result.setSector("сектор");
        result.setSectorName("VIP");
        assertEquals("VIP", result.getFullSector());
    }

    @Test
    void testResultParserGetFullSectorNullSectorOrSectorNameReturnsEmptyString() {
        ResultParser result1 = new ResultParser();
        result1.setSector("A");
        result1.setSectorName(null);
        assertEquals("", result1.getFullSector());

        ResultParser result2 = new ResultParser();
        result2.setSector(null);
        result2.setSectorName("VIP");
        assertEquals("", result2.getFullSector());
    }

    @Test
    void testResultParserGetFullRowValidRowAndRowNameReturnsCombinedString() {
        ResultParser result = new ResultParser();
        result.setRow("Ряд");
        result.setRowName("1");
        assertEquals("Ряд 1", result.getFullRow());
    }

    @Test
    void testResultParserGetFullRowNullRowOrRowNameReturnsEmptyString() {
        ResultParser result1 = new ResultParser();
        result1.setRow("Ряд");
        result1.setRowName(null);
        assertEquals("", result1.getFullRow());

        ResultParser result2 = new ResultParser();
        result2.setRow(null);
        result2.setRowName("1");
        assertEquals("", result2.getFullRow());
    }

    @Test
    void testResultParserGetFullSeatValidSeatAndSeatNameReturnsCombinedString() {
        ResultParser result = new ResultParser();
        result.setSeat("Место");
        result.setSeatName("5");
        assertEquals("Место 5", result.getFullSeat());
    }

    @Test
    void testResultParserGetFullSeatNullSeatOrSeatNameReturnsEmptyString() {
        ResultParser result1 = new ResultParser();
        result1.setSeat("Место");
        result1.setSeatName(null);
        assertEquals("", result1.getFullSeat());

        ResultParser result2 = new ResultParser();
        result2.setSeat(null);
        result2.setSeatName("5");
        assertEquals("", result2.getFullSeat());
    }
}
