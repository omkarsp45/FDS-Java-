public class Main {
    public static void displayDirectoryContents(DirectoryNode directory) {
        System.out.println("Directory: " + directory.name);

        long totalSize = 0;

        for (FileNode file : directory.files) {
            System.out.println("File:" +
                    " Name: " + file.name +
                    ", Date: " + file.date +
                    ", Time: " + file.time +
                    ", Size: " + file.size + " bytes");

            totalSize += file.size;
        }

        for (DirectoryNode subDirectory : directory.directories) {
            displayDirectoryContents(subDirectory);
        }

        System.out.println("Total Disk Space: " + totalSize + " bytes");
    }

    public static void main(String[] args) {
        // Create a sample directory structure
        DirectoryNode rootDirectory = new DirectoryNode("Root");

        FileNode file1 = new FileNode("2023-01-01", "12:00", 0, "file1.txt", 1024);
        FileNode file2 = new FileNode("2023-01-02", "14:30", 0, "file2.txt", 2048);

        DirectoryNode subDirectory = new DirectoryNode("SubDirectory");
        FileNode file3 = new FileNode("2023-01-03", "10:45", 0, "file3.txt", 1536);

        rootDirectory.addFile(file1);
        rootDirectory.addFile(file2);

        subDirectory.addFile(file3);
        rootDirectory.addDirectory(subDirectory);
        
        displayDirectoryContents(rootDirectory);
    }
}

