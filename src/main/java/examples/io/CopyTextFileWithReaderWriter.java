package examples.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CopyTextFileWithReaderWriter {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(CopyTextFileWithReaderWriter.class);

  public static void main(String[] args) {
    Charset charset = Charset.forName("ISO-8859-1");
    Path quellpfad = Paths.get("xanadu.txt");
    Path zielpfad = Paths.get("neueDatei.txt");
    try (BufferedReader reader = Files.newBufferedReader(quellpfad, charset);
         BufferedWriter writer = Files.newBufferedWriter(zielpfad, charset)) {
      String line = null;
      while ((line = reader.readLine()) != null) {
        writer.write(line);
        writer.newLine();
      }
    } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
    }
  }
}


