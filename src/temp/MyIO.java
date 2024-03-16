package temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MyIO {
    public static void main(String[] args) {

    }

    public static void unzip(File src, File dest) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(src), Charset.forName("GBK"));
        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            if (entry.isDirectory()) {
                File file = new File(dest, entry.toString());
                file.mkdirs();
            } else {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(dest, entry.toString()));
                int b;
                while ((b = zipInputStream.read()) != -1) {
                    fileOutputStream.write(b);
                }
                fileOutputStream.close();
                zipInputStream.closeEntry();
            }
        }
        zipInputStream.close();
    }



}
