package zad2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil extends SimpleFileVisitor<Path> {

    private static FileChannel outputFileChannel;
    private static String nameOfFile;

    static void processDir(String dirName, String resultFileName) {
        nameOfFile = resultFileName;
        Path outputPath = Paths.get(dirName + "/" + resultFileName);
        try {
            outputFileChannel = FileChannel.open(outputPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            Files.walkFileTree(Paths.get(dirName), new Futil());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {

        if (attr.isRegularFile() && !file.getFileName().toString().equals(nameOfFile)) {
            FileChannel innerFileChannel = FileChannel.open(file);
            ByteBuffer buffer = ByteBuffer.allocate((int) attr.size());
            buffer.clear();
            innerFileChannel.read(buffer);
            buffer.flip();
            CharBuffer charBuffer = Charset.forName("Cp1250").decode(buffer);
            ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);

            while (byteBuffer.hasRemaining())
                outputFileChannel.write(byteBuffer);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}