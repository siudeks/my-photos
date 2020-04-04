namespace Code {

    public interface IRenameStrategy
    {
        bool CanRename(string fullFileName);
        string Rename(string fullFileName);
    }
}