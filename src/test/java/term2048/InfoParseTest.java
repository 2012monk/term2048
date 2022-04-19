package term2048;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import term2048.ui.TileInfoRepository;
import term2048.ui.constants.TileInfo;
import term2048.utils.InfoParser;

public class InfoParseTest {

    @Test
    void readTest() throws IOException {
        String path = "src/test/resources/test.yaml";
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        SimpleModule module = new SimpleModule();
        module.addDeserializer(TileInfo.class, new InfoParser());
        mapper.registerModule(module);
        List<TileInfo> infos = mapper.readValue(new File(path), new TypeReference<>() {
        });
        assert infos != null;
        TileInfo expect = new TileInfo(2, 0, 0xa);
        TileInfo r = infos.get(0);
        assert r.getNumber() == (expect.getNumber());
        assert r.getDefault().getBg().getHex() == expect.getDefault().getBg().getHex();
        expect = new TileInfo(4, 0x10, 0xff00ff);
        r = infos.get(1);
        assert r.getNumber() == (expect.getNumber());
        assert r.getDefault().getBg().getHex() == expect.getDefault().getBg().getHex();
    }

    @Test
    void loaderTest() throws IOException {
        TileInfo t = TileInfoRepository.getTileInfo(2);
        assert t != null;
        assert t.getNumber() == 2;
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        SimpleModule module = new SimpleModule();
        module.addDeserializer(TileInfo.class, new InfoParser());
        mapper.registerModule(module);
        mapper.writeValue(System.out, TileInfoRepository.tiles());
    }
}
