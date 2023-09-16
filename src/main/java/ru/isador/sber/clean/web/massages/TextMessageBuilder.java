package ru.isador.sber.clean.web.massages;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

/**
 * Класс для десериализации входящего сообщения из json формата.
 *
 * @see StdDeserializer
 * @since 1.0.0
 */
public class TextMessageBuilder extends StdDeserializer<TextMessage> {

    public TextMessageBuilder() {
        super(TextMessage.class);
    }

    @Override
    public TextMessage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var n = p.getCodec().readTree(p);
        JsonNode content = (TextNode) n.get("text");
        if (content == null) {
            return new TextMessage(); // TODO: В тз не указано как поступать с некорректными запросами, поэтому пока их просто пропускаем
        }

        return new TextMessage(content.asText());
    }
}
