package masteringthreads.ch5_threading_problems.exercise_5_1;

public class PrinterClass {
    private static final boolean OUTPUT_TO_SCREEN = false;
    private boolean printingEnabled = OUTPUT_TO_SCREEN;

    private static void print(PrinterClass pc, String s) {
        synchronized (PrinterClass.class) {
            if (pc.isPrintingEnabled()) {
                System.out.println("Printing: " + s);
            }
        }
    }

    public void print(String s) { // class, this
        print(this, s);
    }

    public boolean isPrintingEnabled() {
        synchronized (this) {
            return printingEnabled;
        }
    }

    public void setPrintingEnabled(boolean printingEnabled) { // this, class, this
        synchronized (this) {
            if (!printingEnabled) {
                print(this, "Printing turned off!");
            }
            this.printingEnabled = printingEnabled;
        }
    }
}
