class FileNode {
    String date;
    String time;
    int type; // 0 for file, 1 for directory
    String name;
    long size; // size in bytes

    public FileNode(String date, String time, int type, String name, long size) {
        this.date = date;
        this.time = time;
        this.type = type;
        this.name = name;
        this.size = size;
    }
}
