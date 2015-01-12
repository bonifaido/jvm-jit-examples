import java.util.ArrayList;
import java.util.List;

/**
 * Try with -XX:+PrintGCDetails -XX:-DoEscapeAnalysis (and without -XX:-DoEscapeAnalysis as well)
 *
 * http://blogs.lmax.com/staff-blogs/2013/12/30/jvm-escape-analysis/
 */
public class EscapeAnalysis {
    private static final Object EVENT = new Object();

    public static void main(String[] args) {
        final List<Listener> listeners = new ArrayList<Listener>();
        listeners.add(new Listener());

        for (int i = 0; i < 10000000; i++) {
            for (Listener listener : listeners) {
                listener.onEvent(EVENT);
            }
        }

        System.out.println("Event Count: " + listeners.get(0).getEventCount());
    }

    private static class Listener {
        private long eventCount;

        public void onEvent(Object event) {
            eventCount++;
        }

        public long getEventCount() {
            return eventCount;
        }
    }
}
