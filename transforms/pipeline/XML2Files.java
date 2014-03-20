package pipeline;

import java.io.*;

public class XML2Files {
    private final static long totalLines = 760662933L;

    private final static void dumpToFile(long fileId, String s, String folder) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(folder+"/"+Long.toString(fileId));
        out.print(s);
        out.close();
    }

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 2) {
            System.out.println("usage: java XML2Files input-file output-file");
            return;
        }
        final String inputFile = args[0];
        final String outputFile = args[1];

        final int linesPerFile = 100000;
        int filesSoFar = 0;
        long linesSoFar = 0;
        StringBuilder builder = null;
        PrintWriter out = new PrintWriter(outputFile+".0");

        final BufferedReader br = new BufferedReader(new FileReader(inputFile));
        try {
            for(String line; (line = br.readLine()) != null; ) {
                if (builder == null) {
                    final int textStartIndex = line.indexOf("<text");
                    if (textStartIndex != -1) {
                        builder = new StringBuilder();
                        final int endBracketIndex = line.indexOf(">", textStartIndex);
                        if (endBracketIndex < line.length()-1) {
                            builder.append(line.substring(endBracketIndex + 1));
                        }

                        final int closeTag = builder.indexOf("</text");
                        if (closeTag != -1) {
                            out.println(builder.substring(0, closeTag));
                            filesSoFar++;
                            if (filesSoFar % linesPerFile == 0) {
                                out.close();
                                out = new PrintWriter(outputFile+"."+filesSoFar);
                            }
                            builder = null;
                        }
                    }
                } else {
                    final int textEndIndex = line.indexOf("</text");
                    if (textEndIndex != -1) {
                        builder.append(" "+line.substring(0, textEndIndex));

                        out.println(builder.toString());
                        filesSoFar++;
                        if (filesSoFar % linesPerFile == 0) {
                            out.close();
                            out = new PrintWriter(outputFile+"."+filesSoFar);
                        }

                        builder = null;
                    } else {
                        builder.append(" "+line);
                    }
                }
                linesSoFar++;

                if (linesSoFar % 1000000 == 0) {
                    System.out.format("%2.3f%%\n", (double)linesSoFar / (double)totalLines);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        out.close();
    }
}
