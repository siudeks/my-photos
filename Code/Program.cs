using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;

namespace Code
{
    class Program
    {
        static void Main(string[] args)
        {
            // Niektóre zdjecia mają przedrostek "WP_YYYYDDMM_*.jpg" gdzie YYYYMMDD reprezentuje jakąś datę.
            // Przedrostek WP_ chcę usunąć, podobnie jak i inne wyjtki
            // Because number of elements in my photo folder is too big, we need to create 
            // piece of software for that purpose.
            // Operacja ma dotyczyć wszystkich moich zdjęć które już znajdują się na dysku w folderze obarzów
            var ioc = new ServiceCollection()
                            .AddLogging(loggingBuilder => loggingBuilder.AddConsole())
                            .AddSingleton<ImageNameNormalizer>()
                            .AddSingleton<IRenameStrategy, RenameStrategyWPJPG>()
                            .AddSingleton<IRenameStrategy, RenameStrategyWPMP4>()
                            .AddSingleton<IRenameStrategy, RenameStrategyIMGJPG>()
                            .AddSingleton<IRenameStrategy, RenameStrategyIMGPNG>()
                            .AddSingleton<IRenameStrategy, RenameStrategyVIDMP4>()
                            .BuildServiceProvider();

            var entry = ioc.GetService<ImageNameNormalizer>();
            //entry.Run(@"S:\siudeks\OneDrive\Praca\Siudek & Junger\SLO11-LOK1\Sklep Wega Zdjęcia");
            entry.Run(@"S:\siudeks\OneDrive\Obrazy\Z aparatu");
            
        }
    }
}
