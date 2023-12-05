import java.util.*;

class DirectoryNode {
    String name;
    List<FileNode> files;
    List<DirectoryNode> directories;

    public DirectoryNode(String name) {
        this.name = name;
        this.files = new ArrayList<>();
        this.directories = new ArrayList<>();
    }

    public void addFile(FileNode file) {
        files.add(file);
    }

    public void addDirectory(DirectoryNode directory) {
        directories.add(directory);
    }
}
