import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FilesManager {
     String path;
     List<File> files;

    FilesManager(String path){
        this.path = path;
        searchForImgFiles();
    }

     List<File> searchForImgFiles(){
        List<File> arrayFilesList = new ArrayList<File>();
//         File dir = new File(path);
//         File [] files = dir.listFiles(new FilenameFilter() {
//             @Override
//             public boolean accept(File dir, String name) {
//                 return name.endsWith(".jpg") || name.endsWith(".png");
//             }
//         });
//
//         for (File imgFile : files) {
//             arrayFilesList.add(imgFile);
//         }

         try (Stream<Path> paths = Files.walk(Paths.get(this.path))) {
             paths
                     .filter((f)-> {
                         return f.toString().indexOf(".jpg") != -1 || f.toString().indexOf(".png") != -1;

                     })
                     .forEach((f)->{
                         File file = new File(String.valueOf(f));
                         arrayFilesList.add(file);
                         System.out.println(file);
                     });

         } catch (IOException e) {
             e.printStackTrace();
         }

        this.files = new ArrayList<File>();
         this.files.addAll(arrayFilesList);
         return arrayFilesList;
    }

    public List<File> getFiles() {
        return files;
    }

    public void createDirectories(String[] dirs){

        for(String s : dirs){
            createDirectory(s);
        }
    }

    private boolean createDirectory(String name){
        File dir = new File(path+"/"+name);
        boolean bool = dir.mkdirs();

        return bool;

    }

    public void moveFileToNewDirectory(File file, String dir){
        System.out.println(file.getPath());
        try {
            Files.move(Paths.get(file.getPath()),Paths.get(path+ "/" +dir + "/" +file.getName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
