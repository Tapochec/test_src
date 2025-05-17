package test.entity;

public class ParserSeat {
  private long id;
  private String sector;
  private String sectorName;
  private String row;
  private String rowName;
  private String seat;
  private String seatName;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getSector() {
    return sector;
  }

  public void setSector(String sector) {
    this.sector = sector;
  }

  public String getSectorName() {
    return sectorName;
  }

  public void setSectorName(String sectorName) {
    this.sectorName = sectorName;
  }

  public String getRow() {
    return row;
  }

  public void setRow(String row) {
    this.row = row;
  }

  public String getRowName() {
    return rowName;
  }

  public void setRowName(String rowName) {
    this.rowName = rowName;
  }

  public String getSeat() {
    return seat;
  }

  public void setSeat(String seat) {
    this.seat = seat;
  }

  public String getSeatName() {
    return seatName;
  }

  public void setSeatName(String seatName) {
    this.seatName = seatName;
  }

  public String getFullSector() {
    String fullSector = concatenateWithSpace(sector, sectorName);
      if (sector!= null && sector.equalsIgnoreCase("сектор")) {
        return sectorName;
      }
    return fullSector;
  }

  public String getFullRow() {
    return concatenateWithSpace(row, rowName);
  }

  public String getFullSeat() {
    return concatenateWithSpace(seat, seatName);
  }

  // Вспомогательный метод для объединения строк с пробелом, если обе не null
  private String concatenateWithSpace(String str1, String str2) {
    if (str1 == null || str2 == null) {
      return "";
    }
    return str1 + " " + str2;
  }
}
