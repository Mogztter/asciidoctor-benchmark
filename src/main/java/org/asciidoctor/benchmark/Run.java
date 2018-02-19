package org.asciidoctor.benchmark;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.AttributesBuilder;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.SafeMode;

import java.io.*;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

import static org.asciidoctor.Asciidoctor.Factory.create;

public class Run {

  public static void main(String[] args) throws IOException {
    Asciidoctor asciidoctor = create();
    File baseDir = Paths.get(System.getProperty("user.dir"), "fixtures").toFile();
    Map<String, Object> options = OptionsBuilder.options()
      .baseDir(baseDir)
      .safe(SafeMode.SAFE)
      .docType("article")
      .headerFooter(true)
      .attributes(AttributesBuilder.attributes()
        .attribute("linkcss")
        .attribute("copycss!")
        .attribute("toc!")
        .attribute("numbered!")
        .attribute("icons!")
        .attribute("compat-mode")
        .asMap())
      .asMap();
    String content = read(new FileInputStream(new File(baseDir, "userguide.adoc")));
    int runs = 4;
    for (int i = 1; i <= runs; i++) {
      long start = System.currentTimeMillis();
      asciidoctor.convert(content, options);
      System.out.println("Run " + i + ": " + (System.currentTimeMillis() - start) + "ms");
    }
  }

  private static String read(InputStream input) throws IOException {
    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
      return buffer.lines().collect(Collectors.joining("\n"));
    }
  }
}
