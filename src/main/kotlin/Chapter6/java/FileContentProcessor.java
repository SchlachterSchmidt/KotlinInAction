package Chapter6.java;

import java.io.File;
import java.util.List;

public interface FileContentProcessor {
    void processContents(File path, byte[] binaryContent, List<String> textContent);
}
