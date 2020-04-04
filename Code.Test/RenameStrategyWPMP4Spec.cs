using System;
using FluentAssertions;
using Microsoft.Extensions.Logging.Abstractions;
using Xunit;

namespace Code
{
    public class RenameStrategyWPMP4Spec
    {
        IRenameStrategy strategy = new RenameStrategyWPMP4();

        [Fact]
        public void shouldRename()
        {
            const string fileName = "WP_20000102_name.mP4";
            strategy
                .CanRename(fileName)
                .Should()
                .BeTrue();
            strategy
                .Rename(fileName)
                .Should()
                .Be("20000102_name.mP4");
        }
    }
}
