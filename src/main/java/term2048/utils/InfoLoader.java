package term2048.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.io.InputStream;
import term2048.ui.TileInfoRepository;
import term2048.ui.constants.TileInfo;

public class InfoLoader {

    private static final String PATH = "config.yaml";

    public static void load() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        SimpleModule module = new SimpleModule();
        module.addDeserializer(TileInfo.class, new InfoParser());
        mapper.registerModule(module);
        try {
            InputStream is = InfoLoader.class.getClassLoader().getResourceAsStream("config.yaml");
            TileInfoRepository.addInfos(mapper.readValue(is, new TypeReference<>() {
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
