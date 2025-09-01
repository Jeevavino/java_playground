import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoadFile {

    private void performFileRead() throws CustomException {
        File file = new File("/dfddd");
        try {
            FileReader read = new FileReader(file);

            int i = 0;
            //very important logic
        } catch (FileNotFoundException e) {
            throw new CustomException("File not found: " + file.getPath());
        }
    }
    public static void main(String[] args) {
        LoadFile lf = new LoadFile();
        lf.performFileRead();
    }
}

class CustomException extends RuntimeException {
    public CustomException(String message){
        super(message);
    }
}
