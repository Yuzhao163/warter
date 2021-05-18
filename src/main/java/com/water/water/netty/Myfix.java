package com.water.water.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;

import java.util.List;

import static io.netty.util.internal.ObjectUtil.checkPositive;

/**
 * @author zhaoyu
 * @version 1.0
 * @date 2021/5/17 9:24
 */
public class Myfix extends ByteToMessageDecoder{
/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

/**
 * A decoder that splits the received {@link ByteBuf}s by the fixed number
 * of bytes. For example, if you received the following four fragmented packets:
 * <pre>
 * +---+----+------+----+
 * | A | BC | DEFG | HI |
 * +---+----+------+----+
 * </pre>
 * A {@link io.netty.handler.codec.FixedLengthFrameDecoder}{@code (3)} will decode them into the
 * following three packets with the fixed length:
 * <pre>
 * +-----+-----+-----+
 * | ABC | DEF | GHI |
 * +-----+-----+-----+
 * </pre>
 */
    private final int frameLength1;
    private final int frameLength2;

    /**
     * Creates a new instance.
     *
     * @param frameLength1 the length of the frame
     * @param frameLength2 the length of the frame
     *
     */
    public Myfix(int frameLength1,int frameLength2) {
        checkPositive(frameLength1, "frameLength1");
        checkPositive(frameLength2, "frameLength2");
        this.frameLength1 = frameLength1;
        this.frameLength2 = frameLength2;
    }

    @Override
    protected final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object decoded = decode(ctx, in);
        if (decoded != null) {
            out.add(decoded);
        }
    }

    /**
     * Create a frame out of the {@link ByteBuf} and return it.
     *
     * @param   ctx             the {@link ChannelHandlerContext} which this {@link ByteToMessageDecoder} belongs to
     * @param   in              the {@link ByteBuf} from which to read data
     * @return  frame           the {@link ByteBuf} which represent the frame or {@code null} if no frame could
     *                          be created.
     */
    protected Object decode(
            @SuppressWarnings("UnusedParameters") ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.readableBytes() < frameLength2 && in.readableBytes() >= frameLength1) {
            return in.readRetainedSlice(frameLength1);
        } else if(in.readableBytes() < frameLength1){
            return null;
        }else {
            return in.readRetainedSlice(frameLength2);
        }
    }
}
