//
// MIT License
//
// Copyright (c) 2020 IntellectualSites
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
package com.intellectualsites.commands.components;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

public class StandardCommandSyntaxFormatter implements CommandSyntaxFormatter {

    public StandardCommandSyntaxFormatter() {
    }

    @Nonnull
    @Override
    public String apply(@Nonnull final List<CommandComponent<?, ?>> commandComponents) {
        final StringBuilder stringBuilder = new StringBuilder();
        final Iterator<CommandComponent<?, ?>> iterator = commandComponents.iterator();
        while (iterator.hasNext()) {
            final CommandComponent<?, ?> commandComponent = iterator.next();
            if (commandComponent instanceof StaticComponent) {
                stringBuilder.append(commandComponent.getName());
            } else {
                if (commandComponent.isRequired()) {
                    stringBuilder.append("<").append(commandComponent.getName()).append(">");
                } else {
                    stringBuilder.append("[").append(commandComponent.getName()).append("]");
                }
            }
            if (iterator.hasNext()) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

}
