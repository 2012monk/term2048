package term2048.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import term2048.ui.constants.TileInfo;

public class InfoParser extends StdDeserializer<TileInfo> {

    public InfoParser() {
        super(TileInfo.class);
    }

    @Override
    public TileInfo deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        int number = Integer.parseInt(node.get("number").asText());
        int fg = (int) node.get("fg").numberValue();
        int bg = (int) node.get("bg").numberValue();
        return new TileInfo(number, fg, bg);
    }
}
