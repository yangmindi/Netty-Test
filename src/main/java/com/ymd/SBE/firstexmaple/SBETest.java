package com.ymd.SBE.firstexmaple;


import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.agrona.concurrent.UnsafeBuffer;
import secondTest.MessageDecoder;
import secondTest.MessageEncoder;
import secondTest.MessageHeaderDecoder;
import secondTest.MessageHeaderEncoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardOpenOption.*;

public class SBETest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
        final UnsafeBuffer directBuffer = new UnsafeBuffer(byteBuffer);
        MessageHeaderEncoder messageHeaderEncoder = new MessageHeaderEncoder();


        //编码
        MessageEncoder messageEncoder = new MessageEncoder();
        messageEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder)
                .age(20)
                .name(new String("张三".getBytes(MessageEncoder.nameCharacterEncoding()), StandardCharsets.UTF_8))
                .address(new String("北京".getBytes(MessageEncoder.addressCharacterEncoding()), StandardCharsets.UTF_8));


        int length = messageEncoder.encodedLength() + messageHeaderEncoder.encodedLength();
        try {
            FileChannel channel = FileChannel.open(Paths.get("SBE1.txt"), READ, WRITE, CREATE);
            byteBuffer.limit(length);
            channel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //解码
        MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();
        messageHeaderDecoder.wrap(directBuffer, 0);
        int blockLength = messageHeaderDecoder.blockLength();
        int version = messageHeaderDecoder.version();

        final StringBuilder sb = new StringBuilder();
        MessageDecoder messageDecoder = new MessageDecoder();
        messageDecoder.wrap(directBuffer, messageHeaderDecoder.encodedLength(), blockLength, version);
        sb.append("\nname=").append(messageDecoder.name());
        sb.append("\nage=").append(messageDecoder.age());
        sb.append("\naddress=").append(messageDecoder.address());
        System.out.println(sb);


        String a = "asd";
        StringBuilder builder = new StringBuilder(a);
        Map<Character, Integer> map = new HashMap();
        map.put('a', 1);
        Map<Character, Integer> map1 = map;
        System.out.println(map.get('a'));

    }
}
