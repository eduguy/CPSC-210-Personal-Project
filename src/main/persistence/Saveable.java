package persistence;

import java.io.PrintWriter;

// Represents data that can be saved to file,with code borrowed from CPSC 210 Teller App
public interface Saveable {

    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}
