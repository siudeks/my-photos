using System;
using FluentAssertions;
using Microsoft.Extensions.Logging.Abstractions;
using Xunit;

namespace Code
{
    public class RenameStrategyVIDMP4Spec
    {
        IRenameStrategy strategy = new RenameStrategyVIDMP4();

        [Fact]
        public void shouldRename()
        {
            const string fileName = "vId_20000102_name.mP4";
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
