package test;

import org.junit.jupiter.api.Test;
import test.service.ParserSeatService;
import test.entity.ParserSeat;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testParserSeatValidSeatNameReturnsResultParser() {
        String seatName = "Сектор A Ряд 1 Место 5";
        long id = 123;
        ParserSeat result = ParserSeatService.parser(seatName, id);

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
        ParserSeat result = ParserSeatService.parser(seatName, id);

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
        ParserSeat result = ParserSeatService.parser(seatName, id);

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
        ParserSeat result = ParserSeatService.parser(seatName, id);

        assertNull(result);
    }

    @Test
    void testResultParserGetFullSectorSectorAndSectorNameReturnsCombinedString() {
        ParserSeat result = new ParserSeat();
        result.setSector("A");
        result.setSectorName("VIP");
        assertEquals("A VIP", result.getFullSector());
    }

    @Test
    void testResultParserGetFullSectorSectorIsSectorReturnsSectorName() {
        ParserSeat result = new ParserSeat();
        result.setSector("сектор");
        result.setSectorName("VIP");
        assertEquals("VIP", result.getFullSector());
    }

    @Test
    void testResultParserGetFullSectorNullSectorOrSectorNameReturnsEmptyString() {
        ParserSeat result1 = new ParserSeat();
        result1.setSector("A");
        result1.setSectorName(null);
        assertEquals("", result1.getFullSector());

        ParserSeat result2 = new ParserSeat();
        result2.setSector(null);
        result2.setSectorName("VIP");
        assertEquals("", result2.getFullSector());
    }

    @Test
    void testResultParserGetFullRowValidRowAndRowNameReturnsCombinedString() {
        ParserSeat result = new ParserSeat();
        result.setRow("Ряд");
        result.setRowName("1");
        assertEquals("Ряд 1", result.getFullRow());
    }

    @Test
    void testResultParserGetFullRowNullRowOrRowNameReturnsEmptyString() {
        ParserSeat result1 = new ParserSeat();
        result1.setRow("Ряд");
        result1.setRowName(null);
        assertEquals("", result1.getFullRow());

        ParserSeat result2 = new ParserSeat();
        result2.setRow(null);
        result2.setRowName("1");
        assertEquals("", result2.getFullRow());
    }

    @Test
    void testResultParserGetFullSeatValidSeatAndSeatNameReturnsCombinedString() {
        ParserSeat result = new ParserSeat();
        result.setSeat("Место");
        result.setSeatName("5");
        assertEquals("Место 5", result.getFullSeat());
    }

    @Test
    void testResultParserGetFullSeatNullSeatOrSeatNameReturnsEmptyString() {
        ParserSeat result1 = new ParserSeat();
        result1.setSeat("Место");
        result1.setSeatName(null);
        assertEquals("", result1.getFullSeat());

        ParserSeat result2 = new ParserSeat();
        result2.setSeat(null);
        result2.setSeatName("5");
        assertEquals("", result2.getFullSeat());
    }
}
