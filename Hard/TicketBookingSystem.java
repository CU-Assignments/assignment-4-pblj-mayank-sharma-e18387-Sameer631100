class TicketCounter {
    private int availableSeats = 10;

    public synchronized boolean bookTicket(String personName, int numberOfSeats) {
        if (numberOfSeats <= availableSeats) {
            System.out.println(personName + " successfully booked " + numberOfSeats + " seat(s).");
            availableSeats -= numberOfSeats;
            return true;
        } else {
            System.out.println(personName + " failed to book " + numberOfSeats + " seat(s). Not enough seats.");
            return false;
        }
    }
}

class BookingThread extends Thread {
    private TicketCounter ticketCounter;
    private String personName;
    private int seatsToBook;

    public BookingThread(TicketCounter counter, String name, int seats) {
        this.ticketCounter = counter;
        this.personName = name;
        this.seatsToBook = seats;
    }

    @Override
    public void run() {
        ticketCounter.bookTicket(personName, seatsToBook);
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        TicketCounter counter = new TicketCounter();

        // VIP Booking Threads
        BookingThread vip1 = new BookingThread(counter, "VIP-Alice", 3);
        BookingThread vip2 = new BookingThread(counter, "VIP-Bob", 4);
        vip1.setPriority(Thread.MAX_PRIORITY);
        vip2.setPriority(Thread.MAX_PRIORITY);

        // Regular Booking Threads
        BookingThread user1 = new BookingThread(counter, "User-Charlie", 2);
        BookingThread user2 = new BookingThread(counter, "User-Diana", 3);
        user1.setPriority(Thread.NORM_PRIORITY);
        user2.setPriority(Thread.NORM_PRIORITY);

        // Start VIP bookings first to show prioritization (but no guarantee without explicit thread management)
        vip1.start();
        vip2.start();
        user1.start();
        user2.start();
    }
}
