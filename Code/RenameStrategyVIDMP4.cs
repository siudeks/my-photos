using System.IO;
using System.Text.RegularExpressions;

namespace Code
{

    public class RenameStrategyVIDMP4 : RenameStrategyBase
    {

        private const string mask = @"vid_\d{8}_.*\.mp4";

        public override bool CanRename(string fullFileName)
        {
            var fileName = Path.GetFileName(fullFileName);
            if (!Regex.IsMatch(fileName, mask, RegexOptions.IgnoreCase)) return false;

            return true;
        }

        protected override string generateNewFileName(string current) => current.Substring(4);
    }
}