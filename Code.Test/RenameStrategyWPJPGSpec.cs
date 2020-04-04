using System;
using FluentAssertions;
using Microsoft.Extensions.Logging.Abstractions;
using Xunit;

namespace Code
{
    public class RenameStrategyWPJPGSpec
    {
        IRenameStrategy strategy = new RenameStrategyWPJPG();

        [Fact]
        public void shouldRename()
        {
            const string fileName = "WP_20000102_name.jPg";
            strategy
                .CanRename(fileName)
                .Should()
                .BeTrue();
            strategy
                .Rename(fileName)
                .Should()
                .Be("20000102_name.jPg");
        }

        [Fact]
        public void shouldNotRenameUnknown()
        {
            strategy
                .CanRename("not supported naming style.jpg")
                .Should()
                .BeFalse();
        }
    }
}
