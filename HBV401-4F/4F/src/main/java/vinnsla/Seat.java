package vinnsla;

public class Seat {
    private int row;
    private int col;
    private SeatStatus status;

    public Seat(int row, int col) {
        this.row = row;
        this.col = col;
        this.status = SeatStatus.AVAILABLE;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public String getSeatLabel() {
        char letter;
        switch (col) {
            case 0: letter = 'A'; break;
            case 1: letter = 'B'; break;
            case 2: letter = 'C'; break;
            case 3: letter = 'D'; break;
            default: letter = '?';
        }
        return (row + 1) + "" + letter;
    }
}
