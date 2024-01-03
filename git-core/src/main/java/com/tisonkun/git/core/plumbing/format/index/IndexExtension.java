/*
 * Copyright 2024 tison <wander4096@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tisonkun.git.core.plumbing.format.index;

import com.tisonkun.git.core.plumbing.hash.HashConstants;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class IndexExtension {
    private final CharSequence sig;
    private final ByteBuf data;

    // TODO(@tisonkun) destructure extension data
    public static Optional<IndexExtension> create(ByteBuf bytes) {
        if (bytes.readableBytes() <= HashConstants.SIZE) {
            return Optional.empty();
        }

        final CharSequence sig = bytes.readCharSequence(4, StandardCharsets.US_ASCII);
        final int size = bytes.readInt();
        final ByteBuf data = bytes.readBytes(size);
        return Optional.of(new IndexExtension(sig, data));
    }
}
