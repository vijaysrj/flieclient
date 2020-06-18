package picocli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import static java.nio.file.Files.createFile;
import static java.nio.file.Path.of;

@Command(name = "fileCli", description = "Performs file manipulation operations", mixinStandardHelpOptions = true, version = "File Client 1.0")
public class FileClient implements Callable<String> {


    @Option(names = "-cf", description = "Creates a new file with given name")
    private String name;

    @Option(names = "-o", description = "Opens a given file")
    private boolean openFile;

    @Option(names = "-w", description = "Writes to a given file")
    private String fileToWrite;

    @Option(names="-r",description = "Opens a file and displays its contents")
    private String fileToRead;

    @Option(names = "-content", description = "Content to write to a file")
    private String content;

    public static void main(String... args) throws Exception {
        int exitCode = new CommandLine(new FileClient()).execute(args);
        System.exit(exitCode);
    }

    public String call() throws Exception {

        if (name != null) {
            Path file = of(name);
            if (!file.toFile().exists()) {

                createFile(file);
            }

        }
        if (openFile) {
            Runtime.getRuntime().exec("notepad.exe " + name);
        }

        if(fileToRead!=null){

            Runtime.getRuntime().exec("notepad.exe " + fileToRead);
        }

        if (fileToWrite != null) {

            Files.writeString(of(fileToWrite), content);
        }

        return "success";
    }
}
