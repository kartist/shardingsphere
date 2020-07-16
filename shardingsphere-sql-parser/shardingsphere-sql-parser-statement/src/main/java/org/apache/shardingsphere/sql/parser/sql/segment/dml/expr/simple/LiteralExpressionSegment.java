/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.sql.parser.sql.segment.dml.expr.simple;

import lombok.Getter;
import lombok.ToString;

/**
 * Literal expression segment.
 */
@Getter
@ToString
public class LiteralExpressionSegment implements SimpleExpressionSegment {

    private final int startIndex;

    private final int stopIndex;

    private final Object literals;

    public LiteralExpressionSegment(final int startIndex, final int stopIndex,
            final Object literals) {
        this.startIndex = startIndex;
        this.literals = literals;
        this.stopIndex = fixStopIndex(stopIndex);
    }

    private int fixStopIndex(final int stopIndex) {
        if (this.literals instanceof String) {
            CodePointCounter pointCounter = new CodePointCounter();
            pointCounter.setInput((String) this.literals);
            int start = this.startIndex;
            int end = stopIndex - start - 1;
            int actualIndex = pointCounter.advanceToIndex(end);
            if (actualIndex != end) {
                return start + actualIndex + 2;
            }
        }
        return stopIndex;
    }

    public final class CodePointCounter {

        private String input;

        private int inputIndex;

        private int codePointIndex;

        /**
         * set data.
         *
         * @param input input
         */
        public void setInput(final String input) {
            this.input = input;
        }

        /**
         * get index.
         *
         * @param newCodePointIndex total index
         * @return index
         */
        public int advanceToIndex(final int newCodePointIndex) {
            assert newCodePointIndex >= codePointIndex;
            while (codePointIndex < newCodePointIndex) {
                int codePoint = Character.codePointAt(input, inputIndex);
                inputIndex += Character.charCount(codePoint);
                codePointIndex++;
            }
            return inputIndex;
        }
    }
}
