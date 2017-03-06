package clock;
//import java.awt.event.*;
//import java.awt.Toolkit;
//import javax.swing.Timer;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
//import java.util.EventListener;
//import java.util.TimerTask;
import java.util.concurrent.*;

import restaurant.Customer;
import restaurant.Order;

/*
 * An timetable for pizza related events. Governs the passage of time.
 */

public final class Clock implements Runnable {
    private static float rate;
    //protected Timer timer;
    //private ThreadPoolExecutor op, fp, dp;
    private ScheduledThreadPoolExecutor stpe, stpec, stped;
    //private TimeTable tt;
    private TimeUnit unit = TimeUnit.SECONDS;
    //private int threads, max_threads, ttl;
    //private ArrayBlockingQueue<Runnable> test = new ArrayBlockingQueue<Runnable>(9);

	/*
     * Default constructor for the clock class.
	 */

    public Clock() {
        this(1);
    }
	
	/*
	 * 
	 */

    public Clock(float rt_ratio) {
        rate = rt_ratio;
        stpe = new ScheduledThreadPoolExecutor(1);
    }

    public static final int convertToClocktime(int realtime) {
        return 0;
    }
	
	/*public static final void convertToRealtime(){}*/

    public static void main(String[] args) {
        Clock c = new Clock();
        c.run();
    }
	
	/*
	private static class TimeTable implements ThreadFactory
	{
		public Thread newThread(Runnable r) 
		{
			return new Thread(r);
		}
	}
	*/
	/*
	 * This method not to be used publicly.
	 */

    public void run() {
        stpe.setCorePoolSize(5);

        for (int i = 1; i < 10; i++) {
            this.drop(new Order(i, new Customer()));
        }

        stpe.shutdown();
        try {
            stpe.awaitTermination(60, unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
	
	/*private static class EventSequence<E>
	{
		private E e;
		//private 
		private static String token = "Order ";
		private static String[] event_sequence;
		private static 
		public EventSequence(E e)
		{
			
			this.e = e;
		}
		public String toString()
		{
			return token + e.toString();
		}
	}*/

    public void drop(Order o) {
        List<String> e = Arrays.asList(
                "Order " + o.getID() + " taken.",
                "Order " + o.getID() + " prepared.",
                "Order " + o.getID() + " cooked.",
                "Order " + o.getID() + " out for delivery.",
                "Order " + o.getID() + " delivered.");

        List<Integer> t = Arrays.asList(0,
                o.getLongestPrepTime(),
                o.getLongestCookTime(),
                1,
                o.getCustomer().getDeliveryTime());

        final ListIterator<Integer> tim_seq = t.listIterator();
        final ListIterator<String> evt_seq = e.listIterator();

        for (int i = tim_seq.next(); tim_seq.hasNext() && evt_seq.hasNext(); tim_seq.set(i += tim_seq.next())) {
            final String s = evt_seq.next();
            stpe.schedule(new Runnable() {
                public void run() {
                    fire(s);
                }
            }, i, unit);
        }
    }

    public static void fire(String msg) {
        System.out.println(msg);
    }
}