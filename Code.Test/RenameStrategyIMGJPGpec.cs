using System;
using FluentAssertions;
using Microsoft.Extensions.Logging.Abstractions;
using Xunit;

namespace Code
{
    public class RenameStrategyIMGJPGSpec
    {
        IRenameStrategy strategy = new RenameStrategyIMGJPG();

        [Fact]
        public void shouldRename()
        {
            const string fileName = "iMg_20000102_name.jPg";
            strategy
                .CanRename(fileName)
                .Should()
                .BeTrue();
            strategy
                .Rename(fileName)
                .Should()
                .Be("20000102_name.jPg");
        }
    }
}
