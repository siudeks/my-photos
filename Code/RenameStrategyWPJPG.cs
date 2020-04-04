using System.IO;
using System.Text.RegularExpressions;
using Microsoft.Extensions.Logging;

namespace Code
{

    public class RenameStrategyWPJPG : RenameStrategyBase
    {

        private const string mask = @"wp_\d{8}_.*\.jpg";

        public override bool CanRename(string fullFileName)
        {
            var fileName = Path.GetFileName(fullFileName);
            if (!Regex.IsMatch(fileName, mask, RegexOptions.IgnoreCase)) return false;

            return true;
        }

        protected override string generateNewFileName(string current) => current.Substring(3);

    }
}