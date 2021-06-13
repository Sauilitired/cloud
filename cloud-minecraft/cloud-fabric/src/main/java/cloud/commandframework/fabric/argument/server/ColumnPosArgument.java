//
// MIT License
//
// Copyright (c) 2021 Alexander Söderberg & Contributors
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//
package cloud.commandframework.fabric.argument.server;

import cloud.commandframework.ArgumentDescription;
import cloud.commandframework.arguments.CommandArgument;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.fabric.argument.FabricArgumentParsers;
import cloud.commandframework.fabric.data.Coordinates.ColumnCoordinates;
import net.minecraft.core.BlockPos;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;
import java.util.function.BiFunction;

/**
 * An argument for resolving {@link ColumnCoordinates column (xz) coordinates}.
 *
 * @param <C> the sender type
 * @since 1.5.0
 */
public final class ColumnPosArgument<C> extends CommandArgument<C, ColumnCoordinates> {

    ColumnPosArgument(
            final boolean required,
            final @NonNull String name,
            final @NonNull String defaultValue,
            final @Nullable BiFunction<CommandContext<C>, String, List<String>> suggestionsProvider,
            final @NonNull ArgumentDescription defaultDescription
    ) {
        super(
                required,
                name,
                FabricArgumentParsers.columnPos(),
                defaultValue,
                ColumnCoordinates.class,
                suggestionsProvider,
                defaultDescription
        );
    }

    /**
     * Create a new {@link Builder}.
     *
     * @param name Name of the argument
     * @param <C>  Command sender type
     * @return Created builder
     * @since 1.5.0
     */
    public static <C> ColumnPosArgument.@NonNull Builder<C> builder(final @NonNull String name) {
        return new ColumnPosArgument.Builder<>(name);
    }

    /**
     * Create a new required {@link ColumnPosArgument}.
     *
     * @param name Component name
     * @param <C>  Command sender type
     * @return Created argument
     * @since 1.5.0
     */
    public static <C> @NonNull ColumnPosArgument<C> of(final @NonNull String name) {
        return ColumnPosArgument.<C>builder(name).asRequired().build();
    }

    /**
     * Create a new optional {@link ColumnPosArgument}.
     *
     * @param name Component name
     * @param <C>  Command sender type
     * @return Created argument
     * @since 1.5.0
     */
    public static <C> @NonNull ColumnPosArgument<C> optional(final @NonNull String name) {
        return ColumnPosArgument.<C>builder(name).asOptional().build();
    }

    /**
     * Create a new optional {@link ColumnPosArgument} with the specified default value.
     *
     * @param name         Component name
     * @param defaultValue default value, y coordinate will be ignored as it is always 0
     * @param <C>          Command sender type
     * @return Created argument
     * @since 1.5.0
     */
    public static <C> @NonNull ColumnPosArgument<C> optional(final @NonNull String name, final @NonNull BlockPos defaultValue) {
        return ColumnPosArgument.<C>builder(name)
                .asOptionalWithDefault(defaultValue)
                .build();
    }

    /**
     * Builder for {@link ColumnPosArgument}.
     *
     * @param <C> sender type
     * @since 1.5.0
     */
    public static final class Builder<C> extends TypedBuilder<C, ColumnCoordinates, Builder<C>> {

        Builder(final @NonNull String name) {
            super(ColumnCoordinates.class, name);
        }

        /**
         * Build a new {@link ColumnPosArgument}.
         *
         * @return Constructed argument
         * @since 1.5.0
         */
        @Override
        public @NonNull ColumnPosArgument<C> build() {
            return new ColumnPosArgument<>(
                    this.isRequired(),
                    this.getName(),
                    this.getDefaultValue(),
                    this.getSuggestionsProvider(),
                    this.getDefaultDescription()
            );
        }

        /**
         * Sets the command argument to be optional, with the specified default value.
         *
         * @param defaultValue default value
         * @return this builder
         * @see CommandArgument.Builder#asOptionalWithDefault(String)
         * @since 1.5.0
         */
        public @NonNull Builder<C> asOptionalWithDefault(final @NonNull BlockPos defaultValue) {
            return this.asOptionalWithDefault(String.format(
                    "%s %s",
                    defaultValue.getX(),
                    defaultValue.getZ()
            ));
        }

    }

}
