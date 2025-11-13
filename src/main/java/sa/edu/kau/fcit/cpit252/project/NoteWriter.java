package sa.edu.kau.fcit.cpit252.project;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class NoteWriter {
    private static NoteWriter instance;
    private final String filename = "records.txt";
    private final List<String> buffer = new ArrayList<>();

    private NoteWriter() {}

    public static synchronized NoteWriter getInstance() {
        if (instance == null) {
            instance = new NoteWriter();
        }
        return instance;
    }

    public synchronized void addSummary(String content) {
        buffer.add(content);
    }

    public synchronized void save() {
        try {
            File file = new File(filename);
            StringBuilder old = new StringBuilder();
            if (file.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        old.append(line).append(System.lineSeparator());
                    }
                }
            }

            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename, false), StandardCharsets.UTF_8))) {
                for (String s : buffer) out.print(s);
                out.println(old.toString());
            }
        } catch (IOException e) {
            System.out.println("Error writing to note.txt: " + e.getMessage());
        }
    }

    public String getFilename() {
        return filename;
    }
}
