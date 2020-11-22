using FluentAssertions;
using Xunit;

namespace Code
{
    public class RenameStrategyIMGPNGSpec
    {
        IRenameStrategy strategy = new RenameStrategyIMGPNG();

        [Fact]
        public void shouldRename()
        {
            const string fileName = "iMg_20000102_name.pNg";
            strategy
                .CanRename(fileName)
                .Should()
                .BeTrue();
            strategy
                .Rename(fileName)
                .Should()
                .Be("20000102_name.pNg");
        }
    }
}
