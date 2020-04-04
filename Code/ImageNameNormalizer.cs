using System;
using System.Collections.Generic;
using System.IO;
using Microsoft.Extensions.Logging;
using System.Linq;
using LanguageExt;

namespace Code
{

    public class ImageNameNormalizer
    {
        private readonly ILogger<ImageNameNormalizer> logger;
        private int operationCounter;

        private readonly IEnumerable<IRenameStrategy> renameStrategies;
        public ImageNameNormalizer(IEnumerable<IRenameStrategy> strategies, ILoggerFactory loggerFactory)
        {
            this.renameStrategies = strategies;
            logger = loggerFactory.CreateLogger<ImageNameNormalizer>();
        }

        public void Run(string rootPath)
        {
            logger.LogInformation($"Start processing {rootPath}");

            var fileNames = Directory.GetFiles(rootPath, "*.*", SearchOption.AllDirectories);
            foreach (var fileName in fileNames)
            {
                var strategy = renameStrategies.FirstOrDefault(it => it.CanRename(fileName));
                if (strategy == null) continue;

                var normalizedName = strategy.Rename(fileName);

                var oldFolder = Path.GetDirectoryName(fileName);
                var newFolder = Path.GetDirectoryName(fileName);
                if (oldFolder != newFolder) {
                    logger.LogError($"Prevented: Some strategy tries to change parent folder {oldFolder} -> {newFolder}");
                    break;
                }

                var rootPathLength = rootPath.Length;
                logger.LogInformation($"{++operationCounter}: Change name {fileName.Substring(rootPathLength)} -> {normalizedName.Substring(rootPathLength)}");
                File.Move(fileName, normalizedName);
            }
        }
    }

}