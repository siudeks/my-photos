using System.IO;
using System.Text.RegularExpressions;

namespace Code
{

    public class RenameStrategyIMGPNG : RenameStrategyBase
    {

        private const string mask = @"img_\d{8}_.*\.png";

        public override bool CanRename(string fullFileName)
        {
            var fileName = Path.GetFileName(fullFileName);
            if (!Regex.IsMatch(fileName, mask, RegexOptions.IgnoreCase)) return false;

            return true;
        }

        protected override string generateNewFileName(string current) => current.Substring(4);
    }
}