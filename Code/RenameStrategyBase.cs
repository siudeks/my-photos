using System.IO;
using System.Text.RegularExpressions;

namespace Code
{

    public abstract class RenameStrategyBase : IRenameStrategy
    {

        private const string mask = @"wp_\d{8}_.*\.mp4";

        public abstract bool CanRename(string fullFileName);

        public string Rename(string fullFileName)
        {
            var parentFolderName = Path.GetDirectoryName(fullFileName);
            var fileName = Path.GetFileName(fullFileName);
            var newFileName = generateNewFileName(fileName);
            return Path.Combine(parentFolderName, newFileName);
        }

        protected abstract string generateNewFileName(string current);
    }
}